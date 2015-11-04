package gooey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PDetailsPanel extends JFrame {
	private JPanel PDetailsPanel;
	{
		PDetailsPanel = new JPanel( new BorderLayout());
		PDetailsPanel=(JPanel) getContentPane();
		
		JLabel pNameL = new JLabel("Patient Name: ");
		JTextField pName = new JTextField(20);
		PDetailsPanel.add(pNameL);
		PDetailsPanel.add(pName);
		
		JLabel pAddrL = new JLabel("Address: ");
		JTextField pAddr = new JTextField(20);
		PDetailsPanel.add(pAddrL);
		PDetailsPanel.add(pAddr);
		
		JLabel pPhoneL = new JLabel("Phone number: ");
		JTextField pPhone = new JTextField(20);
		PDetailsPanel.add(pPhoneL);
		PDetailsPanel.add(pPhone);		
		
		PDetailsPanel.setVisible(true);
	
	}
}
