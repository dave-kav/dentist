/**
 * Dave Kavanagh
 * R00013469
 */import java.awt.*;
 import java.awt.event.*;

 import javax.swing.*;
 import javax.swing.plaf.ColorUIResource;

 public class CreatePatientPopUp {

	 private JFrame popupFrame;
	 private JTabbedPane ptp;
	 private JPanel infoTP;
	 private JPanel editTP;
	 private JPanel addProcTP;
	 private JPanel addPayTP;

	 public CreatePatientPopUp(Patient myPatient, MyController controller, CreateGUI myGUI) {
		 //first section creates panels for popup
		 popupFrame 		= 		new JFrame("Patient record for: " + myPatient.getPatientFirstName() + " " + myPatient.getPatientLastName());
		 popupFrame.setSize			(390,300);

		 popupFrame.setDefaultCloseOperation		(JFrame.DO_NOTHING_ON_CLOSE);		//prevents system exiting when x is clicked
		 popupFrame.addWindowListener(new WindowAdapter() {							//this line and following inner class implements save option when x clicked
			 public void windowClosing (WindowEvent event) {
				 popupFrame.dispose();
				 CreateGUI.destroy();
				 new CreateGUI();
			 }
		 });

		 ptp				= 		new JTabbedPane();

		 //creates tabs
		 infoTP 			= 		new JPanel();
		 infoTP.setLayout			(new BoxLayout(infoTP, BoxLayout.Y_AXIS));
		 editTP 			=  		new JPanel();
		 editTP.setLayout			(new BoxLayout(editTP, BoxLayout.Y_AXIS));
		 addProcTP			= 		new JPanel();
		 addProcTP.setLayout		(new BoxLayout(addProcTP, BoxLayout.Y_AXIS));
		 addPayTP 			= 		new JPanel();
		 ptp.addTab					("Patient Info", infoTP);
		 ptp.addTab					("Edit Patient", editTP);
		 ptp.addTab					("Procedures", addProcTP);
		 ptp.addTab					("Payments", addPayTP);

		 //methods which construct tab contents
		 createPatInfoPanel			(myPatient, infoTP, controller, myGUI);
		 createEditPatPanel			(myPatient, editTP, controller, myGUI);
		 createProcPanel			(myPatient, addProcTP, controller, myGUI);	
		 createPayPanel				(myPatient, addPayTP, controller, myGUI);

		 //finalise popup
		 popupFrame.getContentPane			().add	(ptp); 
		 popupFrame.isAlwaysOnTop			();
		 popupFrame.setResizable			(false);
		 popupFrame.setLocationRelativeTo	(null);
		 popupFrame.setVisible				(true);
	 }

	 private void createPatInfoPanel(Patient myPatient, JPanel panel, MyController controller, CreateGUI myGUI) {
		 //create components
		 JPanel topPanel 	= 		new JPanel();
		 topPanel.setLayout			(new BorderLayout());
		 JPanel buttonPanel	=		new JPanel();
		 JTextArea info		=		new JTextArea(5, 50);
		 info.setLineWrap			(false);
		 info.setEditable			(false);
		 info.setVisible			(true);
		 JScrollPane scroll	=		new JScrollPane(info);
		 scroll.setPreferredSize	(new Dimension(350,180));
		 topPanel.add				(scroll, BorderLayout.CENTER);

		 String temp ="Pat. Number\tName\tAddress\tPhone\n======\t======\t======\t======";
		 temp += (myPatient.toString() + "\n");	
		 temp += controller.amountOwed(myPatient);		//this method calls the calcIsPaid method also and returns how much is owed, reduces code

		 info.append			(temp);
		 topPanel.setBorder		(BorderFactory.createTitledBorder("Patient Details"));
		 panel.add				(topPanel, BorderLayout.NORTH);

		 JButton delete		=	new JButton("Delete Patient Record");
		 buttonPanel.add		(delete);
		 panel.add				(buttonPanel, BorderLayout.SOUTH);

		 //functionality for delete button
		 delete.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (controller.deletePatient(myPatient))
				 {
					 popupFrame.dispose();
					 CreateGUI.destroy();
					 new CreateGUI();
				 }
			 }
		 });
	 }

	 private void createEditPatPanel(Patient myPatient, JPanel panel, MyController controller, CreateGUI myGUI) {
		 //create top components for 1st tab
		 JLabel fNameL 		= 	new JLabel("First Name:");
		 JLabel lNameL 		= 	new JLabel("Last Name:");
		 JLabel addressL	= 	new JLabel("Address:");
		 JLabel phoneL 		= 	new JLabel("Phone Number:");
		 JTextField fName 	= 	new JTextField(22);
		 JTextField lName 	= 	new JTextField(22);
		 JTextField address	= 	new JTextField(22);
		 JTextField phone 	= 	new JTextField(22);
		 JButton update		= 	new JButton("Update");
		 JButton reset		= 	new JButton("Reset");
		 JPanel labels 		=	new JPanel();
		 labels.setLayout		(new BoxLayout(labels, BoxLayout.Y_AXIS));
		 JPanel textFieldPanel=	new JPanel();
		 textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		 JPanel buttonPanel	=	new JPanel();
		 buttonPanel.setLayout	(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		 JPanel top			=	new JPanel();
		 top.setLayout			(new BorderLayout());
		 top.setMaximumSize		(new Dimension(370,150));

		 //add top components
		 labels.add				(fNameL);
		 labels.add				(Box.createRigidArea(new Dimension (0,19)));
		 labels.add				(lNameL);
		 labels.add				(Box.createRigidArea(new Dimension (0,19)));
		 labels.add				(addressL);
		 labels.add				(Box.createRigidArea(new Dimension (0,19)));
		 labels.add				(phoneL);
		 labels.add				(Box.createRigidArea(new Dimension (0,20)));
		 top.add				(labels, BorderLayout.WEST);
		 textFieldPanel.add		(fName);
		 textFieldPanel.add		(Box.createRigidArea(new Dimension (0,5)));
		 textFieldPanel.add		(lName);
		 textFieldPanel.add		(Box.createRigidArea(new Dimension (0,5)));
		 textFieldPanel.add		(address);
		 textFieldPanel.add		(Box.createRigidArea(new Dimension (0,5)));
		 textFieldPanel.add		(phone);
		 textFieldPanel.add		(Box.createRigidArea(new Dimension (0,5)));
		 top.add				(textFieldPanel, BorderLayout.EAST);
		 buttonPanel.add		(Box.createRigidArea(new Dimension (100,0)));
		 buttonPanel.add		(update);
		 buttonPanel.add		(Box.createRigidArea(new Dimension (10,0)));
		 buttonPanel.add		(reset);
		 top.add				(buttonPanel, BorderLayout.SOUTH);
		 panel.setBorder		(BorderFactory.createTitledBorder("Edit Patient"));
		 panel.add				(top, BorderLayout.NORTH);

		 //functionality for buttons
		 update.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent event) {
				 String tFName		=	fName.getText();
				 String tLName		=	lName.getText();
				 String tAddress	=	address.getText();
				 String tPhone		=	phone.getText();
				 if (controller.updatePatient(myPatient, tFName, tLName, tAddress, tPhone))	//this method passes variables to relevant setters of Patient object
				 {
					 popupFrame.dispose();
					 new CreatePatientPopUp(myPatient, controller, myGUI);
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

	 private void createProcPanel(Patient myPatient, JPanel panel, MyController controller, CreateGUI myGUI) {
		 //set colour of dropdown menus
		 UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
		 UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
		 UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.LIGHT_GRAY));
		 UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));

		 //create panels
		 JPanel topPanel			= 	new JPanel();
		 topPanel.setLayout				(new BorderLayout());
		 JPanel middlePanel			=	new JPanel();
		 middlePanel.setLayout			(new BorderLayout());
		 JPanel emptyPanel 			=	new JPanel();
		 emptyPanel.setLayout			(new BoxLayout(emptyPanel, BoxLayout.X_AXIS));

		 //add procedure section
		 String tempAdd 				= controller.getProcedureNames();		//this method returns all Procedure names in a String with ';' as the delimiter
		 String[] choices = tempAdd.split(";");								//split the String into an array
		 JComboBox<String> addCb 		= new JComboBox<String>(choices);	//pass the array into a drop box
		 JLabel addProcL				= new JLabel("Select a procedure:");
		 JButton add					= new JButton("Add");

		 //add components
		 topPanel.add(addProcL, BorderLayout.WEST);
		 topPanel.add(addCb, BorderLayout.CENTER);
		 topPanel.add(add, BorderLayout.EAST);

		 //add functionality for drop box/add button
		 add.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index =addCb.getSelectedIndex();
				 if (controller.addPatientProc(myPatient, index))	//adds a procedure to a patients own procedure list
				 {
					 popupFrame.dispose();
					 new CreatePatientPopUp(myPatient, controller, myGUI);
				 }
			 }
		 });

		 // remove procedure section
		 String tempRemove					= controller.getPatientProcedureNames(myPatient);	//returns String containing all of a patients own procedures delimited by ';'
		 String[] removeChoices 			= tempRemove.split(";");							//splits String into an array
		 final JComboBox<String> removeCb 	= new JComboBox<String>(removeChoices);				//pass array into comboBox
		 //		 removeCb.setPreferredSize			(new Dimension(3,20));
		 JLabel removeProcL					= new JLabel("Remove a procedure:");
		 JButton remove						= new JButton("Remove");

		 addCb.setPreferredSize(removeCb.getSize());

		 //add components
		 middlePanel.add(removeProcL, BorderLayout.WEST);
		 middlePanel.add(removeCb, BorderLayout.CENTER);
		 middlePanel.add(remove, BorderLayout.EAST);

		 //functionality for comboBox/remove button
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = removeCb.getSelectedIndex();
				 if (controller.removePatientProc(myPatient, index))		//removes selected procedure from patients own collection
				 {
					 popupFrame.dispose();
					 new CreatePatientPopUp(myPatient, controller, myGUI);
				 }
			 }
		 });

		 //finalise the panel
		 topPanel.setBorder			(BorderFactory.createTitledBorder("Add Procedure"));
		 topPanel.setMaximumSize	(new Dimension(370, 50));
		 middlePanel.setBorder		(BorderFactory.createTitledBorder("Remove Procedure"));
		 middlePanel.setMaximumSize	(new Dimension(370, 50));
		 panel.add					(topPanel);
		 panel.add					(middlePanel);
		 panel.add					(emptyPanel);		
	 }

	 private void createPayPanel(Patient myPatient, JPanel panel, MyController controller, CreateGUI myGUI) {
		 //create top components for 1st tab
		 JLabel dateL 		= 	new JLabel("Date:");
		 JLabel dateFormatL	=	new JLabel("(dd/mm/yyyy)");
		 JLabel amountL		= 	new JLabel("Amount:");
		 JTextField date 	= 	new JTextField(22);
		 JTextField amount	= 	new JTextField(22);
		 JButton add		= 	new JButton("Add");
		 JButton reset		= 	new JButton("Reset");
		 JPanel labelPanel 	=	new JPanel();
		 labelPanel.setLayout	(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		 JPanel textFieldPanel=	new JPanel();
		 textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		 JPanel buttonPanel	=	new JPanel();
		 buttonPanel.setLayout	(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		 JPanel top			=	new JPanel();
		 top.setLayout			(new BorderLayout());
		 top.setMaximumSize		(new Dimension(370,120));

		 //add top components
		 labelPanel.add				(dateL);
		 labelPanel.add				(dateFormatL);
		 labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		 labelPanel.add				(amountL);
		 labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		 top.add					(labelPanel, BorderLayout.WEST);
		 textFieldPanel.add			(date);
		 textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		 textFieldPanel.add			(amount);
		 textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		 top.add					(textFieldPanel, BorderLayout.EAST);
		 buttonPanel.add			(Box.createRigidArea(new Dimension (100,0)));
		 buttonPanel.add			(add);
		 buttonPanel.add			(Box.createRigidArea(new Dimension (10,0)));
		 buttonPanel.add			(reset);
		 top.add					(buttonPanel, BorderLayout.SOUTH);
		 panel.setBorder			(BorderFactory.createTitledBorder("Add Payment"));
		 panel.add					(top, BorderLayout.NORTH);

		 //functionality for buttons
		 add.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent event) {
				 String tDate		=	date.getText();
				 String tamount		=	amount.getText();
				 try {
					 if (controller.addPatientPayment(myPatient, tDate, tamount))	//method which adds a payment to a patients collection
					 {
						 popupFrame.dispose();
						 new CreatePatientPopUp(myPatient, controller, myGUI);
					 }
				 } catch (HeadlessException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }
			 }
		 });

		 reset.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent event) {
				 date.setText("");
				 amount.setText("");
			 }
		 });
	 }	
 }