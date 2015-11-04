package gooey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MyFrame extends JFrame {

	public MyFrame() {	
		initUI();
	}

	public void initUI() {
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		createLayout(quitButton);
		createMenuBar();
		
		setTitle("Patient Management System");
		setSize(600,500);
		setLocationRelativeTo(null);	//center on screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createLayout(JComponent...arg)	{
		Container pane=getContentPane();
		GroupLayout g1=new GroupLayout(pane);
		pane.setLayout(g1);
		
		g1.setAutoCreateContainerGaps(true);	//creates spaces between buttons and side of window
		
		g1.setHorizontalGroup(g1.createSequentialGroup().addComponent(arg[0]));
		g1.setVerticalGroup(g1.createSequentialGroup().addComponent(arg[0]));
	}
	
	private void createMenuBar() {
		JMenuBar menuBar=new JMenuBar();
		JMenu file=new JMenu();
		JMenuItem eMenuItem=new JMenuItem("Exit");
		eMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		file.add(eMenuItem);
		menuBar.add(file);
		
		setJMenuBar(menuBar);
	}

	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {	//prevents hanging
			@Override
			public void run() {
				MyFrame frame = new MyFrame();
				frame.setVisible(true);
			}
		});
	}

}
