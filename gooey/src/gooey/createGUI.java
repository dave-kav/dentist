package gooey;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class createGUI extends JFrame {
	private JFrame mainFrame;
	private JTabbedPane tabbedPane;
	private JPanel patientPanel;
	private JPanel procedurePanel;
	private JPanel paymentPanel;
	
	public static void main (String [] args) {
		createGUI myGUI=new createGUI();
	}
	
	public createGUI() {
		initUI();
	}

	public void initUI() {
		mainFrame = new JFrame("Patient Management System");
		mainFrame.setVisible(true);
		mainFrame.setSize(390,450);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane=createTabbedPane();
		
		//patientPanel=createPatientPanel();
		procedurePanel=createProcedurePanel();
		paymentPanel=createPaymentPanel();
		
		tabbedPane.add("Patients", patientPanel);
		tabbedPane.add("Payments", paymentPanel);
		tabbedPane.add("Procedures", procedurePanel);
		
		mainFrame.add(tabbedPane);
		
		patientPanel = new JPanel();
		patientPanel.setLayout(new BoxLayout(patientPanel, BoxLayout.Y_AXIS));
		//BoxLayout b1=new BoxLayout(patientPanel, BoxLayout.Y_AXIS);
		
		JPanel topPanel = new JPanel();
		patientPanel.add(topPanel);
		topPanel.setBackground(Color.red);
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		topPanel.setMaximumSize( new Dimension(50,20));
		topPanel.setAlignmentY( Component.CENTER_ALIGNMENT);
		middlePanel.setMaximumSize( new Dimension(50,20));
		middlePanel.setAlignmentY( Component.CENTER_ALIGNMENT);
		bottomPanel.setMaximumSize( new Dimension(20,20));
		bottomPanel.setAlignmentY( Component.CENTER_ALIGNMENT);
		
		
		patientPanel.setVisible(true);
	
	}
	
	public JTabbedPane createTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setVisible(true);
		return tabbedPane;
	}
	/*
	public JPanel createPatientPanel() {
		patientPanel = new JPanel();
		BoxLayout b1=new BoxLayout(patientPanel, BoxLayout.Y_AXIS);
		
		JPanel topPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		topPanel.setMaximumSize( new Dimension(50,20));
		topPanel.setAlignmentY( Component.CENTER_ALIGNMENT);
		middlePanel.setMaximumSize( new Dimension(50,20));
		middlePanel.setAlignmentY( Component.CENTER_ALIGNMENT);
		bottomPanel.setMaximumSize( new Dimension(20,20));
		bottomPanel.setAlignmentY( Component.CENTER_ALIGNMENT);
		
		
		patientPanel.setVisible(true);
		
		repaint();
		
		return patientPanel;
	}*/
	
	public JPanel createProcedurePanel() {
		procedurePanel = new JPanel();
		return procedurePanel;
	}
	
	public JPanel createPaymentPanel() {
		paymentPanel = new JPanel();
		return paymentPanel;
	}
}