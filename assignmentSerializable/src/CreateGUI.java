/**
 * Dave Kavanagh
 * R00013469
 */
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
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
					JOptionPane.showMessageDialog(null, "An error occurred, system could not exit safely");
				}
			}
		});

		frame.setSize					(390,450);
		createMenuBar();														//create top menu bar of frame 

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

		//finalise frame and display
		frame.getContentPane().add		(tp); 
		frame.isAlwaysOnTop				();
		frame.setResizable				(false);
		frame.setLocationRelativeTo		(null);
		frame.setVisible				(true);
	}

	/**
	 * Create the contents of the top menu bar visible on the frame
	 */
	public void createMenuBar() {
		JMenuBar menuBar 		=	new JMenuBar();															//create bar
		JMenu menu				=	new JMenu("Menu");														//create component									
		frame.setJMenuBar(menuBar);														
		menuBar.add(menu);																					//add component to bar
		
		//the following two sections of code implement save options on the menu
		JMenuItem savePatients = new JMenuItem("Save patient data");										//create menu item
		savePatients.addActionListener(new ActionListener() {												//add anonymous inner class for functionality
			public void actionPerformed(ActionEvent e) {
				final JFileChooser choose = new JFileChooser("Save File");									//create a new file chooser item
				
				int returnVal = choose.showSaveDialog(null);												
				if (returnVal == JFileChooser.APPROVE_OPTION) {												//if user clicks on the ok option
					try {
						controller.patientBackup(choose.getSelectedFile().getAbsolutePath());				//send the path to the backup method in the controller
						JOptionPane.showMessageDialog(null, "File saved successfully");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error: File not saved");
					}
				}
			}
		});
		
		menu.add(savePatients);

		//same as above but for procedures rather than patients
		JMenuItem saveProcedures = new JMenuItem("Save procedure data");
		saveProcedures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser choose = new JFileChooser("Save File");
				
				int returnVal = choose.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						controller.procedureBackup(choose.getSelectedFile().getAbsolutePath());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error: File not saved");
					}
					JOptionPane.showMessageDialog(null, "File saved successfully");
				}
			}
		});

		menu.add(saveProcedures);

		//the next two sections are similar to above but allow the user to load files rather than save
		JMenuItem loadPatients = new JMenuItem("Load patient data from file");
		loadPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser choose = new JFileChooser("Load Patient File");
				int returnVal = choose.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String patientFile = choose.getSelectedFile().getAbsolutePath();
					try {
						MyController.loadPatients(patientFile);
					} catch (FileNotFoundException f) {
						JOptionPane.showMessageDialog(null, "An error occurred");
					}
				}
			}
		});

		menu.add(loadPatients);

		JMenuItem loadProcedures = new JMenuItem("Load procedure data from file");
		loadProcedures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser choose = new JFileChooser("Load Procedure File");
				int returnVal = choose.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String procedureFile = choose.getSelectedFile().getAbsolutePath();
					try {
						MyController.loadProcedures(procedureFile);
					} catch (FileNotFoundException f) {
						JOptionPane.showMessageDialog(null, "An error occurred");
					}
				}
			}
		});

		menu.add(loadProcedures);
		
		//final menu option allows the user to exit the system
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.safeExitOption(controller);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "An error occurred, system could not exit safely");
				}
			}
		});
		
		menu.add(exit);
	}
}