package View;


import java.awt.FileDialog;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Event.DialogEvent;

@SuppressWarnings("serial")
public class Result extends JDialog {
	private DialogEvent  de;
	private LogDialog ld;
	
	public Result(DialogEvent de ,LogDialog ld) {
		super(ld,"자식창",true);
		
		this.de = de;
		FileDialog fd = new FileDialog(ld, "log파일 선택", FileDialog.LOAD);
		
	
		
		JLabel jlblLine = new JLabel("==================================================");
		JLabel jlblLine2 = new JLabel("==================================================");
		JLabel jlblFileName=new JLabel("파일제목 :"+de.getFileName());
		
		
		JLabel maxCntKey = new JLabel("1. 최다키 : " + de.getMaxCntKey());

		SimpleDateFormat sfm=new SimpleDateFormat("yyyy:MM:dd: hh:mm a");
		Date date=new Date();
		String fmt=sfm.format(date);
		JLabel createTime = new JLabel("생성 일시: "+fmt);
		
		JLabel browserCnt= new JLabel("2. 브라우저 횟수(%) :");
		JPanel browserListpnl= new JPanel();

		String msg="";
		JLabel[] jlBrowserList = new JLabel[de.getBrowserList().length];
		for (int i = 0; i < de.getBrowserPercent().length; i++) {
			 jlBrowserList[i] = new JLabel(de.getBrowserList()[i]+" :" +de.getBrowserCnt()[i]+"회 ("+String.format("%.2f",de.getBrowserPercent()[i])+"%)");
		}//end for
		for(int i=0; i<jlBrowserList.length;i++) {
			browserListpnl.add(jlBrowserList[i]);
		}
		
		JLabel successnFail = new JLabel("3. [200] 성공 발생횟수 : " + de.getCode200()+"회"+"  |   [400] 실패횟수 : "+de.getCode404()+"회");
		JLabel maxHour = new JLabel("4. 최다 시간 (횟수): " + de.getMode()+"시"+" (" + de.getHourCnt().get(de.getMode())+"회)");
		JLabel code403 = new JLabel("5. 403error 발생횟수(%) :" + de.getCode403()+"회 ("+de.getCode403pct()+"%)");
		JLabel code500 = new JLabel("6. 500error 발생횟수(%) :" + de.getCode500()+"회 ("+de.getCode500pct()+"%)");
		JLabel inputMax = new JLabel("7. 선택된 줄의 최다키(횟수) : " + de.getMaxCntKeyFromInput()+" ("+de.getCntFromInput().get(de.getMaxCntKeyFromInput()) + "회)");
		
		setLayout(null);
		add(jlblLine);
		add(createTime);
		add(jlblFileName);
		add(jlblLine2);
		add(maxCntKey);
		add(browserCnt);
		add(browserListpnl);
		add(successnFail);
		add(maxHour);
		add(code403);
		add(code500);
		add( inputMax);

		jlblLine.setBounds(20,5,600,30);
		jlblFileName.setBounds(20, 30, 200, 25);
		createTime.setBounds(200, 17, 200, 50);
		jlblLine2.setBounds(20,45,600,30);
		maxCntKey.setBounds(20, 70, 200, 30);
		browserCnt.setBounds(20, 100, 200, 30);
		browserListpnl.setBounds(60, 130, 170, 100);
		successnFail.setBounds(20, 250, 400, 30);
		maxHour.setBounds(20, 290, 200, 30);
		code403.setBounds(20, 340, 200, 30);
		code500.setBounds(20, 390, 200, 30);
		inputMax.setBounds(20, 420, 400, 30);
		
		setBounds(100, 100, 450, 600);
		setVisible(true);


	}
}
