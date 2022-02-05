package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import LogDialog.LogDialog;


public class DialogEvent extends WindowAdapter implements ActionListener {
	private LogDialog ld;
	public DialogEvent(LogDialog ld) {
		this.ld=ld;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==ld.getJbtnReport()) {
//			if()
		}
	}

}
