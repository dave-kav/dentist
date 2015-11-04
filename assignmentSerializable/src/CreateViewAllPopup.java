/**
 * Dave Kavanagh
 * R00013469
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//this class creates a popup which is used by both the viewAllPatients and viewAllProcedure functions
public class CreateViewAllPopup {

	private JFrame popup;
	private JPanel main;
	private JTextArea info;
	private JScrollPane scroll;
	
	public CreateViewAllPopup(String prompt, String temp) {
		//create panel
		popup				= 		new JFrame(prompt);
		popup.setSize				(470, 510);
		main				=		new JPanel();
		main.setLayout				(new FlowLayout());
		info				=		new JTextArea(10, 100);
		info.setLineWrap			(true);
		info.setEditable			(false);
		info.setVisible				(true);
		scroll				=		new JScrollPane(info);
		scroll.setPreferredSize		(new Dimension(450,490));
		main.add					(scroll);
		
		//insert text passed from controller
		info.append(temp);
		
		//finalise panel
		popup.getContentPane		().add	(main); 
		popup.isAlwaysOnTop			();
		popup.setResizable			(false);
		popup.setLocationRelativeTo	(null);
		popup.setVisible			(true);
	}
}