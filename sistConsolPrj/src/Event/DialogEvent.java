package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import View.LogDialog;


public class DialogEvent extends WindowAdapter implements ActionListener {
	private LogDialog ld;
	private int first,last;
	
	public DialogEvent(LogDialog ld) {
		this.ld=ld;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		//REPORT 버튼이 눌렸을 때
		if(ae.getSource()==ld.getJbtnReport()) {
			//아이디가 root면 오류메세지 생성
			if(ld.getGroa().equals("관리자"))
				JOptionPane.showMessageDialog(ld,"문서를 생성할 수 있는 권한이 없음");
			}else {
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
		//LINE 버튼이 눌렸을 때
		if(ae.getSource()==ld.getJbtnLine()) {
			first=Integer.parseInt(ld.getJtxfFir().getText());
			last=Integer.parseInt(ld.getJtxfLast().getText());

		}//if
		}//actionPerformed

}//class
