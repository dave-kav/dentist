/**
 * Dave Kavanagh
 * R00013469
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateTabTwo {
	
	private JPanel procMain;
	private JPanel procTop;
	private JPanel procMiddle;
	private JPanel procBottom;
	
	public CreateTabTwo(JTabbedPane procedureTP, MyController controller, CreateGUI myGUI) {
		procedureTP.setLayout		(new BoxLayout(procedureTP, BoxLayout.Y_AXIS));
		//create panels for 2nd tab
		procMain 			= 		new JPanel();
		procedureTP.add				(procMain);
		procMain.setLayout			(new BoxLayout(procMain, BoxLayout.Y_AXIS));
		
		//create inner panels
		procTop				=		new JPanel();
		procTop.setLayout			(new BorderLayout());
		procTop.setMaximumSize		(new Dimension(390,150));
		procMiddle			=		new JPanel();
		procMiddle.setLayout		(new BorderLayout());
		procMiddle.setMaximumSize	(new Dimension(390,150));
		procBottom			=		new JPanel();
		procBottom.setLayout		(new BorderLayout());
		procMain.add				(procTop);
		procMain.add				(procMiddle);
		procMain.add				(procBottom);	
		procMain.add				(new JPanel());
		
		//method calls to create contents of panels for tab two
		tabTwoTop(procTop, controller);
		tabTwoMiddle(procMiddle, controller, myGUI);
		tabTwoBottom(procBottom, controller, myGUI);
	}

	private void tabTwoTop(JPanel procTop, final MyController controller) {
		//create top components
		JLabel procNameL 	= 	new JLabel("Name:");
		JLabel procPriceL	= 	new JLabel("Price:");
		final JTextField procName	= 	new JTextField(24);
		final JTextField procPrice= 	new JTextField(24);
		JButton addProc		= 	new JButton("Submit");
		JButton procReset	= 	new JButton("Reset");
		JPanel labelPanel 	=	new JPanel();
		labelPanel.setLayout	(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		JPanel textFieldPanel=	new JPanel();
		textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel	=	new JPanel();
		buttonPanel.setLayout	(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		//add top components
		labelPanel.add			(procNameL);
		labelPanel.add			(Box.createRigidArea(new Dimension (0,19)));
		labelPanel.add			(procPriceL);
		labelPanel.add			(Box.createRigidArea(new Dimension (0,19)));
		procTop.add				(labelPanel, BorderLayout.WEST);
		textFieldPanel.add		(procName);
		textFieldPanel.add		(Box.createRigidArea(new Dimension (0,10)));
		textFieldPanel.add		(procPrice);
		textFieldPanel.add		(Box.createRigidArea(new Dimension (0,10)));
		procTop.add				(textFieldPanel, BorderLayout.EAST);
		buttonPanel.add			(Box.createRigidArea(new Dimension (100,0)));
		buttonPanel.add			(addProc);
		buttonPanel.add			(Box.createRigidArea(new Dimension (10,0)));
		buttonPanel.add			(procReset);
		procTop.add				(buttonPanel, BorderLayout.SOUTH);
		procTop.setBorder		(BorderFactory.createTitledBorder("Add Procedure"));
		
		//functionality
		addProc.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String tempProcName		 	= procName.getText();
				String tempProcCostString 	= procPrice.getText();

				if (controller.addProcedure(tempProcName, tempProcCostString))	//call to addProcedure method of MyController class, returns boolean
				{
					procName.setText("");
					procPrice.setText("");
				}
			}
		});

		procReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procName.setText("");
				procPrice.setText("");
			}
		});
	}

	private void tabTwoMiddle(JPanel procMiddle, final MyController controller, CreateGUI myGUI) {
		//create middle components
		JLabel procSearchL			= 	new JLabel("Name: ");
		JLabel procNoSearchL		= 	new JLabel("Number: ");
		final JTextField procSearch		= 	new JTextField(24);
		final JTextField procNoSearch		= 	new JTextField(24);
		JButton search				= 	new JButton("Search");
		JButton procReset			= 	new JButton("Reset");
		JPanel labelPanel 			=	new JPanel();
		labelPanel.setLayout			(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		JPanel textFieldPanel		=	new JPanel();
		textFieldPanel.setLayout		(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel			=	new JPanel();
		buttonPanel.setLayout			(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		//add middle components
		labelPanel.add				(procSearchL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,18)));
		labelPanel.add				(procNoSearchL);
		labelPanel.add				(Box.createRigidArea(new Dimension (0,18)));
		procMiddle.add				(labelPanel, BorderLayout.WEST);
		textFieldPanel.add			(procSearch);
		textFieldPanel.add			(Box.createRigidArea(new Dimension (0,11)));
		procMiddle.add				(textFieldPanel, BorderLayout.EAST);
		textFieldPanel.add			(procNoSearch);
		procMiddle.add				(textFieldPanel, BorderLayout.EAST);
		buttonPanel.add				(Box.createRigidArea(new Dimension (100,0)));
		buttonPanel.add				(Box.createRigidArea(new Dimension (100,0)));
		buttonPanel.add				(search);
		buttonPanel.add				(Box.createRigidArea(new Dimension (10,0)));
		buttonPanel.add				(procReset);
		procMiddle.add				(buttonPanel, BorderLayout.SOUTH);
		procMiddle.setBorder		(BorderFactory.createTitledBorder("Procedure Search"));
		
		//functionality for buttons
		search.addActionListener	(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				String tempProcName = procSearch.getText();
				String tempProcNoString = procNoSearch.getText();
				if (controller.searchProcedure(tempProcName, tempProcNoString)!=null)	//searchProcedure method of MyController class returns Procedure object if found
				{
					Procedure myProcedure = controller.searchProcedure(tempProcName, tempProcNoString);
					new CreateProcedurePopup(myProcedure, controller);		//method call to create popup for Procedure object found above
					procSearch.setText("");
					procNoSearch.setText("");
				}
			}
		});
	
		procReset.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				procSearch.setText("");
				procNoSearch.setText("");
			}
		});
	}

	private void tabTwoBottom(JPanel procBottom, final MyController controller, CreateGUI myGUI) {
		//create bottom components for 1st tab
		JLabel click			= 	new JLabel("Click here to view all procedure details: ");
		JButton viewAll			=	new JButton("View All");
		JPanel labelPanel		=	new JPanel();
		JPanel buttonPanel		=	new JPanel();
		
		//add components
		labelPanel.setLayout		(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		buttonPanel.setLayout		(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		labelPanel.add				(click);
		procBottom.add				(labelPanel, BorderLayout.WEST);
		buttonPanel.add				(viewAll);
		procBottom.add				(buttonPanel, BorderLayout.EAST);
		procBottom.setBorder		(BorderFactory.createTitledBorder("Procedure Details"));
		procBottom.setMaximumSize	(new Dimension(390,50));

		//functionality
		viewAll.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e)	{
				String temp = controller.viewAllProcedures();		 //viewAllProcedures method in MyController class returns String representation of all Procedure objects
				if (temp!= null)
					new CreateViewAllPopup("All Procedures", temp);
			}
		});
	}
}