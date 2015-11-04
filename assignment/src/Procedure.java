import java.io.Serializable;

/**
 * Procedure class, stores details of a procedure and its cost.
 * 
 * @author (Dave Kavanagh) 
 * @version (1.0)
 */
@SuppressWarnings("serial")
public class Procedure implements Serializable, Cloneable
{
    // instance variables
    private static int procNo = 0;
    private int myProcNo;
    private String procName;
    private double procCost;
    private int myPatientProcNo;

    public Procedure()
    {
    }
    
    public Procedure(String procName, double procCost)
    {
    	procNo++;
    	myProcNo=procNo;
    	this.procName=procName;
    	this.procCost=procCost;
    }
    
    //constructor used in loading procedures from file
    public Procedure(int myProcNo, String procName, double procCost)
    {
    	procNo = myProcNo;
    	this.myProcNo=myProcNo;
    	this.procName=procName;
    	this.procCost=procCost;
    }
    
    public void setProcNo(int procNo) {
		this.myProcNo = procNo;
	}
    
    public void setProcedureName(String procName)
    {
        this.procName=procName;
    }
    
    public void setCost(double procCost)
    {
        this.procCost=procCost;
    }
    
    public void setMyPatientProcNo(int procNo) 
    {
    	this.myPatientProcNo=procNo;
    }
    
    public int getMyPatProcNo() 
    {
    	return this.myPatientProcNo;
    }
    
    public int getMyProcNo()
    {
    	return this.myProcNo;
    }
    
    public String getProcName()
    {
        return this.procName;
    }
    
    public double getProcCost()
    {
        return this.procCost;
    }
    
    public String toString()
    {
        return ("\n" + this.myProcNo + "\t" + this.procName + "\t" + this.procCost + "\t");
    }
    
    //this toString method lists the patient's individual procedure numbers rather than the statically generated procedure numbers, thus avoiding a bug on incorrectly displayed procedyre numbers
    public String myPatProcToString()
    {
        return ("\n" + this.myPatientProcNo + "\t" + this.procName + "\t" + this.procCost + "\t");
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