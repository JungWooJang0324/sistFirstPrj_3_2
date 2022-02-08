package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Result;

public class ResultEvt implements ActionListener {
	private Result r;
	public ResultEvt(Result r) {
		this.r=r;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 종료버튼
		r.dispose();

	}

}