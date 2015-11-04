//comparing cars on owner name
import java.util.Comparator;
public class OwnertComparator implements Comparator<Car>{
	
	 public int compare (Car c1, Car c2) {
		  Name n1 = c1.getOwner();
		  Name n2 = c2.getOwner();
		  return n1.compareTo(n2);
	  }
}
