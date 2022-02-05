package View;

import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Event.LoginEvent;

@SuppressWarnings("serial")
public class LoginModel extends JFrame{
	private JTextField id;
	private JPasswordField pw;
	private JButton loginBtn;
	Font font;
	
	public LoginModel() {
		super("로그인 창");
		JLabel idLabel = new JLabel("I  D  : ");
		JLabel pwLabel = new JLabel("PW  : ");
		
		id = new JTextField();
		pw = new JPasswordField();
		loginBtn = new JButton("LOGIN");
		
		add(idLabel);
		add(id);
		add(pwLabel);
		add(pw);
		add(loginBtn);
		
		font = new Font("Aldhabi", Font.BOLD, 45);
		idLabel.setFont(font);
		id.setFont(font);
		pwLabel.setFont(font);
		pw.setFont(font);
		

		font = new Font("Aldhabi", Font.PLAIN, 30);
		loginBtn.setFont(font);
		
		//로그인 이벤트 처리
		LoginEvent loginEvt = new LoginEvent(this);
		addWindowListener(loginEvt);
		loginBtn.addActionListener(loginEvt);
		
		//로그인 엔터처리
		id.registerKeyboardAction(loginEvt, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
		pw.registerKeyboardAction(loginEvt, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);

		setLayout(null);
		idLabel.setBounds(30, 100, 130, 50);
		id.setBounds(200, 100, 200, 60);
		
		pwLabel.setBounds(25, 170, 150, 50);
		pw.setBounds(200, 170, 200,60);
		
		loginBtn.setBounds(250, 270, 150, 50);

		
		setSize(500, 400);
		setVisible(true);
	}
	
	
	
	public JTextField getId() {
		return id;
	}

	public JPasswordField getPw() {
		return pw;
	}


	public JButton getLoginBtn() {
		return loginBtn;
	}



}
