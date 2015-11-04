package gooey;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TEMP extends JFrame {

	private static JFrame frame;
	private static JTabbedPane tp, patientTP, procedureTP, paymentTP;

	private TEMP () {
		this.createMainDisplay();
	}

	public void createMainDisplay() {
		frame 				= 	new JFrame 	("Dentist Administration System");
		frame.setDefaultCloseOperation	(JFrame.EXIT_ON_CLOSE);
		tp 					= 	new JTabbedPane();
		//creates tabs
		patientTP 			= 	new JTabbedPane();
		procedureTP 		=  	new JTabbedPane();
		paymentTP 			= 	new JTabbedPane();
		tp.addTab				("Patient", patientTP);
		tp.addTab				("Procedures", procedureTP);
		tp.addTab				("Payments", paymentTP);
		//methods which construct tab contents
		createTabOne();
		createTabTwo();
		createTabThree();		
		//finalise
		frame.getContentPane	().add	(tp); 
		frame.pack				();
		frame.setVisible		(true);

	}

	public void createTabOne()
	{		
		//set layout of first tab
		patientTP.setLayout		(new BoxLayout(patientTP, BoxLayout.Y_AXIS));

		//create panels for 1st tab
		JPanel patMain 		= 	 new JPanel();
		patientTP.add			(patMain);
		patMain.setLayout		(new BoxLayout(patMain, BoxLayout.Y_AXIS));
		//create inner panels
		JPanel patTop		=	new JPanel();
		patTop.setLayout		(new BorderLayout());
		patTop.setMaximumSize	(new Dimension(390,150));
		JPanel patMiddle	=	new JPanel();
		patMiddle.setLayout		(new BorderLayout());
		patMiddle.setMaximumSize(new Dimension(390,150));
		JPanel patBottom	=	new JPanel();
		patBottom.setLayout		(new BorderLayout());
		patMain.add				(patTop);
		patMain.add				(patMiddle);
		patMain.add				(patBottom);	

		tabOneTop(patTop);
		tabOneMiddle(patMiddle);
		tabOneBottom(patBottom);		
	}

	public void tabOneTop(JPanel patTop) {
		//create top components for 1st tab
		JLabel nameL 		= 	new JLabel("Patient Name:");
		JLabel addressL		= 	new JLabel("Address:");
		JLabel phoneL 		= 	new JLabel("Phone Number:");
		JTextField name 	= 	new JTextField(25);
		JTextField address	= 	new JTextField(25);
		JTextField phone 	= 	new JTextField(25);
		JButton add			= 	new JButton("Submit");
		JButton reset		= 	new JButton("Reset");
		JPanel topLeft 		=	new JPanel();
		topLeft.setLayout		(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
		JPanel topRight		=	new JPanel();
		topRight.setLayout		(new BoxLayout(topRight, BoxLayout.Y_AXIS));
		JPanel topBottom	=	new JPanel();
		topBottom.setLayout		(new BoxLayout(topBottom, BoxLayout.X_AXIS));
		//add top components
		topLeft.add				(nameL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		topLeft.add				(addressL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		topLeft.add				(phoneL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,20)));
		patTop.add				(topLeft, BorderLayout.WEST);
		topRight.add			(name);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		topRight.add			(address);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		topRight.add			(phone);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		patTop.add				(topRight, BorderLayout.EAST);
		topBottom.add			(Box.createRigidArea(new Dimension (100,0)));
		topBottom.add			(add);
		topBottom.add			(Box.createRigidArea(new Dimension (10,0)));
		topBottom.add			(reset);
		patTop.add				(topBottom, BorderLayout.SOUTH);
		patTop.setBorder		(BorderFactory.createTitledBorder("Add Patient"));
	}

	public void tabOneMiddle(JPanel patMiddle) {
		//create middle components for 1st tab
		JLabel nameSearchL			= 	new JLabel("Patient Name: ");
		JLabel patientNoSearchL		= 	new JLabel("Patient No:");
		JTextField nameSearch 		= 	new JTextField(25);
		JTextField patientNoSearch	= 	new JTextField(25);
		JButton search				= 	new JButton("Submit");
		JButton searchReset			= 	new JButton("Reset");
		JPanel middleLeft 			=	new JPanel();
		middleLeft.setLayout			(new BoxLayout(middleLeft, BoxLayout.Y_AXIS));
		JPanel middleRight			=	new JPanel();
		middleRight.setLayout			(new BoxLayout(middleRight, BoxLayout.Y_AXIS));
		JPanel middleBottom			=	new JPanel();
		middleBottom.setLayout			(new BoxLayout(middleBottom, BoxLayout.X_AXIS));
		//add middle components
		middleLeft.add				(nameSearchL);
		middleLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		middleLeft.add				(patientNoSearchL);
		middleLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		patMiddle.add				(middleLeft, BorderLayout.WEST);
		middleRight.add				(nameSearch);
		middleRight.add				(Box.createRigidArea(new Dimension (0,10)));
		middleRight.add				(patientNoSearch);
		patMiddle.add				(middleRight, BorderLayout.EAST);
		middleBottom.add			(Box.createRigidArea(new Dimension (100,0)));
		middleBottom.add			(search);
		middleBottom.add			(Box.createRigidArea(new Dimension (10,0)));
		middleBottom.add			(searchReset);
		patMiddle.add				(middleBottom, BorderLayout.SOUTH);
		patMiddle.setBorder			(BorderFactory.createTitledBorder("Patient Search"));

	}

	public void tabOneBottom(JPanel patBottom) {
		//create bottom components for 1st tab
		JLabel click			= 	new JLabel("Click here to view all patient details: ");
		JButton viewAll			=	new JButton("View All");
		JPanel bottomLeft		=	new JPanel();
		JPanel bottomRight		=	new JPanel();
		//add components
		bottomLeft.setLayout		(new BoxLayout(bottomLeft, BoxLayout.X_AXIS));
		bottomRight.setLayout		(new BoxLayout(bottomRight, BoxLayout.X_AXIS));
		bottomLeft.add				(click);
		patBottom.add				(bottomLeft, BorderLayout.WEST);
		bottomRight.add				(viewAll);
		patBottom.add				(bottomRight, BorderLayout.EAST);
		patBottom.setBorder			(BorderFactory.createTitledBorder("Patient Details"));
	}

	public void createTabTwo() {
		procedureTP.setLayout		(new BoxLayout(procedureTP, BoxLayout.Y_AXIS));
		//create panels for 2nd tab
		JPanel procMain 		= 	new JPanel();
		procedureTP.add				(procMain);
		procMain.setLayout			(new BoxLayout(procMain, BoxLayout.Y_AXIS));
		//create inner panels
		JPanel procTop			=	new JPanel();
		procTop.setLayout			(new BorderLayout());
		procTop.setMaximumSize		(new Dimension(390,150));
		JPanel procMiddle		=	new JPanel();
		procMiddle.setLayout		(new BorderLayout());
		procMiddle.setMaximumSize	(new Dimension(390,150));
		JPanel procBottom		=	new JPanel();
		procBottom.setLayout		(new BorderLayout());
		procMain.add				(procTop);
		procMain.add				(procMiddle);
		procMain.add				(procBottom);	
		procMain.add				(new JPanel());

		tabTwoTop(procTop);
		tabTwoMiddle(procMiddle);
		tabTwoBottom(procBottom);
	}

	public void tabTwoTop(JPanel procTop) {
		JLabel procNameL 	= 	new JLabel("Procedure Name:");
		JLabel procPriceL	= 	new JLabel("Price:");
		JTextField procName	= 	new JTextField(24);
		JTextField procPrice= 	new JTextField(24);
		JButton addProc			= 	new JButton("Submit");
		JButton procReset		= 	new JButton("Reset");
		JPanel topLeft 		=	new JPanel();
		topLeft.setLayout		(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
		JPanel topRight		=	new JPanel();
		topRight.setLayout		(new BoxLayout(topRight, BoxLayout.Y_AXIS));
		JPanel topBottom	=	new JPanel();
		topBottom.setLayout		(new BoxLayout(topBottom, BoxLayout.X_AXIS));
		//add top components
		topLeft.add				(procNameL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		topLeft.add				(procPriceL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		procTop.add				(topLeft, BorderLayout.WEST);
		topRight.add			(procName);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		topRight.add			(procPrice);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		procTop.add				(topRight, BorderLayout.EAST);
		topBottom.add			(Box.createRigidArea(new Dimension (100,0)));
		topBottom.add			(addProc);
		topBottom.add			(Box.createRigidArea(new Dimension (10,0)));
		topBottom.add			(procReset);
		procTop.add				(topBottom, BorderLayout.SOUTH);
		procTop.setBorder		(BorderFactory.createTitledBorder("Add Procedure"));
	}

	public void tabTwoMiddle(JPanel procMiddle) {
		JLabel procSearchL			= 	new JLabel("Procedure Name: ");
		JTextField procSearch		= 	new JTextField(24);
		JButton search				= 	new JButton("Search");
		JButton procReset			= 	new JButton("Reset");
		JPanel middleLeft 			=	new JPanel();
		middleLeft.setLayout			(new BoxLayout(middleLeft, BoxLayout.Y_AXIS));
		JPanel middleRight			=	new JPanel();
		middleRight.setLayout			(new BoxLayout(middleRight, BoxLayout.Y_AXIS));
		JPanel middleBottom			=	new JPanel();
		middleBottom.setLayout			(new BoxLayout(middleBottom, BoxLayout.X_AXIS));
		//add middle components
		middleLeft.add				(procSearchL);
		middleLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		procMiddle.add				(middleLeft, BorderLayout.WEST);
		middleRight.add				(procSearch);
		middleRight.add				(Box.createRigidArea(new Dimension (0,10)));
		procMiddle.add				(middleRight, BorderLayout.EAST);
		middleBottom.add			(Box.createRigidArea(new Dimension (100,0)));
		middleBottom.add			(search);
		middleBottom.add			(Box.createRigidArea(new Dimension (10,0)));
		middleBottom.add			(procReset);
		procMiddle.add				(middleBottom, BorderLayout.SOUTH);
		procMiddle.setBorder		(BorderFactory.createTitledBorder("Procedure Search"));
	}

	public void tabTwoBottom(JPanel procBottom) {
		//create bottom components for 1st tab
		JLabel click			= 	new JLabel("Click here to view all procedure details: ");
		JButton viewAll			=	new JButton("View All");
		JPanel bottomLeft		=	new JPanel();
		JPanel bottomRight		=	new JPanel();
		//add components
		bottomLeft.setLayout		(new BoxLayout(bottomLeft, BoxLayout.X_AXIS));
		bottomRight.setLayout		(new BoxLayout(bottomRight, BoxLayout.X_AXIS));
		bottomLeft.add				(click);
		procBottom.add				(bottomLeft, BorderLayout.WEST);
		bottomRight.add				(viewAll);
		procBottom.add				(bottomRight, BorderLayout.EAST);
		procBottom.setBorder		(BorderFactory.createTitledBorder("Procedure Details"));
		procBottom.setMaximumSize	(new Dimension(390,50));
	}

	public void createTabThree() {
		//create panels for 3rd tab
		paymentTP.setLayout		(new BoxLayout(paymentTP, BoxLayout.Y_AXIS));
		JPanel payMain 		= 	new JPanel();
		paymentTP.add			(payMain);
	
		payMain.setLayout		(new BoxLayout(payMain, BoxLayout.Y_AXIS));
		//create inner panels
		JPanel payTop		=	new JPanel();
		JPanel payBottom	=	new JPanel();
		payMain.add				(payTop);
		payMain.add				(payBottom);
			
		tabThreeTop(payTop);
	}

	public void tabThreeTop(JPanel payTop) {
		JLabel payNameL 		= 	new JLabel("Patient Name:");
		JLabel payProcL			= 	new JLabel("Procedure:");
		JLabel payPriceL 		= 	new JLabel("Price:");
		JTextField payName 		= 	new JTextField(24);
		JTextField payProc		= 	new JTextField(24);
		JTextField payPrice		= 	new JTextField(24);
		JButton add				= 	new JButton("Submit");
		JButton reset			= 	new JButton("Reset");
		JPanel topLeft 			=	new JPanel();
		topLeft.setLayout		(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
		JPanel topRight			=	new JPanel();
		topRight.setLayout		(new BoxLayout(topRight, BoxLayout.Y_AXIS));
		JPanel topBottom		=	new JPanel();
		topBottom.setLayout		(new BoxLayout(topBottom, BoxLayout.X_AXIS));
		//add top components
		topLeft.add				(payNameL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		topLeft.add				(payProcL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,19)));
		topLeft.add				(payPriceL);
		topLeft.add				(Box.createRigidArea(new Dimension (0,20)));
		payTop.add				(topLeft, BorderLayout.WEST);
		topRight.add			(payName);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		topRight.add			(payProc);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		topRight.add			(payPrice);
		topRight.add			(Box.createRigidArea(new Dimension (0,10)));
		payTop.add				(topRight, BorderLayout.EAST);
		topBottom.add			(Box.createRigidArea(new Dimension (100,0)));
		topBottom.add			(add);
		topBottom.add			(Box.createRigidArea(new Dimension (10,0)));
		topBottom.add			(reset);
		payTop.add				(topBottom, BorderLayout.SOUTH);
		payTop.setBorder		(BorderFactory.createTitledBorder("Add Payment"));
	}

	public static void main(String[] args) {
		new TEMP						();
		frame.setVisible				(true);
		frame.setSize					(395,400);
		frame.isAlwaysOnTop				();
		frame.setResizable				(false);
		frame.setLocationRelativeTo		(null);
		frame.setDefaultCloseOperation	(JFrame.EXIT_ON_CLOSE);
	}
}
