package gooey;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {

	private JPanel mainPanel;
	private JButton addButton;
	private JButton deleteButton;
	private JButton editButton;

	public GUI(String title)
	{
		super(title);
		initUI();
	}
	
	public void initUI()
	{
		this.mainPanel = new JPanel();
		JTabbedPane tp = new JTabbedPane();
		//tp.addTab("Patients", new PatientPanel());
		tp.addTab("Procedures", new ProcedurePanel());
		tp.addTab("Payments", new PaymentPanel());
		add(tp);
	}
}
