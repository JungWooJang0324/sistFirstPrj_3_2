package Login;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LoginModel extends JFrame{

	public LoginModel() {
		super("로그인 창");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		LoginModel lm = new LoginModel();
	}
	
}
