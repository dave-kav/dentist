/**
 * Dave Kavanagh
 * R00013469
 */
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class MainApplication {
	static Scanner kbd=new Scanner (System.in);
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		ArrayList<Procedure> procedureList = new ArrayList<Procedure>();
		String patientFName, patientLName, patientAdd, patientPhone, patNameSearch = null, quit, delete, procName, procNameSearch, paymentDate;
		int menuChoice, subMenuChoice, subSubMenuChoice, count, patNoSearch = 0, patMenuChoice, patSubMenuChoice, procMenuChoice, procNoSearch, index = 0;
		double procCost, payment;
		//main menu
		do{
			System.out.println("Please choose from the following options:");
			System.out.println("\t1)\tManage patient");
			System.out.println("\t2)\tManage Procedures");		
			System.out.println("\t3)\tSave all info and quit");
			System.out.println("\t4)\tQuit without saving");

			menuChoice = Handy.readInt();
			//manage patient sub menu
			switch(menuChoice)
			{
			case 1:
				do{
					System.out.println("\t1)\tAdd patient");
					System.out.println("\t2)\tSearch for patient");	
					System.out.println("\t3)\tView all patient records");
					System.out.println("\t4)\tBack to main menu");

					subMenuChoice = Handy.readInt();

					switch(subMenuChoice)
					{
					case 1:
						patientFName		=	Handy.clearReadString("PLease enter the patient's first name: ");
						patientLName		=	Handy.clearReadString("PLease enter the patient's surname: ");
						patientAdd			=	Handy.readString("Please enter the patient's address: ");
						patientPhone		=	Handy.readString("Please enter the patient's phone number: ");
						Patient myPatient	=	new Patient(patientFName, patientLName, patientAdd, patientPhone);
						if (patientList.add(myPatient))
							System.out.println("\nNew patient record added for " + patientFName + " " + patientLName + ".\nPatient number: " + myPatient.getMyPatientNo());	break;
					case 2:
						patMenuChoice 		= 	Handy.readInt("Search by:\n1)\tName\n2)\tPatient number\nChoose number:  "); 
						if (patMenuChoice==1)
							patNameSearch	=	Handy.clearReadString("Enter patient name: ");

						else if (patMenuChoice==2)
							patNoSearch		=	Handy.clearReadInt("Enter patient number: ");			

						else System.out.println("Invalid choice");

						if(nameSearch(patientList, patNameSearch) || (search(patientList, patNoSearch)))
						{
							for (int i=0;i<patientList.size();i++)
							{
								String tempName = patientList.get(i).getPatientFirstName() + " " + patientList.get(i).getPatientLastName();
								if (patNameSearch.equalsIgnoreCase(tempName) || patNoSearch==patientList.get(i).getMyPatientNo())
									index=i;										
							}
							do {
								//individual patient menu
								System.out.println("\t1)\tAdd procedure");
								System.out.println("\t2)\tAdd payment");
								System.out.println("\t3)\tDelete patient record");
								System.out.println("\t4)\tBack to previous menu");

								patSubMenuChoice 	=	Handy.readInt();

								switch (patSubMenuChoice) 
								{
								case 1:		procName	= 	Handy.clearReadString("Procedure Name: ");
								procCost 	= 	Handy.readDouble("Procedure Cost: ");
								patientList.get(index).addProcedure(procName, procCost);	break;

								case 2:		paymentDate =	Handy.clearReadString("Please enter payment date: ");
								payment		=	Handy.readDouble("Amount:");
								patientList.get(index).getP_paymentList().add(new Payment(paymentDate, payment));	break;

								case 3:		delete=Handy.clearReadString("Delete patient record?\nEnter Y for yes\nPress any other key to cancel ");
								if (delete.charAt(0)=='y'||delete.charAt(0)=='Y')
								{
									patientList.remove(index);
									System.out.println("Record deleted"); break;
								} 
								else System.out.println("Record not deleted"); break;

								case 4: 	break;

								default:	System.out.println("Invalid Choice"); break;
								}
							} while (patSubMenuChoice != 4);
						}
						else 
							System.out.println("Patient not found"); break;

					case 3:
						count=0;
						System.out.println("\nPatient Details\n===============\n");
						while(count<patientList.size())
						{
							System.out.println(patientList.get(count).toString());
							if (calcIsPaid(patientList.get(count))>0)
							{
								System.out.println("Amount owed: " + (calcIsPaid(patientList.get(count))));
							}
							else
								System.out.println("No money owed");
							count++;
						}	break;

					case 4:   break;	//back to previous menu
					default:	
						System.out.println("Invalid Choice");
					}	
				}while (subMenuChoice != 4);

			case 2:
				do {
					//procedure submenu
					System.out.println("\t1)\tAdd a procedure");
					System.out.println("\t2)\tDelete a procedure");	
					System.out.println("\t3)\tView all procedures");
					System.out.println("\t4)\tBack to main menu");

					subSubMenuChoice = Handy.readInt();
					
					switch (subSubMenuChoice)
					{
					case 1:	//add procedure
						procName = Handy.clearReadString("Procedure name: ");
						procCost = Handy.readDouble("Procedure cost");
						procedureList.add(new Procedure(procName, procCost));	break;
					case 2:	//delete a procedure
						procMenuChoice = Handy.readInt("Search by:\n1)\tName\n2)\tProcedure number\nChoose number:  "); 
						if (procMenuChoice==1)
						{
							procNameSearch=Handy.clearReadString("Enter procedure name: ");			//find procedure by name
							if(procNameSearch(procedureList, procNameSearch))						//if found
							{
								quit=Handy.clearReadString("Delete procedure record?\nEnter Y for yes\nPress any other key to cancel ");
								if (quit.charAt(0)=='y'||quit.charAt(0)=='Y')						//if delete
								{
									count=0; //used to track values in array
									while(count<patientList.size())									//locate and delete
									{
										if (procedureList.get(count).getProcName().equals(procNameSearch))
											procedureList.remove(count);
										else count++;
									}
								}
								else break;
							}
							else System.out.println("Procedure not found");	break;
						}
						else if (procMenuChoice==2)													//same as above but using procedure number
						{
							procNoSearch=Handy.clearReadInt("Enter procedure number: ");

							if(procSearch(procedureList, procNoSearch))
							{
								quit=Handy.clearReadString("Delete patient record?\nEnter Y for yes\nPress any other key to cancel ");
								if (quit.charAt(0)=='y'||quit.charAt(0)=='Y')
								{
									count=0; //used to track values in array
									while(count<patientList.size())
									{
										if (procedureList.get(count).getProcName().equals(procNoSearch))
											procedureList.remove(count);
										else count++;
									}
								}
								else 
									kbd.nextLine(); break;
							}
							else System.out.println("Procedure not found");	break;
						}
						else System.out.println("Invalid choice");	break;
					}

				} while (subSubMenuChoice!=4);

			case 3:
				//backup data to files
				quit=Handy.clearReadString("Back up and Exit?\nEnter Y for yes\nPress any other key to cancel ");
				if (quit.charAt(0)=='y'||quit.charAt(0)=='Y')
				{
					File procedureFile = new File("procedures.txt");
					if (!procedureFile.exists())
						procedureFile.createNewFile();
					FileWriter procfw = new FileWriter(procedureFile);
					BufferedWriter procbw = new BufferedWriter(procfw);

					File patientsFile = new File("patients.txt");
					if (!patientsFile.exists())
						patientsFile.createNewFile();
					FileWriter patfw = new FileWriter(procedureFile);
					BufferedWriter patbw = new BufferedWriter(patfw);

					for (int i=0;i<procedureList.size();i++)
					{
						procbw.write(procedureList.get(i).getMyProcNo());
						procbw.write(procedureList.get(i).getProcName());
						procbw.write((int) procedureList.get(i).getProcCost());
					}
					procbw.close();

					for (int i=0;i<patientList.size();i++)
					{
						patfw.write(patientList.get(i).getMyPatientNo());
						patfw.write(patientList.get(i).getPatientFirstName());
						patfw.write(patientList.get(i).getPatientLastName());
						patfw.write(patientList.get(i).getPatientAdd());
						patfw.write(patientList.get(i).getPatientPhone());

						for (int j=0;j<patientList.get(i).getP_paymentList().size();j++)
						{
							patfw.write(patientList.get(i).getP_paymentList().get(j).getMyPaymentNo());
							patfw.write((int) patientList.get(i).getP_paymentList().get(j).getPayment());
							patfw.write(patientList.get(i).getP_paymentList().get(j).getPaymentDate().toString());
						}

						for (int k=0;k<patientList.get(i).getP_procList().size();k++)
						{
							patfw.write(patientList.get(i).getP_procList().get(k).getMyProcNo());
							patfw.write(patientList.get(i).getP_procList().get(k).getProcName());
							patfw.write((int) patientList.get(i).getP_procList().get(k).getProcCost());
						}

						patbw.close();
					}

					break;
				}
				else menuChoice=0; break;
				
			case 4:
				quit=Handy.clearReadString("Exit without saving?\nEnter Y for yes\nPress any other key to cancel ");
				if (quit.charAt(0)=='y'||quit.charAt(0)=='Y')
					break;
				else menuChoice=0; break;

			default:
				System.out.println("Incorrect selection, please from options 1 - 4");
			}
		}while(menuChoice!=3&&menuChoice!=4);
	}
	
	//finds patient within arrayList by patient number
	public static boolean search(ArrayList<Patient> patientList, int delPatient)
	{
		int count=0; //used to track values in array
		while(count<patientList.size())
		{
			if (patientList.get(count).getMyPatientNo()==(delPatient))
				return true;
			else count++;
		}
		return false;
	}
	
	//same as above but with name instead of number
	public static boolean nameSearch(ArrayList<Patient> patientList, String patNameSearch)
	{
		int count=0; //used to track values in array
		while(count<patientList.size())
		{
			String tempName = patientList.get(count).getPatientFirstName() + " " + patientList.get(count).getPatientLastName(); 
			if (tempName.equals(patNameSearch))
				return true;
			else count++;
		}
		return false;
	}

	//find procedure with in array by procedure number
	public static boolean procSearch(ArrayList<Procedure> procList, int delProc)
	{
		int count=0; //used to track values in array
		while(count<procList.size())
		{
			if (procList.get(count).getMyProcNo()==(delProc))
				return true;
			else count++;
		}
		return false;
	}

	//same as above but with procedure name
	public static boolean procNameSearch(ArrayList<Procedure> procList, String procNameSearch)
	{
		int count=0; //used to track values in array
		while(count<procList.size())
		{
			if (procList.get(count).getProcName().equals(procNameSearch))
				return true;
			else count++;
		}
		return false;
	}
	
	//checks running totals for amount owed and amount paid to determine if customer s paid up
	public static double calcIsPaid(Patient myPatient)
	{
		double procCostTotal=0;
		double payTotal=0;

		for (int i=0;i<myPatient.getP_paymentList().size();i++)
		{
			payTotal += myPatient.getP_paymentList().get(i).getPayment();
		}

		for (int i=0;i<myPatient.getP_procList().size();i++)
		{
			procCostTotal += myPatient.getP_procList().get(i).getProcCost();
		}

		if (payTotal >= procCostTotal)
			myPatient.setIsPaid(true);
		else 
		{
			myPatient.setIsPaid(false);
			return (procCostTotal-payTotal);	
		}
		return 0;
	}
}