package View;

import javax.swing.JDialog;

import Event.DialogEvent;

@SuppressWarnings("serial")
public class testModel extends JDialog{
	private DialogEvent  de;
	public testModel(DialogEvent de) {
		this.de = de;
		
		//2�����
		for(int i=0; i<de.getBrowserPercent().length;i++) {
			System.out.printf("������:%s �ۼ�Ʈ: %.2f\n",de.getBrowserList()[i],de.getBrowserPercent()[i]);
		}
		
		//3�� ���
		System.out.println("200error : "+de.getCode200());
		
		//4�� ���
		System.out.println("�ִٽð�: "+de.getMode());
		System.out.println("Ƚ��: "+de.getHourCnt().get(de.getMode()));
		
		//5,6��
		System.out.println("403error :"+de.getCode403());
		System.out.println("500error :"+de.getCode500());
		
		//7��
		System.out.println("�ִ�Ű : "+de.getMaxCntKey());
		System.out.println(de.getCntFromInput().get(de.getMaxCntKeyFromInput())+"ȸ");
		
		
		}
}
