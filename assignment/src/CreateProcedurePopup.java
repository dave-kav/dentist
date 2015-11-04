/**
 * Dave Kavanagh
 * R00013469
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateProcedurePopup {

	private JFrame procPopupFrame;
	private JPanel infoTP;
	private JPanel editTP;
	
	public CreateProcedurePopup(Procedure myProcedure, MyController controller) {
		//first section creates panels for popup
		procPopupFrame 			= 	new JFrame(myProcedure.getProcName());
		procPopupFrame.setSize			(370,300);
		JTabbedPane ptp			= 	new JTabbedPane();
		//creates tabs
		infoTP 					=	new JPanel();
		infoTP.setLayout			(new BoxLayout(infoTP, BoxLayout.Y_AXIS));
		editTP 					=  	new JPanel();
		editTP.setLayout			(new BoxLayout(editTP, BoxLayout.Y_AXIS));
		ptp.addTab					("Procedure Info", infoTP);
		ptp.addTab					("Edit Procedure", editTP);

		//methods which construct tab contents
		createProcInfoPanel			(myProcedure, infoTP, controller);
		createEditProcPanel			(myProcedure, editTP, controller);		

		//finalise popup
		procPopupFrame.getContentPane			().add	(ptp); 
		procPopupFrame.isAlwaysOnTop			();
		procPopupFrame.setResizable				(false);
		procPopupFrame.setLocationRelativeTo	(null);
		procPopupFrame.setVisible				(true);
	}

	private void createProcInfoPanel (final Procedure myProcedure, JPanel infoTP, final MyController controller) {
		//create components
		JPanel topPanel 	= 		new JPanel();
		topPanel.setLayout			(new BorderLayout());
		JPanel buttonPanel	=		new JPanel();
		JTextArea info		=		new JTextArea(5, 50);
		info.setLineWrap			(false);
		info.setEditable			(false);
		info.setVisible				(true);
		JScrollPane scroll	=		new JScrollPane(info);
		scroll.setPreferredSize		(new Dimension(350,180));
		topPanel.add				(scroll, BorderLayout.CENTER);
		info.setText				("Proc. Number\tName\tCost\n======\t======\t======" + myProcedure.toString());
		topPanel.setBorder			(BorderFactory.createTitledBorder("Patient Details"));
		infoTP.add					(topPanel, BorderLayout.NORTH);
		
		JButton delete		=		new JButton("Delete Procedure Record");
		buttonPanel.add				(delete);
		infoTP.add					(buttonPanel, BorderLayout.SOUTH);

		//functionality for delete button
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if(controller.deleteProcedure(myProcedure))	//deletes the selected procedure
					procPopupFrame.dispose();
			}
		});
	}

	private void createEditProcPanel (final Procedure myProcedure, JPanel panel, final MyController controller) {
		//create top components for 1st tab
		JLabel nameL 			= 	new JLabel("Procedure Name:");
		JLabel costL			= 	new JLabel("Cost:");
		final JTextField name 		= 	new JTextField(22);
		final JTextField cost			= 	new JTextField(22);
		JButton update			= 	new JButton("Update");
		JButton reset			= 	new JButton("Reset");
		JPanel labelPanel 		=	new JPanel();
		labelPanel.setLayout		(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		JPanel textFieldPanel	=	new JPanel();
		textFieldPanel.setLayout	(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel		=	new JPanel();
		buttonPanel.setLayout		(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JPanel topPanel			=	new JPanel();
		topPanel.setLayout			(new BorderLayout());
		topPanel.setMaximumSize		(new Dimension(370,120));
		
		//add top components
		labelPanel.add				(nameL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		labelPanel.add				(costL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,19)));
		topPanel.add				(labelPanel, BorderLayout.WEST);
		textFieldPanel.add			(name);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		textFieldPanel.add			(cost);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,10)));
		topPanel.add				(textFieldPanel, BorderLayout.EAST);
		buttonPanel.add				(Box.createRigidArea(new Dimension (100,0)));
		buttonPanel.add				(update);
		buttonPanel.add				(Box.createRigidArea(new Dimension (10,0)));
		buttonPanel.add				(reset);
		topPanel.add				(buttonPanel, BorderLayout.SOUTH);
		panel.setBorder				(BorderFactory.createTitledBorder("Edit Procedure"));
		panel.add					(topPanel, BorderLayout.NORTH);
		
		//functionality for buttons
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String tName		=	name.getText();
				String tCostString	=	cost.getText();
				if (controller.updateProcedure(myProcedure, tName, tCostString)) //passes date into relevant setters of Procedure object 
				{
					procPopupFrame.dispose();
					new CreateProcedurePopup(myProcedure, controller);
				}
			}
		});

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				name.setText("");
				cost.setText("");
			}
		});
	}
}