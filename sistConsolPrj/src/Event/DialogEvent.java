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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import View.LogDialog;


public class DialogEvent extends WindowAdapter implements ActionListener {
	private LogDialog ld;
	private int first,last;
	private int mode;
	private int code200,code403,code404,code500;
	private double code403pct,code500pct;
	private String filePath, fileName;
	private String[] langList = {"mongodb","res","ora","javascript", "java","hadoop","jsp","d8","jk9k","front" };
	private String[] browserList= {"opera", "ie", "firefox", "Chrome", "Safari" };
	private int[] cnt = new int[langList.length];
	private int[] browserCnt = new int[browserList.length];
	private double[] browserPercent = new double[browserList.length];
	
	private Map<String, Integer> countLang = new HashMap<String, Integer>();
	private Map<String, Integer> browser = new HashMap<String, Integer>();
	private String maxCntKey;
	
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
			if(ld.getGroa().equals("관리자")) {
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
					of.write("");
					of.flush();
				} catch (IOException e) {
					e.printStackTrace();
		    
				}//catch
			}//if else
		}//if
		
		
		//LINE 버튼이 눌렸을 때
		if(ae.getSource()==ld.getJbtnLine()) {
			if(ld.getJtxfFir().getText().equals("")) {
				first = 0;
			}
			else if(ld.getJtxfLast().getText().equals("")) {
				JOptionPane.showMessageDialog(ld, "마지막줄을 써주세요");
			}//if
			else {
				first=Integer.parseInt(ld.getJtxfFir().getText());
				last=Integer.parseInt(ld.getJtxfLast().getText());
			}
			
			System.out.println(first);
			System.out.println(last);
			
			
		}//if
		
		//View버튼 눌렸을때 
		if(ae.getSource() == ld.getJbtnView()) {
			selectLogFile();
			
		}//if
	
	}//actionPerformed
	
	
	//파일선택 method
	public void selectLogFile() {
		FileDialog fd = new FileDialog(ld, "log파일 선택", FileDialog.LOAD);
		fd.setVisible(true);
		
		String selPath =  fd.getDirectory();
		fileName = fd.getFile();
		int lastFileidx;
		
		if(fileName == null) {
			JOptionPane.showMessageDialog(fd, "파일을 선택해주세요");
		}else {
			lastFileidx = fileName.length();
			if(fileName.substring(lastFileidx-4, lastFileidx).equals(".log")) {
				//파일 선택
				filePath = selPath+fileName;
				try {
					logAnalyze();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(fd, "로그파일을 선택해 주세요.");
			}
		}
	}//selectLogFile
	
	//로그파일 분석
	public void logAnalyze() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String fileContext="";
		while((fileContext = br.readLine())!= null) {
			keyLang(fileContext);
			browserCnt(fileContext);
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
			System.out.printf("%.2f\n", browserPercent[i]);
		}
	}	
	//4.최다 사용시간
	public void hourCount(String fileContext) {
		//로그 시간구해 배열에 저장
		int hour[]= {Integer.parseInt(fileContext.substring(fileContext.lastIndexOf("[")+1, fileContext.lastIndexOf("]")).substring(11,13))};
		int[] index= {};
		int max=Integer.MIN_VALUE;
		
		//최다 시간 구하기
		for(int i=0; i<hour.length;i++) {
			index[hour[i]]++;
		}
		for(int i=0; i<index.length;i++) {
			if(max<index[i]) {
				max=index[i];
				mode=i;
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
		code403pct=(state*100)/code403;
		code500pct=(state*100)/code500;
	}
	
	//7번 입력된줄에 맞춰서 구하기
	
	
	
}//class


