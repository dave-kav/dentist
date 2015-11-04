import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Name implements Comparable<Name> {
    private final String firstName, lastName;
	private int lastCmp;

    public Name(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new NullPointerException();
	this.firstName = firstName;
        this.lastName = lastName;
    }

    public String firstName() { return firstName; }
    public String lastName()  { return lastName;  }

    public boolean equals(Object o) {
        if (!(o instanceof Name))
            return false;
        Name n = (Name)o;
        return n.firstName.equals(firstName) &&
               n.lastName.equals(lastName);
    }

    public int hashCode() {
        return 31*firstName.hashCode() + lastName.hashCode();
    }

    public String toString() {
	return firstName + " " + lastName;
    }

    public int compareTo(Name n) {
        int lastCmp = this.lastName.compareTo(n.lastName);
        return (this.lastCmp != 0 ? lastCmp :
                this.firstName.compareTo(n.firstName));
    }

		public static void main(String[] args) {
	        ArrayList <Name> names= new ArrayList<Name>();
	           names.add(new Name("John", "Smith"));
	           names.add(new Name("Karl", "Ng"));
	           names.add(new Name("Jeff", "Smith"));
	           names.add(new Name("Tom", "Rich"));
	      	        
	        Collections.sort(names);
	        
	        for (int i=0;i<names.size();i++){
	        	System.out.println(names.get(i));	
	        }
	        
		}

}
