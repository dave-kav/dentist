/**
 * Payment class, stores details of a payment that a patient made on a particular date.
 * 
 * @author (Dave Kavanagh) 
 * @version (1.0)
 */
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Payment implements Serializable, Cloneable
{
	// instance variables
	private static int paymentNo=0;
	private int myPaymentNo;
	private double payment;
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private Date myDate;

	public Payment()
	{
		paymentNo++;
		myPaymentNo=paymentNo;
	}

	public Payment(String paymentDate, double payment) throws ParseException
	{
		try {
			this.myDate = dateFormatter.parse(paymentDate);		
			this.payment=payment;
			paymentNo++;
			myPaymentNo=paymentNo;
		}
		catch (IllegalArgumentException i ) {
			JOptionPane.showMessageDialog(null, "Please enter date in format dd/mm/yyyy");
		}
	}

	public void setMyPaymentNumber(int paymentNo) {
		this.myPaymentNo = paymentNo;
	}

	public void setPayment(double payment)
	{
		this.payment=payment;
	}

	public void setPaymentDate(String paymentDate)
	{
		try {
			this.myDate = dateFormatter.parse(paymentDate);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Date entered in incorrect format, please use dd/mm/yyyy");
		}
	}

	/**
	 * Used in loading from files
	 * @param paymentDate - date read in from file, converted into correct format
	 */
	public void loadDate (String paymentDate) {
		try {
			myDate = dateFormatter.parse(paymentDate);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Date stored in incorrect format, ensure to use dd/mm/yyyy");
		}
	}

	public int getMyPaymentNo()
	{
		return this.myPaymentNo;
	}

	public double getPayment()
	{
		return this.payment;
	}

	public String getPaymentDate()
	{
		return this.dateFormatter.format(myDate);
	}

	public String toString()
	{
		return ("\n" + this.myPaymentNo + "\t" + this.payment + "\t" + this.dateFormatter.format(myDate));
	}

	public void print()
	{
		System.out.println(toString());
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
