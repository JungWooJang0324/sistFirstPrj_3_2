package View;

import javax.swing.JDialog;

import Event.LoginEvent;

@SuppressWarnings("serial")
public class testModel extends JDialog{
	
	public testModel(LoginModel lm, LoginEvent le) {
		super(lm, "test", true);
		
		System.out.println(le.getRootOrAd()+"계정입니다.");
		
		setBounds(lm.getX()+100, lm.getY()+100, 500,450);
		setVisible(true);
	}
}
