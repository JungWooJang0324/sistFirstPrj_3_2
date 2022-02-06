package View;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Event.LoginEvent;
import Event.DialogEvent;

@SuppressWarnings("serial")
public class LogDialog extends JDialog {
	private JButton jbtnView,jbtnReport,jbtnLine;
	private JTextField jtxfFir,jtxfLast;
	private JLabel jlblFir,jlblLast;
	private String groa;
	public LogDialog(LoginModel lm,LoginEvent le) {
		super(lm,"LogDialog",true);
		
		JOptionPane.showMessageDialog(lm,le.getRootOrAd()+"계정입니다.");
		groa=le.getRootOrAd();
		
		jbtnView=new JButton("V I E W");
		jbtnReport=new JButton("R E P O R T");
		jbtnLine=new JButton("L I N E");
		
		jtxfFir=new JTextField(10);
		jtxfLast=new JTextField(10);
		
		jlblFir=new JLabel("First");
		jlblLast=new JLabel("Last");
		setLayout(null);
		
		add(jbtnView);
		add(jbtnReport);
		add(jlblFir);
		add(jlblLast);
		add(jtxfFir);
		add(jtxfLast);
		add(jtxfLast);
		add(jbtnLine);
		
		DialogEvent de=new DialogEvent(this);
		jbtnView.addActionListener(de);
		jbtnReport.addActionListener(de);
		jbtnLine.addActionListener(de);
		
		jbtnView.setBounds(40,250,100,50);
		jbtnReport.setBounds(190,250,100,50);
		jbtnLine.setBounds(40,160,250,50);
		jlblFir.setBounds(70,50,30,30);
		jlblLast.setBounds(70,100,30,30);
		jtxfFir.setBounds(130,50,150,30);
		jtxfLast.setBounds(130,100,150,30);
		
		setBounds(lm.getX()+100, lm.getY()+100, 350,400);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public JButton getJbtnView() {
		return jbtnView;
	}

	public JButton getJbtnReport() {
		return jbtnReport;
	}

	public JButton getJbtnLine() {
		return jbtnLine;
	}

	public JTextField getJtxfFir() {
		return jtxfFir;
	}

	public JTextField getJtxfLast() {
		return jtxfLast;
	}

	public String getGroa() {
		return groa;
	}

}
