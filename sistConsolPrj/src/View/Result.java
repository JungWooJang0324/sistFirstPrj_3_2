package View;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Event.DialogEvent;

@SuppressWarnings("serial")
public class Result extends JDialog {
	private DialogEvent  de;
//	public JPanel browserInfo() {
//		
//		JPanel jpBrowser = new JPanel();
//		jpBrowser.setOpaque(false);
//		setLayout(new GridLayout(1, 5));
//		
//		JLabel[] jlBrowser = new JLabel[de.getBrowserList().length];
//		Map<String, Integer> CntFromInput =de.getCntFromInput();
//		
//		StringBuilder content = new StringBuilder();
//		
//		Set<String> key = CntFromInput.keySet();
//		Iterator<String> itKey = key.iterator();
//		String Name = "";
//		int i = 0;
//		while (itKey.hasNext()) {
//			Name = itKey.next();
//
//			content.append(Name).append(" : ").append(CntFromInput.get(Name)).append(" (");
//					
//			jlBrowser[i] = new JLabel(content.toString());
//			jpBrowser.add(jlBrowser[i]);
//			i++;
//			content.delete(0, content.length());
//			
//			
//		}//end while
//		
//		
//		
//		
//		return jpBrowser;
//		
//	}//browserInfo
//	
	public Result(DialogEvent de, LogDialog ldl) {
		super(ldl, "��� ���", true);
		this.de = de;
		JPanel bgr = new JPanel();
		bgr.setLayout(null);

		JButton jbConfirm = new JButton("Ȯ��");
		bgr.add(new JLabel("1. �ִ� ��� key�� �̸��� Ƚ��")).setBounds(80, 50, 200, 50);
		bgr.add(new JLabel(de.getMaxCntKey() + " : " + "��")).setBounds(380, 60, 200, 30);
		bgr.add(new JLabel("2. �������� ����Ƚ��, ����")).setBounds(80, 130, 200, 50);
		for(int i=0;i<de.getBrowserPercent().length; i++) {
			bgr.add(new JLabel(String.valueOf(de.getBrowserPercent()[i])));
		}
		bgr.add(new JLabel("3. ���� ����(200) ����(404) Ƚ��")).setBounds(80, 230, 200, 50);
		bgr.add(new JLabel("����(200) : " + de.getCode200() + ", ����(404) : " + de.getCode404())).setBounds(380, 230, 200, 50);
		bgr.add(new JLabel("4. ��û�� ���� ���� �ð�")).setBounds(80, 310, 200, 30);
		bgr.add(new JLabel(de.getMode() + "��")).setBounds(380	, 310, 200,30);
		bgr.add(new JLabel("5. ������ ��û Ƚ���� ����")).setBounds(80, 360, 200, 30);
//		bgr.add(new JLabel(de.getCode403() +"("+ (de.getCode403() /de.getCode403pct()) +")%"+setBounds(380, 360, 200, 30));
//		bgr.add(new JLabel(de.getCode403() +"("+ (de.getCode403() /de.getCode403pct()) +")%"+setBounds(380, 360, 200, 30));
//		bgr.add(new JLabel("6. ��û�� ���� ����(500)�� �߻��� Ƚ��, ���� ���ϱ�"
//				+ "")).setBounds(80, 360, 200, 30);
//		bgr.add(new JLabel(de.getCode500() + " ("+ String.format("%4.2f",de.getCode500() /de.getCode500pct() + "%)")).setBounds(380, 360, 200, 30));
//		
		
		bgr.add(jbConfirm).setBounds(270, 450, 60, 30);
		bgr.add(new JLabel("���ϸ�")).setBounds(40, 5, 100, 50);
	
		setBounds(400, 300, 650, 530);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


}//Result
}
