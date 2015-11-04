/**
 * Dave Kavanagh
 * R00013469
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateTabOne {

	private JPanel patMainPanel;
	private JPanel patTopPanel;
	private JPanel patMiddlePanel;
	private JPanel patBottom;

	public CreateTabOne(JTabbedPane patientTP, MyController controller, CreateGUI myGUI)
	{		
		//set layout of main tabbed pane
		patientTP.setLayout		(new BoxLayout(patientTP, BoxLayout.Y_AXIS));

		//create panels for 1st tab
		patMainPanel 		= 	 	new JPanel();
		patientTP.add			(patMainPanel);
		patMainPanel.setLayout		(new BoxLayout(patMainPanel, BoxLayout.Y_AXIS));

		//create inner panels
		patTopPanel			=		 new JPanel();
		patTopPanel.setLayout		(new BorderLayout());
		patTopPanel.setMaximumSize	(new Dimension(390,150));
		patMiddlePanel		=		 new JPanel();
		patMiddlePanel.setLayout		(new BorderLayout());
		patMiddlePanel.setMaximumSize(new Dimension(390,150));
		patBottom			=		 new JPanel();
		patBottom.setLayout			(new BorderLayout());

		//add inner panels to main panel
		patMainPanel.add				(patTopPanel);
		patMainPanel.add				(patMiddlePanel);
		patMainPanel.add				(patBottom);	

		//method calls to create contents of panels for tab one
		createTabOneTop				(patTopPanel, controller, myGUI);
		createTabOneMiddle			(patMiddlePanel, controller, myGUI);
		createTabOneBottom			(patBottom, controller, myGUI);		
	}

	private void createTabOneTop(JPanel patTopPanel, MyController controller, CreateGUI myGUI) {
		//create top components for 1st tab
		JLabel fNameL 		= 	new JLabel("First Name:");
		JLabel lNameL 		= 	new JLabel("Last Name:");
		JLabel addressL		= 	new JLabel("Address:");
		JLabel phoneL 		= 	new JLabel("Phone Number:");
		JTextField fName 	= 	new JTextField(24);
		JTextField lName 	= 	new JTextField(24);
		JTextField address	= 	new JTextField(24);
		JTextField phone 	= 	new JTextField(24);
		JButton add			= 	new JButton("Submit");
		JButton reset		= 	new JButton("Reset");
		JPanel labelPanel 	=	new JPanel();
		labelPanel.setLayout	(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		JPanel textFieldPanel =	new JPanel();
		textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel	=	new JPanel();
		buttonPanel.setLayout	(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		//add top components
		labelPanel.add				(fNameL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		labelPanel.add				(lNameL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		labelPanel.add				(addressL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		labelPanel.add				(phoneL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,20)));
		patTopPanel.add				(labelPanel, BorderLayout.WEST);
		textFieldPanel.add			(fName);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		textFieldPanel.add			(lName);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		textFieldPanel.add			(address);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		textFieldPanel.add			(phone);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		patTopPanel.add				(textFieldPanel, BorderLayout.EAST);
		buttonPanel.add				(Box.createRigidArea(new Dimension (100,0)));
		buttonPanel.add				(add);
		buttonPanel.add				(Box.createRigidArea(new Dimension (10,0)));
		buttonPanel.add				(reset);
		patTopPanel.add				(buttonPanel, BorderLayout.SOUTH);
		patTopPanel.setBorder		(BorderFactory.createTitledBorder("Add Patient"));

		//functionality for buttons
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String tFName		=	fName.getText();
				String tLName		=	lName.getText();				
				String tAddress		=	address.getText();
				String tPhone		=	phone.getText();

				//next section adds a patient and automatically pulls up the patient's profile
				if (controller.addPatient(tFName, tLName, tAddress, tPhone, myGUI))
				{
					if(controller.searchPatient((tFName + " " + tLName), "")!=null)
					{
						fName.setText("");
						lName.setText("");
						address.setText("");
						phone.setText("");
					}
				}
			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				fName.setText("");
				lName.setText("");
				address.setText("");
				phone.setText("");
			}
		});
	}

	private void createTabOneMiddle(JPanel patMiddlePanel, MyController controller, CreateGUI myGUI) {
		//create middle components for 1st tab
		JLabel nameSearchL			= 	new JLabel("Patient Name: ");
		JLabel patientNoSearchL		= 	new JLabel("Patient No:");
		JTextField nameSearch 		= 	new JTextField(24);
		JTextField patientNoSearch	= 	new JTextField(24);
		JButton search				= 	new JButton("Submit");
		JButton reset			= 	new JButton("Reset");
		JPanel labelPanel 			=	new JPanel();
		labelPanel.setLayout			(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		JPanel textFieldPanel		=	new JPanel();
		textFieldPanel.setLayout		(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel			=	new JPanel();
		buttonPanel.setLayout			(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		//add middle components
		labelPanel.add				(nameSearchL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		labelPanel.add				(patientNoSearchL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		patMiddlePanel.add			(labelPanel, BorderLayout.WEST);
		textFieldPanel.add			(nameSearch);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		textFieldPanel.add			(patientNoSearch);
		patMiddlePanel.add			(textFieldPanel, BorderLayout.EAST);
		buttonPanel.add				(Box.createRigidArea(new Dimension (100,0)));
		buttonPanel.add				(search);
		buttonPanel.add				(Box.createRigidArea(new Dimension (10,0)));
		buttonPanel.add				(reset);
		patMiddlePanel.add			(buttonPanel, BorderLayout.SOUTH);
		patMiddlePanel.setBorder	(BorderFactory.createTitledBorder("Patient Search"));

		//functionality for buttons
		search.addActionListener	(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				String tempPatName = nameSearch.getText();
				String tempPatNoString = patientNoSearch.getText();

				if (controller.searchPatient(tempPatName, tempPatNoString)!=null)					//calls searchPatient method of MyController class, returns Patient object if found
				{
					Patient myPatient = controller.searchPatient(tempPatName, tempPatNoString);		//copy reference to Patient object found in above if statement 
					new CreatePatientPopUp(myPatient, controller, myGUI);							//calls method to create popup for given patient
					nameSearch.setText("");
					patientNoSearch.setText("");
				}
			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				nameSearch.setText("");
				patientNoSearch.setText("");
			}
		});
	}

	private void createTabOneBottom(JPanel patBottom, MyController controller, CreateGUI myGUI) {
		//create bottom components for 1st tab
		JLabel  click			= 	new JLabel("Click here to view all patient details: ");
		JButton viewAll			=	new JButton("View All");
		JPanel 	labelPanel		=	new JPanel();
		JPanel 	buttonPanel		=	new JPanel();

		//add components
		labelPanel.setLayout		(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		buttonPanel.setLayout		(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		labelPanel.add				(click);
		patBottom.add				(labelPanel, BorderLayout.WEST);
		buttonPanel.add				(viewAll);
		patBottom.add				(buttonPanel, BorderLayout.EAST);
		patBottom.setBorder			(BorderFactory.createTitledBorder("Patient Details"));		

		//functionality 
		viewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String temp = controller.viewAllPatients();		//call to viewAll method of MyController class returns a string
				if (temp!= null)
					new CreateViewAllPopup("All Patients", temp);
			}
		});
	}
}