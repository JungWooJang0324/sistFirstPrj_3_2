package Event;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import View.LogDialog;
import View.Result;
import View.testModel;


public class DialogEvent extends WindowAdapter implements ActionListener {
	private LogDialog ld;
	private int first,last;
	private String mode;
	private int code200,code403,code404,code500;
	//줄번호 
	private int lineCnt;
	private int maxVal;
	private String code403pct,code500pct;
	private String filePath, fileName;
	private String[] langList = {"mongodb","res","ora","javascript", "java","hadoop","jsp","d8","jk9k","front" };
	private String[] browserList= {"opera", "ie", "firefox", "Chrome", "Safari" };
	private int[] cnt = new int[langList.length];
	private int[] browserCnt = new int[browserList.length];
	private double[] browserPercent = new double[browserList.length];	
	private Map<String, Integer> countLang = new HashMap<String, Integer>();
	private Map<String, Integer> browser = new HashMap<String, Integer>();
	private Map<String, Integer> cntFromInput = new HashMap<String, Integer>();	
	private Map<String, Integer> hourCnt = new HashMap<String, Integer>();	
	private String maxCntKey;
	private String maxCntKeyFromInput;
	
	public DialogEvent(LogDialog ld) {
		this.ld=ld;
	}
	
	@Override
	public void windowClosing(WindowEvent we) {
		ld.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		//REPORT 버튼이 눌렸을 때
		if(ae.getSource()==ld.getJbtnReport()) {
			//아이디가 root면 오류메세지 생성
			if(ae.getSource()!=ld.getJbtnLine()||ae.getSource()!=ld.getJtxfLast()) {
				JOptionPane.showMessageDialog(ld,"LINE이나 VIEW버튼을 입력해주세요");				
			}
			else if(ld.getGroa().equals("관리자")) {
				JOptionPane.showMessageDialog(ld,"문서를 생성할 수 있는 권한이 없음");
			}else {
				JOptionPane.showMessageDialog(ld,"파일이 생성되었습니다.");
				
				//root 이외 사용자는 폴더 및 파일 생성
				File folder=new File("c:/dev/report/");
				folder.mkdirs();
				SimpleDateFormat sfm=new SimpleDateFormat("yyyyMMdd");
				Date date=new Date();
				String fmt=sfm.format(date);
				File outFile=new File("c:/dev/report/report_"+fmt+".dat");
				
				FileWriter of=null;
				
				try {
					of=new FileWriter(outFile);
					of.write(btnReport());
					of.flush();
				} catch (IOException e) {
					e.printStackTrace();
		    
				}//catch
			}//if else
		}//if
		
		
		//LINE 버튼이 눌렸을 때
		if(ae.getSource()==ld.getJbtnLine() || ae.getActionCommand().equals("firLastInput")) {
			if(!ld.getJtxfFir().getText().isEmpty() && !ld.getJtxfLast().getText().isEmpty()) {
				first=Integer.parseInt(ld.getJtxfFir().getText());
				last=Integer.parseInt(ld.getJtxfLast().getText());				
				selectLogFile();
				runLog();
			}			
			if(ld.getJtxfFir().getText().isEmpty() && !ld.getJtxfLast().getText().isEmpty()) {
				first = 0;
				last=Integer.parseInt(ld.getJtxfLast().getText());				
				selectLogFile();				
				runLog();
			}			
			if(ld.getJtxfLast().getText().isEmpty()) {
				JOptionPane.showMessageDialog(ld, "찾으실 마지막줄을 써주세요");
			}//if
			
		}//if
		
		//View버튼 눌렸을때 
		if(ae.getSource() == ld.getJbtnView()) {
			selectLogFile();	
			runLog();
			
		}//if
	
	}//actionPerformed
	
	public void runLog() {
		try {
			logAnalyze();
			new testModel(this);
//			new Result(this, ld);
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(ld, "파일을 선택해주세요!");
		} catch(IOException e) {
			e.printStackTrace();				
		}
	}
	
	//파일선택 method
	public void selectLogFile() {
		FileDialog fd = new FileDialog(ld, "log파일 선택", FileDialog.LOAD);
		fd.setVisible(true);
		
		String selPath =  fd.getDirectory();
		fileName = fd.getFile();
		filePath = selPath+fileName;

	}//selectLogFile
	
