package gooey;

import javax.swing.JFrame;

public class guiMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI myFrame = new GUI("Patient Management System");
		myFrame.setSize(390,450);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
