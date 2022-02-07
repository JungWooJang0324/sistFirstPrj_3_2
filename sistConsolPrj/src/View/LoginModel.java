package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		super("·Î±×ÀÎ Ã¢");
		ImageIcon icon = new ImageIcon("E:\\dev\\workspace\\sistConsolPrj\\src\\imgs\\1.PNG");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		background.setLayout(null);
		
		JLabel idLabel = new JLabel("I  D  : ");
		JLabel pwLabel = new JLabel("PW  : ");
		
		id = new JTextField();
		pw = new JPasswordField();
		loginBtn = new JButton("L O G I N");
		loginBtn.setBackground(Color.white);
		
		loginBtn.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				loginBtn.setBackground(Color.black);
				loginBtn.setForeground(Color.white);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				loginBtn.setBackground(Color.white);
				loginBtn.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		font = new Font("¸¼Àº °íµñ", Font.BOLD, 35);
		idLabel.setFont(font);
		pwLabel.setFont(font);
		font = new Font("¸¼Àº °íµñ", Font.PLAIN, 30);
		id.setFont(font);
		pw.setFont(font);
		font = new Font("¸¼Àº °íµñ", Font.BOLD, 25);
		loginBtn.setFont(font);
		
		id.setOpaque(false);
		pw.setOpaque(false);
		
				
		//·Î±×ÀÎ ÀÌº¥Æ® Ã³¸®
		LoginEvent loginEvt = new LoginEvent(this);
		addWindowListener(loginEvt);
		loginBtn.addActionListener(loginEvt);
		
		//·Î±×ÀÎ ¿£ÅÍÃ³¸®
		id.registerKeyboardAction(loginEvt, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
		pw.registerKeyboardAction(loginEvt, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);

		idLabel.setBounds(50, 120, 130, 50);
		id.setBounds(200, 120, 200, 50);
		
		pwLabel.setBounds(45, 190, 150, 50);
		pw.setBounds(200, 190, 200,50);
		
		loginBtn.setBounds(250, 270, 150, 50);

		background.add(idLabel);
		background.add(id);
		background.add(pwLabel);
		background.add(pw);
		background.add(loginBtn);
		
		add(background);

		
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
