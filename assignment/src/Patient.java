/**
 * Patient class, stores details of a patient.
 * 
 * @author (Dave Kavanagh) 
 * @version (1.0)
 */

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Patient implements Serializable, Comparable<Patient>, Cloneable
{
	//instance variables
	
	private static int patientNo = 0;
	private int myPatientNo;
	private String firstName;
	private String lastName;
	private String patientAdd;	//address
	private	String patientPhone;
	private boolean isPaid=false;
	private ArrayList <Payment> p_paymentList;
	private ArrayList <Procedure> p_procList;
	private int myPatientProcNo=1;	//used to avoid bug in incorrectly incrementing procedure numbers - assigns unique number to each procedure belonging to a patient
	
	public Patient()
	{
		p_paymentList = new ArrayList<Payment>();
		p_procList = new ArrayList<Procedure>();
	}

	public Patient(String patientFirstName, String patientLastName, String patientAdd, String patientPhone)
	{
		patientNo++;
		myPatientNo=patientNo;
		p_paymentList = new ArrayList<Payment>();
		p_procList = new ArrayList<Procedure>();
		this.firstName=patientFirstName;
		this.lastName=patientLastName;
		this.patientAdd=patientAdd;
		this.patientPhone=patientPhone;
	}

	public void setMyPatientNumber(int myPatientNo) {
		this.myPatientNo = myPatientNo;
	}

	public void setPatientFirstName(String patientFirstName)
	{
		this.firstName=patientFirstName;
	}

	public void setPatientLastName(String patientLastName)
	{
		this.lastName=patientLastName;
	}

	public void setPatientAdd(String patientAdd)
	{
		this.patientAdd=patientAdd;
	}

	public void setPatientPhone(String patientPhone)
	{
		this.patientPhone=patientPhone;
	}

	public void setIsPaid(boolean isPaid)
	{
		this.isPaid=isPaid;
	}

	public int getMyPatientNo()
	{
		return this.myPatientNo;
	}

	public String getPatientFirstName()
	{
		return this.firstName;	
	}

	public String getPatientLastName()
	{
		return this.lastName;	
	}

	public String getPatientAdd()
	{
		return this.patientAdd;
	}

	public String getPatientPhone()
	{
		return this.patientPhone;
	}

	public boolean getIsPaid()
	{
		return this.isPaid;
	}

	public boolean addPayment(String paymentDate, double payment) 
	{

		Payment tempPayment = new Payment();
		tempPayment.setPaymentDate(paymentDate);
		tempPayment.setPayment(payment);
		if (p_paymentList.add(tempPayment))
			return true;
		return false;
	}

	/**
	 * used in loading patients from files
	 * @param payment
	 */
	public void addPayment(Payment payment) {
		if (p_paymentList == null) {
			p_paymentList = new ArrayList<Payment>();
		}
		p_paymentList.add(payment);
	}

	public boolean addProcedure(String procName, double procCost)
	{
		if (p_procList.add(new Procedure(procName, procCost)))
			return true;
		return false;
	}

	//takes in an object and creates a deep copy
	public boolean addProcedure(Procedure myProcedure)
	{
		if (p_procList == null) {
			p_procList = new ArrayList<Procedure>();
		}
		Procedure tempProcedure = new Procedure();	//calling this constructor prevents the static variable for procNo being incorrectly incremented when adding a patient procedure
		tempProcedure.setProcedureName(myProcedure.getProcName());
		tempProcedure.setCost(myProcedure.getProcCost());
		tempProcedure.setMyPatientProcNo(myPatientProcNo);
		if (p_procList.add(tempProcedure))
		{
			myPatientProcNo++;
			return true;
		}
		return false;
	}

	public boolean removeProcedure(int i)
	{
		if (p_procList.remove(i) != null)
			return true;
		return false;
	}

	public ArrayList<Payment> getP_paymentList()
	{
		return this.p_paymentList;
	}

	public ArrayList<Procedure> getP_procList()
	{
		return this.p_procList;
	}

	public String toString()
	{
		String temp="Patient Procedures:\n================\n";

		for(int i=0;i<p_procList.size();i++)
		{
			temp+=p_procList.get(i).myPatProcToString() + "\n";
		}

		temp+="\n\nPatient Payments:\n================\n";

		for(int i=0;i<p_paymentList.size();i++)
		{
			temp+=p_paymentList.get(i).toString() + "\n";
		}

		return ("\n" + this.myPatientNo + "\t" + this.firstName + " " +  this.lastName + "\t" + this.patientAdd + "\t" + this.patientPhone + "\n\n" + temp + "\n");
	}

	public void print()
	{
		System.out.println(toString());
	}

	@Override
	public int compareTo(Patient n) {
		int lastCmp = this.lastName.compareTo(n.getPatientLastName());
		return (lastCmp != 0 ? lastCmp :
			this.firstName.compareTo(n.getPatientFirstName()));
	}
	
	public static void setPatientNoValue(int noOfPatients) {
		patientNo = noOfPatients;
	}
	
	public Object clone(){
        try
            {
                return super.clone();
            }
            catch(CloneNotSupportedException e)
                {
                    System.out.println("Cloning not allowed.");
                    return this;
                }
    }
}