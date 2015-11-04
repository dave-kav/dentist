import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class CreateTabFour {

	private JPanel reportMain;
	private JPanel reportTop;
	private JPanel reportMiddle;
	private JPanel reportBottom;

	public CreateTabFour(JTabbedPane reportTP, MyController controller) {

		reportTP.setLayout			(new BoxLayout(reportTP, BoxLayout.Y_AXIS));
		//create panels for 2nd tab
		reportMain 			= 		new JPanel();
		reportTP.add				(reportMain);
		reportMain.setLayout			(new BoxLayout(reportMain, BoxLayout.Y_AXIS));

		//create inner panels
		reportTop				=	new JPanel();
		reportTop.setLayout			(new BorderLayout());
		reportTop.setMaximumSize	(new Dimension(390,150));
		reportMiddle			=	new JPanel();
		reportMiddle.setLayout		(new BorderLayout());
		reportMiddle.setMaximumSize	(new Dimension(390,150));
		reportBottom			=		new JPanel();
		reportBottom.setLayout		(new BorderLayout());
		reportMiddle.setMaximumSize	(new Dimension(390,150));
		reportMain.add				(reportTop);
		reportMain.add				(reportMiddle);
		reportMain.add				(reportBottom);	
		reportMain.add				(new JPanel());

		//method calls to create contents of panels for tab two
		tabFourTop(reportTop, controller);
		tabFourMiddle(reportMiddle, controller);
	}

	private void tabFourTop (JPanel procTop, MyController controller) {
		//create bottom components for 1st tab
		JLabel click			= 	new JLabel("Click here for a detailed patient report: ");
		JButton generate		=	new JButton("Generate");
		JPanel labelPanel		=	new JPanel();
		JPanel buttonPanel		=	new JPanel();

		//add components
		labelPanel.setLayout		(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		buttonPanel.setLayout		(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		labelPanel.add				(click);
		reportTop.add				(labelPanel, BorderLayout.WEST);
		buttonPanel.add				(generate);
		reportTop.add				(buttonPanel, BorderLayout.EAST);
		reportTop.setBorder			(BorderFactory.createTitledBorder("Patient Report"));
		reportTop.setMaximumSize	(new Dimension(390,50));	

		//functionality
		generate.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e)	{
				if(controller.createPatientReport()) {
					JOptionPane.showMessageDialog(null, "Report Generated - Check Project Folder.");
				}
			}
		});
	}

	private void tabFourMiddle(JPanel procMiddle2, MyController controller) {
		JLabel click			= 	new JLabel("Click here for an overdue payment report: ");
		JButton generate		=	new JButton("Generate");
		JPanel labelPanel		=	new JPanel();
		JPanel buttonPanel		=	new JPanel();

		//add components
		labelPanel.setLayout		(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		buttonPanel.setLayout		(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		labelPanel.add				(click);
		reportMiddle.add			(labelPanel, BorderLayout.WEST);
		buttonPanel.add				(generate);
		reportMiddle.add			(buttonPanel, BorderLayout.EAST);
		reportMiddle.setBorder		(BorderFactory.createTitledBorder("Procedure Report"));
		reportMiddle.setMaximumSize	(new Dimension(390,50));

		//functionality
		generate.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e)	{
				if(controller.createLatePaymentReport()) {
					JOptionPane.showMessageDialog(null, "Report Generated - Check Project Folder.");
				}
			}
		});
	}
}
