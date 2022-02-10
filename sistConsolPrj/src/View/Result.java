package View;


import java.awt.FileDialog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Event.DialogEvent;
import Event.ResultEvt;

@SuppressWarnings("serial")
public class Result extends JDialog {
	private DialogEvent  de;
	private LogDialog ld;
	
	
	public Result(DialogEvent de ,LogDialog ld) {
		super(ld,"���â",true);
		
		this.de = de;
		FileDialog fd = new FileDialog(ld, "log���� ����", FileDialog.LOAD);
		
	
		
		JLabel jlblLine = new JLabel("==================================================");
		JLabel jlblLine2 = new JLabel("==================================================");
		JLabel jlblFileName=new JLabel("�������� :"+de.getFileName());
		
		
		JLabel maxCntKey = new JLabel("1. �ִ�Ű : " + de.getMaxCntKey());

		SimpleDateFormat sfm=new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		Date date=new Date();
		String fmt=sfm.format(date);
		JLabel createTime = new JLabel("���� �Ͻ�: "+fmt);
		
		JLabel browserCnt= new JLabel("2. ������ Ƚ��(%) :");
		//������ ���� �ֱ�
		JLabel[] browsers = new JLabel[de.getBrowserList().length];
		JPanel browserJpl = new JPanel();
		Map<String, Integer> browserMap = de.getBrowser();
		Map<String, String> browserPctMap = de.getBrowserPctMap();
		
		Set<String> key = browserMap.keySet();
		Iterator<String> iter = key.iterator();
		String browserName= "";
		int i=0;
		while (iter.hasNext()) {
			browserName= iter.next();
			browsers[i] = new JLabel(browserName+" : "+browserMap.get(browserName)+"�� ("+browserPctMap.get(browserName)+"%)");
			browserJpl.add(browsers[i]);
			i++;
		}
		
		JLabel successnFail = new JLabel("3. [200] ���� �߻�Ƚ�� : " + de.getCode200()+"ȸ"+"  |   [400] ����Ƚ�� : "+de.getCode404()+"ȸ");
		JLabel maxHour = new JLabel("4. �ִ� �ð� (Ƚ��): " + de.getMode()+"��"+" (" + de.getHourCnt().get(de.getMode())+"ȸ)");
		JLabel code403 = new JLabel("5. 403error �߻�Ƚ��(%) :" + de.getCode403()+"ȸ ("+de.getCode403pct()+"%)");
		JLabel code500 = new JLabel("6. 500error �߻�Ƚ��(%) :" + de.getCode500()+"ȸ ("+de.getCode500pct()+"%)");
		JLabel inputMax = new JLabel("7. ���õ� �� "+de.getFirst()+"���� "+de.getLast()+"������ �ִ�Ű(Ƚ��) : " + de.getMaxCntKeyFromInput()+" ("+de.getCntFromInput().get(de.getMaxCntKeyFromInput()) + "ȸ)");
		JButton confirm = new JButton("Ȯ��");
		
		ResultEvt revent = new ResultEvt(this);
		addWindowListener(revent);
		confirm.addActionListener(revent);
		
		setLayout(null);
		add(jlblLine);
		add(createTime);
		add(jlblFileName);
		add(jlblLine2);
		add(maxCntKey);
		add(browserCnt);
		add(browserJpl);
		add(successnFail);
		add(maxHour);
		add(code403);
		add(code500);
		add(inputMax);
		add(confirm);

		jlblLine.setBounds(20,5,600,30);
		jlblFileName.setBounds(20, 30, 200, 25);
		createTime.setBounds(200, 17, 200, 50);
		jlblLine2.setBounds(20,45,600,30);
		maxCntKey.setBounds(20, 70, 200, 30);
		browserCnt.setBounds(20, 100, 200, 30);
		browserJpl.setBounds(60, 130, 170, 120);
		successnFail.setBounds(20, 270, 400, 30);
		maxHour.setBounds(20, 300, 200, 30);
		code403.setBounds(20, 340, 250, 30);
		code500.setBounds(20, 370, 250, 30);
		inputMax.setBounds(20, 400, 400, 30);
		confirm.setBounds(150, 470,100,40);
		
		setBounds(100, 100, 430, 600);
		setVisible(true);


	}
}
