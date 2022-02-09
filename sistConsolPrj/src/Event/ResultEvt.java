package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import View.Result;

public class ResultEvt extends WindowAdapter implements ActionListener {
	private Result r;
	public ResultEvt(Result r) {
		this.r=r;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		r.dispose();
	}
	@Override
	public void windowClosing(WindowEvent e) {
		r.dispose();
	}
	
	

}