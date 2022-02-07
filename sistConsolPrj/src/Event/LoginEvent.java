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
			//�Էµ� id, pw����������
			String pressId = loginModel.getId().getText();
			String pressPw = new String(loginModel.getPw().getPassword()); 

			//�⺻ 
			if(pressId.equals("admin")&& pressPw.equals("1234")) {
				JOptionPane.showMessageDialog(loginModel, "�α��� ����!");
				this.rootOrAd = "�Ϲ�";
				System.out.println(getRootOrAd());
				new LogDialog(loginModel, this);
			}
			else if(pressId.equals("administrator") && pressPw.equals("12345")) {
				JOptionPane.showMessageDialog(loginModel, "�α��� ����!");
				this.rootOrAd  = "�Ϲ�";				
				new LogDialog(loginModel, this);
			} 
			//������ ����
			else if(pressId.equals("root") && pressPw.equals("1111")) {
				JOptionPane.showMessageDialog(loginModel, "�α��� ����!");
				this.rootOrAd  = "������";
				new LogDialog(loginModel, this);
			//�α��� ����
			}else {
				JOptionPane.showMessageDialog(loginModel, "�α��� ����!");	
			}
		}
				
	}
	
	public String getRootOrAd() {
		return rootOrAd;
	
	}
}
