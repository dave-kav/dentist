/**
 * Dave Kavanagh
 * R00013469
 */
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class CreateGUI extends JFrame {

	private static JFrame frame;
	private static JTabbedPane tp, patientTP, procedureTP, paymentTP, reportTP;
	public static MyController controller = new MyController();

	public CreateGUI () {
		this.createMainDisplay();
	}

	public static void destroy() {
		frame.dispose();
	}
	
	//creates basic frame, calls methods to create inner panels and components
	private void createMainDisplay() {

		JFrame.setDefaultLookAndFeelDecorated(true);							//gives alternative theme to panel visuals
		frame 				= 	new JFrame 	("Dentist Administration System");
		frame.setDefaultCloseOperation		(JFrame.DO_NOTHING_ON_CLOSE);		//prevents system exiting when x is clicked
		frame.addWindowListener(new WindowAdapter() {							//this line and following inner class implements save option when x clicked
			public void windowClosing (WindowEvent event) {
				try {
					controller.safeExitOption(controller);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.setSize					(390,430);
		
		tp 					= 	new JTabbedPane();
		//creates tabs
		patientTP 			= 	new JTabbedPane();
		procedureTP 		=  	new JTabbedPane();
		paymentTP 			= 	new JTabbedPane();
		reportTP			=	new JTabbedPane();
		tp.addTab				("Patient", patientTP);
		tp.addTab				("Procedures", procedureTP);
		tp.addTab				("Payments", paymentTP);
		tp.addTab				("Reports", reportTP);

		//classes which construct tab contents
		new CreateTabOne		(patientTP, controller, this);
		new CreateTabTwo		(procedureTP, controller, this);
		new CreateTabThree		(paymentTP, controller);
		new CreateTabFour		(reportTP, controller);

		//finalise
		frame.getContentPane().add		(tp); 
		frame.isAlwaysOnTop				();
		frame.setResizable				(false);
		frame.setLocationRelativeTo		(null);
		frame.setVisible				(true);
	}
}