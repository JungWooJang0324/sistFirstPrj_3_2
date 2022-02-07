package Event;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import View.LogDialog;


public class DialogEvent extends WindowAdapter implements ActionListener {
	private LogDialog ld;
	private int first,last;
	private int mode;
	private int code200,code403,code404,code500;
	//�ٹ�ȣ 
	private int lineCnt;
	private double code403pct,code500pct;
	private String filePath, fileName;
	private String[] langList = {"mongodb","res","ora","javascript", "java","hadoop","jsp","d8","jk9k","front" };
	private String[] browserList= {"opera", "ie", "firefox", "Chrome", "Safari" };
	private int[] cnt = new int[langList.length];
	private int[] browserCnt = new int[browserList.length];
	private double[] browserPercent = new double[browserList.length];	
	private Map<String, Integer> countLang = new HashMap<String, Integer>();
	private Map<String, Integer> browser = new HashMap<String, Integer>();
	private Map<String, Integer> cntFromInput = new HashMap<String, Integer>();	
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
		//REPORT ��ư�� ������ ��
		if(ae.getSource()==ld.getJbtnReport()) {
			//���̵� root�� �����޼��� ����
			if(ld.getGroa().equals("������")) {
				JOptionPane.showMessageDialog(ld,"������ ������ �� �ִ� ������ ����");
			}else {
				JOptionPane.showMessageDialog(ld,"������ �����Ǿ����ϴ�.");
				
				//root �̿� ����ڴ� ���� �� ���� ����
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
		
		
		//LINE ��ư�� ������ ��
		if(ae.getSource()==ld.getJbtnLine() || ae.getSource()==ld.getJtxfLast()) {
			if(!ld.getJtxfFir().getText().isEmpty() && !ld.getJtxfLast().getText().isEmpty()) {
				first=Integer.parseInt(ld.getJtxfFir().getText());
				last=Integer.parseInt(ld.getJtxfLast().getText());				
			}			
			if(ld.getJtxfFir().getText().isEmpty() && !ld.getJtxfLast().getText().isEmpty()) {
				first = 0;
				last=Integer.parseInt(ld.getJtxfLast().getText());				
			}			
			if(ld.getJtxfLast().getText().isEmpty()) {
				JOptionPane.showMessageDialog(ld, "ã���� ���������� ���ּ���");
			}//if
			
			selectLogFile();
			
		}//if
		
		//View��ư �������� 
		if(ae.getSource() == ld.getJbtnView()) {
			selectLogFile();
			
		}//if
	
	}//actionPerformed
	
	
	//���ϼ��� method
	public void selectLogFile() {
		FileDialog fd = new FileDialog(ld, "log���� ����", FileDialog.LOAD);
		fd.setVisible(true);
		
		String selPath =  fd.getDirectory();
		fileName = fd.getFile();
		int lastFileidx;
		
		if(fileName == null) {
			JOptionPane.showMessageDialog(fd, "������ �������ּ���");
		}else {
			lastFileidx = fileName.length();
			if(fileName.substring(lastFileidx-4, lastFileidx).equals(".log")) {
				//���� ����
				filePath = selPath+fileName;
				try {
					logAnalyze();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(fd, "�α������� ������ �ּ���.");
			}
		}
	}//selectLogFile
	
	//�α����� �м�
	public void logAnalyze() throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(filePath));
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

		}
	}
	
	//Ű��=java... ���ϱ�
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
	
	//1 . �ִ밹�� ���ϱ�
	public void maxCntLang() {
		int maxVal = (Collections.max(countLang.values()));
		for(String key: countLang.keySet()) {
			Integer value= countLang.get(key);
			if(value == maxVal) {
				maxCntKey = key;
			}
		}
	}
	
	//2. �������� ����Ƚ��
	public void browserCnt(String fileContext) {
		for(int i=0; i<browserList.length; i++) {
			if(fileContext.contains(browserList[i])) {
				browserCnt[i]++;
				browser.put(browserList[i], browserCnt[i]);
			}
		}
		browserAnalyze();
	}
	
	//2. �������� % ���
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
	//4.�ִ� ���ð�
	public void hourCount(String fileContext) {
		//�α� �ð����� �迭�� ����
		int hour[]= {Integer.parseInt(fileContext.substring(fileContext.lastIndexOf("[")+1, fileContext.lastIndexOf("]")).substring(11,13))};
		int[] index= {};
		int max=Integer.MIN_VALUE;
		
		//�ִ� �ð� ���ϱ�
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
	//3.5.6. ���� ���� Ƚ�����ϱ�
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
	
	//7 ������ �� �� �ִ�Ű ���ϱ�
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
		System.out.println("�ִ� Ű: "+maxCntKeyFromInput);
		System.out.println(cntFromInput.get(maxCntKeyFromInput)+"ȸ");
		
	}

	//getter
	//ù��° �Էµ� ��
	public int getFirst() {
		return first;
	}
	
	//��������
	public int getLast() {
		return last;
	}

	//�ִ� ���ð�
	public int getMode() {
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
	
	//�� ����
	public int getLineCnt() {
		return lineCnt;
	}

	public double getCode403pct() {
		return code403pct;
	}

	public double getCode500pct() {
		return code500pct;
	}

	//�������� �ۼ�Ʈ����Ʈ
	public double[] getBrowserPercent() {
		return browserPercent;
	}
	//��û���� ó������ ������
	public Map<String, Integer> getCntFromInput() {
		return cntFromInput;
	}
	
	//�ִ�� �Ҹ� Ű
	public String getMaxCntKey() {
		return maxCntKey;
	}

	//���ð����� �ִ�� �Ҹ� Ű
	public String getMaxCntKeyFromInput() {
		return maxCntKeyFromInput;
	}
	
	
	//getters
	
	
}//class



