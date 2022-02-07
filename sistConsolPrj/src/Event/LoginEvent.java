package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import View.LogDialog;
import View.LoginModel;

public class LoginEvent extends WindowAdapter implements ActionListener{
	private LoginModel loginModel;
	private String rootOrAd;
	
	public LoginEvent(LoginModel loginModel) {
		this.loginModel = loginModel;
	}
	
	@Override
	public void windowClosing(WindowEvent we) {		
		loginModel.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == loginModel.getLoginBtn() || ae.getActionCommand() == "login") {
			//입력된 id, pw값가져오기
			String pressId = loginModel.getId().getText();
			String pressPw = new String(loginModel.getPw().getPassword()); 

			//기본 
			if(pressId.equals("admin")&& pressPw.equals("1234")) {
				JOptionPane.showMessageDialog(loginModel, "로그인 성공!");
				this.rootOrAd = "일반";
				System.out.println(getRootOrAd());
				new LogDialog(loginModel, this);
			}
			else if(pressId.equals("administrator") && pressPw.equals("12345")) {
				JOptionPane.showMessageDialog(loginModel, "로그인 성공!");
				this.rootOrAd  = "일반";				
				new LogDialog(loginModel, this);
			} 
			//관리자 계정
			else if(pressId.equals("root") && pressPw.equals("1111")) {
				JOptionPane.showMessageDialog(loginModel, "로그인 성공!");
				this.rootOrAd  = "관리자";
				new LogDialog(loginModel, this);
			//로그인 실패
			}else {
				JOptionPane.showMessageDialog(loginModel, "로그인 실패!");	
			}
		}
				
	}
	
	public String getRootOrAd() {
		return rootOrAd;
	
	}
}
