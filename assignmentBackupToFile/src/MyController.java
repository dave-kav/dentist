/**
 * Dave Kavanagh
 * R00013469
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.Collections;

public class MyController {

	//main collection of procedures and patients
	private static ArrayList<Patient> patientList = new ArrayList<Patient>();
	private static ArrayList<Procedure> procedureList = new ArrayList<Procedure>();

	public MyController() {	}

	public static void main (String [] args) {
		try {
			loadProcedures();
		}
		catch (NullPointerException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error Loading Procedure File.");
		}

		try {
			loadPatients();
		}
		catch (NullPointerException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error Loading Patient File.");
		}
		Patient.setPatientNoValue(patientList.size());
		new CreateGUI();
	}

	public static void loadProcedures() throws FileNotFoundException {
		Scanner input = new Scanner(new File("Procedures.txt"));
		Procedure p;

		while(input.hasNext())
		{
			p = new Procedure();
			p.setProcNo(input.nextInt());
			input.nextLine();
			p.setProcedureName(input.nextLine());
			p.setCost(input.nextDouble());
			procedureList.add(p);
		}
		input.close();
	}

	public static void loadPatients() throws FileNotFoundException {
		Scanner input = new Scanner(new File("Patients.txt"));
		Patient patient;
		Procedure procedure;
		Payment payment;
		int count = 0;

		while(input.hasNext())
		{
			if(input.next().equals("aaaaa"))
			{
				patient = new Patient();
				patient.setMyPatientNumber(input.nextInt());
				patient.setPatientFirstName(input.next());
				patient.setPatientLastName(input.next());
				patient.setPatientAdd(input.next());
				patient.setPatientPhone(input.next());
				patientList.add(patient);
				while( !input.next().equals("bbbbb"))
				{
					procedure = new Procedure();
					procedure.setMyPatientProcNo(input.nextInt());
					procedure.setProcedureName(input.next());
					procedure.setCost(input.nextDouble());
					patientList.get(count).addProcedure(procedure);
				}
				while(!input.next().equals("ccccc"))
				{
					payment = new Payment();
					payment.setMyPaymentNumber(input.nextInt());
					payment.setPayment(input.nextDouble());
					input.nextLine();
					payment.loadDate(input.nextLine());
					patientList.get(count).addPayment(payment);
				}
				count++;
			}
		}
		input.close();

	}
	
	// deprecated method previously used in converting date - over complicated
	//	private static String convertDate(String tempDate) {
	//		try {
	//			String parts [] = tempDate.split(" ");
	//			String month = "";
	//			switch (parts[1]){
	//			case "Jan":	month="01"; break;
	//			case "Feb":	month="02"; break;
	//			case "Mar":	month="03"; break;
	//			case "Apr":	month="04"; break;
	//			case "May":	month="05"; break;
	//			case "Jun":	month="06"; break;
	//			case "Jul":	month="07"; break;
	//			case "Aug":	month="08"; break;
	//			case "Sep":	month="09"; break;
	//			case "Oct":	month="10"; break;
	//			case "Nov":	month="11"; break;
	//			case "Dec":	month="12"; break;
	//			}
	//			return (parts[2] + "-" + month + "-" + parts[5]);
	//		} catch (IndexOutOfBoundsException e) {e.printStackTrace();	}
	//		return null;
	//	}

	//adds a patient object to the ArrayList
	public boolean addPatient(String fName, String lName, String address, String phoneNumber, CreateGUI myGUI) {
		if (fName.isEmpty() || lName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty())		//display an error message if a field is not populated
		{
			JOptionPane.showMessageDialog(null, "Please enter values for all fields.");
		}
		else
		{
			Patient myPatient=new Patient(fName, lName, address, phoneNumber);
			if (getPatientList().add(myPatient))
			{
				JOptionPane.showMessageDialog(null, "New patient record added for " + fName + " " + lName + ".\nPatient number: " + myPatient.getMyPatientNo());
				new CreatePatientPopUp(myPatient, this, myGUI);
				return true;
			}
		}
		return false;
	}

	//calls one of two methods of this class to search for a Patient, either by name or patient number
	public Patient searchPatient(String patName, String patNoString) {
		int index=-1;
		if (patName.isEmpty() && patNoString.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please enter either name or number");
			return null;
		}
		if (!patName.isEmpty())
		{
			if(nameSearch(getPatientList(), patName) >-1)	//search by name will return an index greater than -1 if a patient is found
				index = nameSearch(getPatientList(), patName);
		}
		else if (!patNoString.isEmpty())
			try {
				int patNo = Integer.parseInt(patNoString);
				index = search(getPatientList(), patNo);			//search uses patient number, will return an index greater than -1 if a patient is found	
			}
		catch (NumberFormatException x) {
			JOptionPane.showMessageDialog(null, "Please enter a valid integer value to search by patient number.");
			return null;
		}

		if (index >-1)
			return getPatientList().get(index);				//return the Patient object found
		else 
		{
			JOptionPane.showMessageDialog(null, "Record not found.");
			return null;
		}
	}

	//returns a String containing all patient details,  including each patients own procedures and payments
	public String viewAllPatients() {
		if (getPatientList().size()<1)	//if no patients yet exist, display message
		{
			JOptionPane.showMessageDialog(null, "No records to display");
			return null;
		}

		int count=0;
		String temp ="Pat. Number\tName\tAddress\tPhone\n======\t======\t======\t======";

		while(count<getPatientList().size())
		{
			temp += (getPatientList().get(count).toString() + "\n");	
			temp += amountOwed(getPatientList().get(count));
			count++;
		}
		return temp;
	}


	public boolean updatePatient(Patient myPatient, String fName, String lName, String address, String phone) {
		if (fName.isEmpty() && lName.isEmpty() && address.isEmpty() && phone.isEmpty())			//if no values are entered in GUI, display a message
		{
			JOptionPane.showMessageDialog(null, "No values entered");
			return false;
		}

		//if any one or more of the following are populated there will be an update to the patient record
		if (!fName.isEmpty())
			myPatient.setPatientFirstName(fName);
		if (!lName.isEmpty())
			myPatient.setPatientLastName(lName);
		if (!address.isEmpty())
			myPatient.setPatientAdd(address);
		if (!phone.isEmpty())
			myPatient.setPatientPhone(phone);

		JOptionPane.showMessageDialog(null, "Paitent record updated.");
		return true;
	}

	//removes a patient record from the system
	public boolean deletePatient(Patient myPatient) {
		for (int i=0;i<getPatientList().size();i++)
		{
			if (getPatientList().get(i).getMyPatientNo()==myPatient.getMyPatientNo())
			{
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to permanently delete this patient record?", null, JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					getPatientList().remove(i);
					JOptionPane.showMessageDialog(null, "Patient record removed");
					return true;
				}
				else return false;
			}
		}

		JOptionPane.showMessageDialog(null, "Record not found");
		return false;
	}

	//adds a procedure to the main system collection
	public boolean addProcedure(String procName, String procCost) {
		if (procName.isEmpty() || procCost.isEmpty())		//if a value is not entered, display error
			JOptionPane.showMessageDialog(null, "Please enter all values.");

		else
		{
			try {
				Double tempProcCost	= Double.parseDouble(procCost);

				Procedure myProcedure = new Procedure(procName, tempProcCost);
				if (procedureList.add(myProcedure))
				{
					JOptionPane.showMessageDialog(null, "New procedure record added for " + procName);
					new CreateProcedurePopup(myProcedure, this);
					return true;
				}
			}

			catch (NumberFormatException x) {
				JOptionPane.showMessageDialog(null, "Please ensure a valid price is entered");
				return false;
			}
		}
		return false;
	}

	//finds a and returns given Procedure so that actions can be performed on it, uses one of two methods of this class to search by name or procedure number
	public Procedure searchProcedure(String procName, String procNoString)	{
		int index = -1;
		int tempProcNo=0;

		if (procName.isEmpty() && procNoString.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please enter either name or number.");
			return null;
		}

		if (!procName.isEmpty())
		{
			if ((procNameSearch(procedureList, procName) >-1)) 
				index = procNameSearch(procedureList, procName);		//uses the procNameSearch function to find the Procedure by name
		}

		else if (!procNoString.isEmpty())
		{
			try {
				tempProcNo = Integer.parseInt(procNoString);
				index = procSearch(procedureList, tempProcNo);			//finds the procedure by procedure number		
			}
			catch (NumberFormatException x) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer value to search by number.");
				return null;
			}
		}

		if (index >-1)
			return procedureList.get(index);

		else 
		{
			JOptionPane.showMessageDialog(null, "No record found.");
			return null;
		}
	}

	//returns String representation of all of the systems collection of procedures
	public String viewAllProcedures() {
		if (procedureList.size()<1)		//if none yet exist, display an error
		{
			JOptionPane.showMessageDialog(null, "No records to display");
			return null;
		}

		int count=0;
		String temp ="Number\tName\tCost\n======\t======\t======\t";

		while(count<procedureList.size())
		{
			temp += (procedureList.get(count).toString() + "\n");	
			count++;
		}
		return temp;
	}

	//similar to method above but works around bug on patient search where error dialog always shows if no procedures yet exist
	public String listAllProcedures () {
		if (procedureList.size()<1)
			return null;

		int count=0;

		String temp ="Number\tName\tCost\n======\t======\t======\t";
		while(count<procedureList.size())
		{
			temp += (procedureList.get(count).toString() + "\n");	
			count++;
		}
		return temp;
	}

	//passes parameters to relevant setters of a Procedure object to update
	public boolean updateProcedure (Procedure myProcedure, String procName, String procCostString) {
		if (procName.isEmpty()&&procCostString.isEmpty())			//display error if no details entered 
		{
			JOptionPane.showMessageDialog(null, "No details entered");
			return false;
		}
		//if any detail is provided, update accordingly 
		if (!procName.isEmpty())
			myProcedure.setProcedureName(procName);
		if (!procCostString.isEmpty())
		{
			try {
				Double tempProcCost	= Double.parseDouble(procCostString);
				myProcedure.setCost(tempProcCost);
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Please ensure a valid price is entered.");
				return false;
			}
		}

		JOptionPane.showMessageDialog(null, "Procedure details updated");
		return true;
	}

	//removes a procedure from the system collections
	public boolean deleteProcedure(Procedure myProcedure) {
		for (int i=0;i<procedureList.size();i++)
		{
			if (procedureList.get(i).getMyProcNo()==myProcedure.getMyProcNo())
			{
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to permanently delete this procedure record?", null, JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					procedureList.remove(i);
					JOptionPane.showMessageDialog(null, "Procedure removed");
					return true;
				}
				else return false;
			}
		}

		JOptionPane.showMessageDialog(null, "Procedure not found");
		return false;
	}

	//adds a procedure to a patients individual collection
	public boolean addPatientProc(Patient myPatient, int index) {
		//		redundant code from earlier design
		//		if (procedureList.size()<1)
		//		{
		//			JOptionPane.showMessageDialog(null, "No procedures to add");
		//			return false;
		//		}
		if (index < 0)
			throw new IndexOutOfBoundsException();

		try {
			Procedure myProcedure = procedureList.get(index);
			if (myPatient.addProcedure(myProcedure))
			{
				JOptionPane.showMessageDialog(null, "Procedure added to patient's record");
				return true;
			}	
		}
		catch (IndexOutOfBoundsException x){
			JOptionPane.showMessageDialog(null, "No procedures available to add.");
		}	
		return false;
	}

	//as above but removes a procedure from a patients own collection
	public boolean removePatientProc(Patient myPatient, int index) {
		//		redundant code from earlier design
		//		if (myPatient.getP_procList().size()<1)
		//		{
		//			JOptionPane.showMessageDialog(null, "No procedures to remove");
		//			return false;
		//		}
		if (index < 0)
			throw new IndexOutOfBoundsException();

		try {
			if (myPatient.removeProcedure(index))
			{
				JOptionPane.showMessageDialog(null, "Procedure removed from patient records");
				return true;
			}
		}
		catch (IndexOutOfBoundsException x) {
			JOptionPane.showMessageDialog(null, "No procedures available to remove.");			
		}

		return false;
	}

	//returns names of system collection of patients
	public String getPatientNames()
	{
		String names = "";
		for (int i=0;i<getPatientList().size();i++)
		{
			names += getPatientList().get(i).getPatientFirstName() + " " + getPatientList().get(i).getPatientLastName() + ";";
		}
		return (names);
	}

	//returns names of system collection of procedures
	public String getProcedureNames()
	{
		String names = "";
		for (int i=0;i<procedureList.size();i++)
		{
			names += procedureList.get(i).getProcName() + ";";
		}
		return (names);
	}

	//as above but returns names of an individual patients collection of procedures
	public String getPatientProcedureNames(Patient myPatient) {
		String names = "";
		for (int i=0;i<myPatient.getP_procList().size();i++)
		{
			names += myPatient.getP_procList().get(i).getProcName() + ";";
		}
		return (names);
	}

	//adds a payment to a patients collection
	public boolean addPatientPayment(Patient myPatient, String date, String amount) {
		if (date.isEmpty()||amount.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please enter both date and amount.");
			return false;
		}
		try {
			double payAmount = Double.parseDouble(amount);
			if (myPatient.addPayment(date, payAmount))
			{
				JOptionPane.showMessageDialog(null, "Payment added to patient's record");
				return true;
			}
		}
		catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
			return false;
		}
		return false;
	}

	//	the following method is used in tab three of the GUI, searches if a patient exists and adds a payment if successful
	//	deprecated since implementation of drop down on tab three
	//	public boolean searchAddPayment(String name, String date, String amount) throws HeadlessException, ParseException {
	//		if (patientList.size()<1)
	//		{
	//			JOptionPane.showMessageDialog(null, "Patient record not found");
	//			return false;
	//		}
	//
	//		if(name.isEmpty() || (date.isEmpty() || amount.isEmpty()))
	//		{
	//			JOptionPane.showMessageDialog(null, "Please provide all details");
	//			return false;
	//		}
	//
	//		else
	//		{
	//			int index=-1;
	//
	//			if ((nameSearch(patientList, name) >-1)) 	//search by name will return an index greater than -1 if a patient is found
	//				index = nameSearch(patientList, name);
	//
	//			double payAmount = Double.parseDouble(amount);
	//
	//			if(patientList.get(index).addPayment(date, payAmount))
	//			{
	//				JOptionPane.showMessageDialog(null, "Payment added to patient's record");
	//				return true;
	//			}
	//		}
	//
	//		JOptionPane.showMessageDialog(null, "Patient record not found");
	//		return false;
	//	}

	//backs up info to files on system exit
	public void procedureBackup() throws FileNotFoundException, IOException  {

		PrintWriter procpw 	= 	new PrintWriter("Procedures.txt");

		//create file and writers for procedure info

		for (int i = 0; i < procedureList.size(); i++)
		{
			procpw.println((int) procedureList.get(i).getMyProcNo());
			procpw.println(procedureList.get(i).getProcName());
			procpw.println((double) procedureList.get(i).getProcCost());
		}
		procpw.close();	
	}

	public void patientBackup() throws FileNotFoundException, IOException  {
		{
			PrintWriter pw = new PrintWriter("Patients.txt");

			ArrayList<Procedure> patProc = new ArrayList<Procedure>();
			ArrayList<Payment> patPay = new ArrayList<Payment>();

			for(int i = 0; i< MyController.patientList.size();i++)
			{
				patProc = patientList.get(i).getP_procList();
				patPay = patientList.get(i).getP_paymentList();

				pw.println("aaaaa");
				pw.println(patientList.get(i).getMyPatientNo());
				pw.println(patientList.get(i).getPatientFirstName());
				pw.println(patientList.get(i).getPatientLastName());
				pw.println(patientList.get(i).getPatientAdd());
				pw.println(patientList.get(i).getPatientPhone());

				if (patProc.size()==0){
					pw.println("bbbbb");
				}
				else {
					for(int j = 0; j<patProc.size();j++) {
						pw.println("--");
						pw.println(patProc.get(j).getMyPatProcNo());
						pw.println(patProc.get(j).getProcName());
						pw.println(patProc.get(j).getProcCost());
					}
					pw.println("bbbbb");
				}

				if (patPay.size()==0){
					pw.println("ccccc\n");
				}
				else {
					for(int l = 0; l<patPay.size();l++)
					{
						pw.println("--");
						pw.println(patPay.get(l).getMyPaymentNo());
						pw.println(patPay.get(l).getPayment());
						pw.println(patPay.get(l).getPaymentDate());
					}
					pw.println("ccccc\n");
				}
			}
			pw.close();
		}
	}


	//find out if patient owes money by generating running totals of all a payments procedures and payments and calculating the difference
	public double calcIsPaid(Patient myPatient)
	{
		double procCostTotal=0;
		double payTotal=0;

		//get total cost of procedures 
		for (int i=0;i<myPatient.getP_paymentList().size();i++)
		{
			payTotal += myPatient.getP_paymentList().get(i).getPayment();
		}

		//get total of payments
		for (int i=0;i<myPatient.getP_procList().size();i++)
		{
			procCostTotal += myPatient.getP_procList().get(i).getProcCost();
		}

		//return amount overpaid
		if (procCostTotal < payTotal)
		{
			myPatient.setIsPaid(true);
			return Math.round((procCostTotal-payTotal) * 100.0) / 100.0;
		}

		//return amount owed
		else if (procCostTotal > payTotal)
		{
			myPatient.setIsPaid(false);
			return Math.round((procCostTotal-payTotal) * 100.0) / 100.0;
		}

		else 
			myPatient.setIsPaid(true);

		return 0;
	}

	//returns a String representation of amount owed by patient
	public String amountOwed(Patient myPatient) {
		String owed="";
		if (calcIsPaid(myPatient)>0)			//calcIsPaid works out whether a patient owes money
		{
			owed += ("Amount owed: " + (calcIsPaid(myPatient)) + "\n==============================================");
		}
		else if ((calcIsPaid(myPatient)<0))
		{
			owed += ("Account in credit: " + (calcIsPaid(myPatient)) + "\n==============================================");
		}
		else
			owed += ("No money owed\n==============================================");
		return owed;
	}

	public void safeExitOption(MyController controller) throws FileNotFoundException, IOException {
		int result1, result2;
		result1 = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?", null, JOptionPane.YES_NO_OPTION);
		if (result1 == JOptionPane.YES_OPTION)
		{
			result2 = JOptionPane.showConfirmDialog(null, "Would you like to backup your data first?", null, JOptionPane.YES_NO_OPTION);
			if (result2 == JOptionPane.YES_OPTION)
			{
				controller.patientBackup();
				controller.procedureBackup();
				System.exit(0);
			}
			else 
				System.exit(0);
		}
	}

	//methods used in searching patients and procedures below here
	//search for a patient by patient number
	public int search(ArrayList<Patient> patientList, int patNo)
	{
		int count=0; //used to track values in array
		while(count<patientList.size())
		{
			if (patientList.get(count).getMyPatientNo()==(patNo))
				return count;
			else count++;
		}
		return -1;
	}

	//search for a patient by name
	public int nameSearch(ArrayList<Patient> patientList, String patName)
	{
		int count=0; //used to track values in array
		while(count<patientList.size())
		{
			String tempName = patientList.get(count).getPatientFirstName() + " " + patientList.get(count).getPatientLastName();
			if (tempName.equalsIgnoreCase(patName))
				return count;
			else count++;
		}
		return -1;
	}

	//search for a procedure by number
	public int procSearch(ArrayList<Procedure> procList, int procSearch)
	{
		int count=0; //used to track values in array
		while(count<procList.size())
		{
			if (procList.get(count).getMyProcNo()==(procSearch))
				return count;
			else count++;
		}
		return -1;
	}

	public int procNameSearch(ArrayList<Procedure> procList, String procNameSearch)
	{
		int count=0; //used to track values in array
		while(count<procList.size())
		{
			if (procList.get(count).getProcName().equalsIgnoreCase(procNameSearch))
				return count;
			else count++;
		}
		return -1;
	}

	/**
	 * @return the patientList
	 */
	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	@SuppressWarnings("unchecked")
	public boolean createPatientReport() {
		ArrayList<Patient> patientListClone = (ArrayList<Patient>) patientList.clone();
		Collections.sort(patientListClone);

		PrintWriter pR = null;
		try {
			pR = new PrintWriter("patientReport" + System.currentTimeMillis() + ".txt");
		} catch (FileNotFoundException e) {
			return false;
		}

		pR.println("=============================");
		pR.println("=ALPHABETCIAL PATIENT REPORT=");
		pR.println("=============================");

		for(int i = 0; i < patientListClone.size(); i++) {

			pR.println(patientListClone.get(i).getPatientLastName() + ", " + patientListClone.get(i).getPatientFirstName());
			pR.println("Patient Number: " + patientListClone.get(i).getMyPatientNo());
			pR.println(patientListClone.get(i).getPatientAdd());
			pR.println(patientListClone.get(i).getPatientPhone());
			pR.println("-----------");

			pR.println("Procedures");
			if (patientListClone.get(i).getP_procList()==null) {
				pR.println("No procedures.");
			}
			else {
				for(int j = 0; j < patientListClone.get(i).getP_procList().size(); j++) {
					pR.println(patientListClone.get(i).getP_procList().get(j).getMyPatProcNo());
					pR.println(patientListClone.get(i).getP_procList().get(j).getProcName());
					pR.println(patientListClone.get(i).getP_procList().get(j).getProcCost());
				}
			}
			pR.println("-----------");

			pR.println("Payments");
			if (patientListClone.get(i).getP_paymentList()==null) {
				pR.println("No Payments.");
			}
			else {
				for(int k = 0; k < patientListClone.get(i).getP_paymentList().size(); k++) {
					pR.println(patientListClone.get(i).getP_paymentList().get(k).getMyPaymentNo());
					pR.println(patientListClone.get(i).getP_paymentList().get(k).getPaymentDate());
					pR.println(patientListClone.get(i).getP_paymentList().get(k).getPayment());
				}
			}
			pR.println("-----------");
		}
		pR.close();
		return true;
	}

	public boolean createLatePaymentReport() {
		ArrayList<Patient> lateList = new ArrayList<Patient>();
		ArrayList<Payment> latePayList = new ArrayList<Payment>();
		
		for (int i = 0; i < patientList.size(); i++) {
			if (calcIsPaid(patientList.get(i)) > 0) {
				lateList.add((Patient)patientList.get(i).clone());
			}
		}
				
		Collections.sort(lateList, new Comparator<Patient>() {
			public int compare (Patient p1, Patient p2) {
					double amount1 = calcIsPaid(p1);
					double amount2 = calcIsPaid(p2);
					return (int) (amount2 - amount1);					
			}
		});

		for(int j = 0; j < lateList.size();j++)
		{
			latePayList = lateList.get(j).getP_paymentList();
			for(int k = 0;k<latePayList.size();k++)
			{
				if(this.compareDates(latePayList.get(k).getPaymentDate()))
				{
					lateList.remove(j);
					j = 0;
					k = latePayList.size();
				}
			}
		}

		PrintWriter pR = null;
		try {
			pR = new PrintWriter("lateReport" + System.currentTimeMillis() + ".txt");
		} catch (FileNotFoundException e) {
			return false;
		}

		pR.println("=============================");
		pR.println("=====LATE PAYMENT REPORT=====");
		pR.println("=============================");
		
		if (lateList.size()==0) {
			pR.println("No patients currently have any outstanding payments that have not been serviced for more than 6 months.");
		}

		for(int i = 0; i < lateList.size(); i++) {
			pR.println(lateList.get(i).getPatientLastName() + ", " + lateList.get(i).getPatientFirstName());
			pR.println("Patient Number: " + lateList.get(i).getMyPatientNo());
			pR.println(lateList.get(i).getPatientAdd());
			pR.println(lateList.get(i).getPatientPhone());
			
			pR.println("-----------");
			
			pR.println("Payments");
			if (lateList.get(i).getP_paymentList()==null) {
				pR.println("No Payments.");
			}
			else {
				for(int k = 0; k < lateList.get(i).getP_paymentList().size(); k++) {
					pR.println(lateList.get(i).getP_paymentList().get(k).getMyPaymentNo());
					pR.println(lateList.get(i).getP_paymentList().get(k).getPaymentDate());
					pR.println(lateList.get(i).getP_paymentList().get(k).getPayment());
				}
			}
			pR.println("-----------");
			pR.println("Total Owed: " + calcIsPaid(lateList.get(i)));
			pR.println("-----------");
			pR.println("-----------");
		}

		pR.close();
		return true;
	}

	//This method checks to see if a payment has been paid in the last 6 months
	public boolean compareDates(String payDate)
	{
		long backDate = System.currentTimeMillis() - ((long)180) *24*60*60*1000;

		Date checkDate = new Date();
		checkDate.setTime(backDate);

		@SuppressWarnings("deprecation")
		Date paymentDate = new Date(payDate);

		if(paymentDate.after(checkDate))
			return true;

		return false;
	}

	public static int patListSize() {
		return patientList.size();
	}
}