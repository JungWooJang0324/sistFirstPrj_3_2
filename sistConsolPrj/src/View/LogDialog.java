package View;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

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
		jtxfFir.registerKeyboardAction(de, "firLastInput", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);
		jtxfLast.registerKeyboardAction(de, "firLastInput", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_FOCUSED);

		jbtnView.addActionListener(de);
		jbtnReport.addActionListener(de);
		jbtnLine.addActionListener(de);
		addWindowListener(de);
		
		jbtnView.setBounds(40,250,120,50);
		jbtnReport.setBounds(170,250,120,50);
		jbtnLine.setBounds(40,160,250,50);
		jlblFir.setBounds(70,50,30,30);
		jlblLast.setBounds(70,100,30,30);
		jtxfFir.setBounds(130,50,150,30);
		jtxfLast.setBounds(130,100,150,30);
		
		setBounds(lm.getX()+100, lm.getY()+100, 350,400);
		setVisible(true);
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
