package View;

import javax.swing.JDialog;

import Event.DialogEvent;

@SuppressWarnings("serial")
public class testModel extends JDialog{
	private DialogEvent  de;
	public testModel(DialogEvent de) {
		this.de = de;
		//1번 출력
		System.out.println("최다키 : "+de.getMaxCntKey());
		
		//2번출력
		for(int i=0; i<de.getBrowserPercent().length;i++) {
			System.out.printf("브라우저:%s 퍼센트: %.2f\n",de.getBrowserList()[i],de.getBrowserPercent()[i]);
		}
		
		//3번 출력
		System.out.println("200error : "+de.getCode200());
		
		//4번 출력
		System.out.println("최다시간: "+de.getMode());
		System.out.println("횟수: "+de.getHourCnt().get(de.getMode()));
		
		//5,6번
		System.out.println("403error :"+de.getCode403());
		System.out.println("500error :"+de.getCode500());
		
		//7번
		System.out.println("선택된 줄의 최다키 : "+de.getMaxCntKeyFromInput());
		System.out.println(de.getCntFromInput().get(de.getMaxCntKeyFromInput())+"회");
		
		
		}
}
