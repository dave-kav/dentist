public class Person implements Cloneable {

	private String name;
	private String address;
	private int age;
	private Department depart;
	public Person(){

	}
	public Person(String aName, int aAge, Department aDepart) {

		this.name = aName;
		this.age = aAge;
		this.depart = aDepart;
	}

	protected Object clone() throws CloneNotSupportedException {

		Person clone = null;
		try{
			super.clone();
		}
		catch (CloneNotSupportedException e){
			e.printStackTrace();
		}

		// make the copy of the object of type Department
		// is it deep or shallow? Write a program to test it 
		clone.depart=(Department)depart.clone();
		return clone;

	}
	public static void main(String[] args) {

		Person ce=new Person();

		try {
			// make deep copy of the object of type Person
			Person cloned= (Person) ce.clone();
			// Now see if a change to the department object of ce changes the other 
			// one ie cloned
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}
} 
