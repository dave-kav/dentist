/**
 * Dave Kavanagh
 * R00013469
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class CreateTabThree {

	private JPanel payMain;
	private JPanel payTop;
	private JPanel payBottom;

	public CreateTabThree(JTabbedPane paymentTP, MyController controller) {
		//create panels for 3rd tab
		paymentTP.setLayout	(new BoxLayout(paymentTP, BoxLayout.Y_AXIS));
		payMain 		= 		new JPanel();
		paymentTP.add			(payMain);
		payMain.setLayout		(new BoxLayout(payMain, BoxLayout.Y_AXIS));

		//create inner panels
		payTop			=		new JPanel();
		payTop.setLayout		(new BorderLayout());
		payTop			=		new JPanel();
		payBottom		=		new JPanel();
		payMain.add			(payTop);
		payMain.add			(payBottom);

		//method call to create tab three contents
		tabThreeTop(payTop, controller);

	}

	private void tabThreeTop(JPanel payMiddle, MyController controller) {
		//set colour of dropdown
		UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
		UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.LIGHT_GRAY));
		UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
		//create dropdown components
		JPanel dropDownLabelPanel	=	new JPanel();
		dropDownLabelPanel.setLayout	(new BoxLayout(dropDownLabelPanel, BoxLayout.X_AXIS));
		JLabel names				=	new JLabel("Patients:");
		dropDownLabelPanel.add			(names);
		dropDownLabelPanel.add			(Box.createRigidArea(new Dimension(28,0)));
		JPanel dropDownPanel		=	new JPanel();
		dropDownPanel.setLayout		(new BorderLayout());
		dropDownPanel.add				(dropDownLabelPanel, BorderLayout.WEST);
		JPanel comboBoxPanel		=	new JPanel();
		String tempPatientNames	=	controller.getPatientNames();
		String [] choices			=	tempPatientNames.split(";");
		JComboBox<String> patientNames=new JComboBox<String>(choices);
		patientNames.setPreferredSize	(new Dimension(257, 25));
		comboBoxPanel.add				(patientNames);
		dropDownPanel.add				(patientNames, BorderLayout.EAST);
		payTop.add						(dropDownPanel, BorderLayout.NORTH);

		//create text field components
		JLabel dateL			= 	new JLabel("Date:");
		JLabel dateFormatL		= 	new JLabel("(dd/mm/yyyy)");
		JLabel amountL 		= 	new JLabel("Amount:");
		JTextField date		= 	new JTextField(23);
		JTextField amount		= 	new JTextField(23);
		JButton add			= 	new JButton("Submit");
		JButton reset			= 	new JButton("Reset");
		JPanel bottomPanel		=	new JPanel();
		bottomPanel.setLayout		(new BorderLayout());
		JPanel textFieldLabels	=	new JPanel();
		textFieldLabels.setLayout	(new BoxLayout(textFieldLabels, BoxLayout.Y_AXIS));
		JPanel textFieldPanel	=	new JPanel();
		textFieldPanel.setLayout	(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel		=	new JPanel();
		buttonPanel.setLayout		(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		//add middle components
		//labels
		textFieldLabels.add			(dateL);
		textFieldLabels.add			(dateFormatL);
		textFieldLabels.add			(Box.createRigidArea(new Dimension (0,8)));
		textFieldLabels.add			(amountL);
		textFieldLabels.add			(Box.createRigidArea(new Dimension (0,20)));
		bottomPanel.add				(textFieldLabels, BorderLayout.WEST);
		//textfields
		textFieldPanel.add				(date);
		textFieldPanel.add				(Box.createRigidArea(new Dimension (0,10)));
		textFieldPanel.add				(amount);
		textFieldPanel.add				(Box.createRigidArea(new Dimension (0,10)));
		bottomPanel.add				(textFieldPanel, BorderLayout.EAST);
		buttonPanel.add				(Box.createRigidArea(new Dimension (10,0)));
		buttonPanel.add				(add);
		buttonPanel.add				(Box.createRigidArea(new Dimension (10,0)));
		buttonPanel.add				(reset);
		payTop.add						(bottomPanel, BorderLayout.SOUTH);
		payTop.setBorder				(BorderFactory.createTitledBorder("Add Payment"));
		payTop.add						(buttonPanel, BorderLayout.SOUTH);

		//functionality for buttons
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String tDate		=	date.getText();
				String tAmount		=	amount.getText();
				int name 			=	patientNames.getSelectedIndex();


				try {
					Patient myPatient  = controller.getPatientList().get(name);
					if (myPatient!=null)
					{
						if (controller.addPatientPayment(myPatient, tDate, tAmount))	//this method checks if the patient requested exists and adds a payment if successful
						{
							date.setText("");
							amount.setText("");
						}
					}
				}
				catch (IndexOutOfBoundsException x) {
					JOptionPane.showMessageDialog(null, "No Patient available to select.");
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