package View;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Event.DialogEvent;

@SuppressWarnings("serial")
public class testModel extends JDialog{
	private DialogEvent  de;
	
	public testModel(DialogEvent de) {
		this.de = de;
		
		//1¹ø
		System.out.println(de.getMaxCntKey());
		
		//2¹øÃâ·Â
		for(int i=0; i<de.getBrowserPercent().length;i++) {
			System.out.printf("ºê¶ó¿ìÀú:%s ÆÛ¼¾Æ®: %.2f\n",de.getBrowserList()[i],de.getBrowserPercent()[i]);
		}
		
		//3¹ø Ãâ·Â
		System.out.println("200error : "+de.getCode200());
		
		//4¹ø Ãâ·Â
		System.out.println("ÃÖ´Ù½Ã°£: "+de.getMode());
		System.out.println("È½¼ö: "+de.getHourCnt().get(de.getMode()));
		
		//5,6¹ø
		System.out.println("403error :"+de.getCode403());
		System.out.println("500error :"+de.getCode500());
		System.out.println("500error :"+de.getCode403pct());
		System.out.println("500error :"+de.getCode500pct());
		
<<<<<<< HEAD
=======
		//7ë²ˆ
		System.out.println("ì„ íƒëœ ì¤„ì˜ ìµœë‹¤í‚¤ : "+de.getMaxCntKeyFromInput());
		System.out.println(de.getCntFromInput().get(de.getMaxCntKeyFromInput())+"íšŒ");
>>>>>>> b2fe1a03429ccf9996654ab6b21ad5dc6d25dc1b
		
		//7¹ø
		System.out.println("ÃÖ´ÙÅ° : "+de.getMaxCntKeyFromInput());
		System.out.println(de.getCntFromInput().get(de.getMaxCntKeyFromInput())+"È¸");
		
		JLabel[] jlBrowserList = new JLabel[de.getBrowserList().length];
		for(int i=0; i<de.getBrowserPercent().length;i++) {
			jlBrowserList[i] = new JLabel(de.getBrowserList()[i]+" :" 
											+de.getBrowserCnt()[i]+"È¸ ("
											+String.format("%.2f",de.getBrowserPercent()[i])+"%)");
		}
		
		JPanel jp = new JPanel();
		for(int i=0; i<jlBrowserList.length; i++) {
			jp.add(jlBrowserList[i]);
		}
		
		add(jp);
		jp.setBounds(10,50,400, 500);
		setLayout(null);
		setSize(500,500);
		setVisible(true);
		
		}
}
