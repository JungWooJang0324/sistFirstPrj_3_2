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
		//REPORT ��ư�� ������ ��
		if(ae.getSource()==ld.getJbtnReport()) {
			//���̵� root�� �����޼��� ����
			if(ld.getGroa().equals("������"))
				JOptionPane.showMessageDialog(ld,"������ ������ �� �ִ� ������ ����");
			}else {
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
		//LINE ��ư�� ������ ��
		if(ae.getSource()==ld.getJbtnLine()) {
			first=Integer.parseInt(ld.getJtxfFir().getText());
			last=Integer.parseInt(ld.getJtxfLast().getText());

		}//if
		}//actionPerformed

}//class
