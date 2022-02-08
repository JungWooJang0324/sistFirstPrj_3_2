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
		super(ldl, "결과 출력", true);
		this.de = de;
		JPanel bgr = new JPanel();
		bgr.setLayout(null);

		JButton jbConfirm = new JButton("확인");
		bgr.add(new JLabel("1. 최다 사용 key의 이름과 횟수")).setBounds(80, 50, 200, 50);
		bgr.add(new JLabel(de.getMaxCntKey() + " : " + "번")).setBounds(380, 60, 200, 30);
		bgr.add(new JLabel("2. 브라우저별 접속횟수, 비율")).setBounds(80, 130, 200, 50);
		for(int i=0;i<de.getBrowserPercent().length; i++) {
			bgr.add(new JLabel(String.valueOf(de.getBrowserPercent()[i])));
		}
		bgr.add(new JLabel("3. 서비스 성공(200) 실패(404) 횟수")).setBounds(80, 230, 200, 50);
		bgr.add(new JLabel("성공(200) : " + de.getCode200() + ", 실패(404) : " + de.getCode404())).setBounds(380, 230, 200, 50);
		bgr.add(new JLabel("4. 요청이 가장 많은 시간")).setBounds(80, 310, 200, 30);
		bgr.add(new JLabel(de.getMode() + "시")).setBounds(380	, 310, 200,30);
		bgr.add(new JLabel("5. 비정상 요청 횟수와 비율")).setBounds(80, 360, 200, 30);
//		bgr.add(new JLabel(de.getCode403() +"("+ (de.getCode403() /de.getCode403pct()) +")%"+setBounds(380, 360, 200, 30));
//		bgr.add(new JLabel(de.getCode403() +"("+ (de.getCode403() /de.getCode403pct()) +")%"+setBounds(380, 360, 200, 30));
//		bgr.add(new JLabel("6. 요청에 대한 에러(500)가 발생한 횟수, 비율 구하기"
//				+ "")).setBounds(80, 360, 200, 30);
//		bgr.add(new JLabel(de.getCode500() + " ("+ String.format("%4.2f",de.getCode500() /de.getCode500pct() + "%)")).setBounds(380, 360, 200, 30));
//		
		
		bgr.add(jbConfirm).setBounds(270, 450, 60, 30);
		bgr.add(new JLabel("파일명")).setBounds(40, 5, 100, 50);
	
		setBounds(400, 300, 650, 530);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


}//Result
}