	//로그파일 분석
	public void logAnalyze() throws IOException, FileNotFoundException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));

			String fileContext="";
			while((fileContext = br.readLine())!= null) {
				lineCnt++;
				keyLang(fileContext);
				browserCnt(fileContext);
				stateCount(fileContext);
				hourCount(fileContext);
				if(lineCnt>=first && lineCnt<=last) {
					maxKeysFromInput(fileContext);
				}
				if(first==0 && last == 0) {
					maxKeysFromInput(fileContext);
				}
			}
		}finally {
			if(br != null) {
				br.close();
			}
		}
		}
	
	
	//키값=java... 구하기
	public void keyLang(String fileContext) {
		String key;
		if(fileContext.contains("key")) {
			key = fileContext.substring(fileContext.indexOf("=")+1,fileContext.indexOf("&"));
			for(int i=0; i<langList.length;i++) {
				if(key.equals(langList[i])){
					countLang.put(langList[i], cnt[i]++);
				}//if
			}//for
		}//if	
		maxCntLang();
	}
	
	//1 . 최대갯수 구하기
	public void maxCntLang() {
		int maxVal = (Collections.max(countLang.values()));
		for(String key: countLang.keySet()) {
			Integer value= countLang.get(key);
			if(value == maxVal) {
				maxCntKey = key;
			}
		}
	}
	
	//2. 브라우저별 접속횟수
	public void browserCnt(String fileContext) {
		for(int i=0; i<browserList.length; i++) {
			if(fileContext.contains(browserList[i])) {
				browserCnt[i]++;
				browser.put(browserList[i], browserCnt[i]);
			}
		}
		browserAnalyze();
	}
	
	//2. 브라우저별 % 통계
	public void browserAnalyze() {
		int maxCnt = 0;
		for(int i=0; i<browserCnt.length; i++) {
			maxCnt+= browserCnt[i];
		}
		for(int i=0; i<browserCnt.length;i++) {
			browserPercent[i] = (double)(browserCnt[i]*100)/maxCnt;
		}
	}	
	//4.최다 사용시간
	public void hourCount(String fileContext) {
		String hour = fileContext.substring(fileContext.lastIndexOf("[") + 1, fileContext.lastIndexOf("]")).substring(11, 13);
		hourCnt.put(hour, hourCnt.get(hour)!=null? hourCnt.get(hour)+1:1);
		
		Set<String> setHour = hourCnt.keySet();
		Iterator<String> iterator = setHour.iterator();
		String hourKey = "";
		maxVal = 0;
		while(iterator.hasNext()) {
			hourKey = iterator.next();
			if(hourCnt.get(hourKey) > maxVal) {
				mode = hourKey;
				maxVal = hourCnt.get(hourKey);
			}
		}
	}
	//3.5.6. 서비스 상태 횟수구하기
	public void stateCount(String fileContext) {
		int state= Integer.parseInt (fileContext.substring(fileContext.indexOf("[")+1, fileContext.indexOf("]")));
		if(state==200) {
			code200++;
		}else if(state==403) {
			code403++;
		}else if(state==404) {
			code404++;
		}else if(state==500) {
			code500++;
		}
		code403pct= String.format("%4.2f", (code403 / (double)lineCnt) * 100);
		code500pct= String.format("%4.2f", (code500 /(double)lineCnt) * 100);
	}
	
	//7 줄행의 수 중 최다키 구하기
	public void maxKeysFromInput(String fileContext) {
		String key ="";
		if(fileContext.contains("key")) {
			key = fileContext.substring(fileContext.indexOf("=")+1,fileContext.indexOf("&"));
			cntFromInput.put(key, cntFromInput.get(key)!=null ? cntFromInput.get(key)+1 : 1);
		}//if
		
		int maxVal = (Collections.max(cntFromInput.values()));
		for(String keys: cntFromInput.keySet()) {
			if(cntFromInput.get(keys) == maxVal) {
				maxCntKeyFromInput = keys;
			}
		}
	}
	


	public String btnReport() {
		
	StringBuilder sb = new StringBuilder();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date d = new Date();
	String s = sdf.format(d);
	sb.append("1. 최다 사용키: ").append(maxCntKey).append(" ").append(countLang.get(maxCntKey)).append("회\n");
	sb.append("////////////////////////////////////////////\n");
	sb.append("2. 브라우저별 접속 횟수와 비율 : \n");
	sb.append("////////////////////////////////////////////\n");
	for(int i=0; i<browserPercent.length; i++) {
		sb.append(browserList[i]+" : "+browserCnt[i]+"회 ("+ String.format("%.2f", browserPercent[i])+"%)");
	}
	sb.append("////////////////////////////////////////////\n");
	sb.append("3. 서비스를 성공적으로 수행한(200)횟수,실패(404) 횟수\n").append("성공 횟수(200) : ").append(code200+"번").append("\n실패 횟수(404) : ").append(code404+"번\n");
	sb.append("////////////////////////////////////////////\n");
	sb.append("4. 요청이 가장 많은 시간 \n").append(maxVal);
	sb.append("////////////////////////////////////////////\n");
	sb.append("5. 비정상적인 요청(403)이 발생한 횟수,비율구하기\n").append("비정상적인 요청 횟수(403):").append(code403+"번")
	.append("\n비정상적인 요청 비율(403):").append(code403pct+"%");
	sb.append("\n////////////////////////////////////////////\n");
	sb.append("\n6. 요청에 대한 에러(500)가 발생한 횟수, 비율 구하기\n").append("요청에 대한 에러 횟수(500)").append(code500+"번")
	.append("\n요청에 대한 에러 비율(500)").append(code500pct+"%");
	
	return sb.toString();
}
	//getter
	//첫번째 입력된 줄
	public int getFirst() {
		return first;
	}
	
	//마지막줄
	public int getLast() {
		return last;
	}

	//최다 사용시간
	public String getMode() {
		return mode;
	}

	public int getCode200() {
		return code200;
	}

	public int getCode403() {
		return code403;
	}

	public int getCode404() {
		return code404;
	}

	public int getCode500() {
		return code500;
	}
	
	//줄 갯수
	public int getLineCnt() {
		return lineCnt;
	}

	public String getCode403pct() {
		return code403pct;
	}

	public String getCode500pct() {
		return code500pct;
	}

	//브라우저별 퍼센트리스트
	public double[] getBrowserPercent() {
		return browserPercent;
	}
	//요청받은 처음부터 끝까지
	public Map<String, Integer> getCntFromInput() {
		return cntFromInput;
	}
	
	//최대로 불린 키
	public String getMaxCntKey() {
		return maxCntKey;
	}

	//선택값에서 최대로 불린 키
	public String getMaxCntKeyFromInput() {
		return maxCntKeyFromInput;
	}

	public Map<String, Integer> getHourCnt() {
		return hourCnt;
	}

	public String[] getBrowserList() {
		return browserList;
	}

	public int[] getBrowserCnt() {
		return browserCnt;
	}
	
	
	//getters
	
	
}//class
