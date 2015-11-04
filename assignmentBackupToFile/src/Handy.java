/**
 * Handy methods
 * 
 * @author (Dave Kavanagh)
 */
import java.util.*;
public class Handy
{

    static Scanner kbd = new Scanner ( System.in ) ;

    /**
     * Reads validated integer
     */
     
    
    public static int readInt ( )                            
    {
        int p = 0 ;
        do{
            boolean wrongType ;                                 //flag used in validation
            do {
                wrongType = true ;                              //flag given value of true
                if ( kbd.hasNextInt ( ) )   
                {
                    wrongType = false ;                         //flag changes value if user inputs an int
                    p = kbd.nextInt ( ) ;   
                }
                    
                else 
                {
                    kbd.nextLine ( ) ;                          //otherwise clear kbd, print prompt
                    System.out.println ( "Invalid Entry.\n" )  ;
                }
            } while ( wrongType == true ) ;                     //loop while flag is set to true
        } while ( p < 0 ) ;               
        return p ;
    }
    
    public static int readInt (String prompt )                            
    {
        int p = 0 ;
        do{
            boolean wrongType ;                                 //flag used in validation
            do {
                wrongType = true ;                              //flag given value of true
                System.out.print ( prompt ) ;
                if ( kbd.hasNextInt ( ) )   
                {
                    wrongType = false ;                         //flag changes value if user inputs an int
                    p = kbd.nextInt ( ) ;   
                }
                    
                else 
                {
                    kbd.nextLine ( ) ;                          //otherwise clear kbd, print prompt
                    System.out.println ( "Invalid Entry.\n" )  ;
                }
            } while ( wrongType == true ) ;                     //loop while flag is set to true
        } while ( p < 0 ) ;               
        return p ;
    }
    
    public static int clearReadInt ()                            
    {
    	kbd.nextLine();
        int p = 0 ;
        do{
            boolean wrongType ;                                 //flag used in validation
            do {
                wrongType = true ;                              //flag given value of true
                if ( kbd.hasNextInt ( ) )   
                {
                    wrongType = false ;                         //flag changes value if user inputs an int
                    p = kbd.nextInt ( ) ;   
                }
                    
                else 
                {
                    kbd.nextLine ( ) ;                          //otherwise clear kbd, print prompt
                    System.out.println ( "Invalid Entry.\n" )  ;
                }
            } while ( wrongType == true ) ;                     //loop while flag is set to true
        } while ( p < 0 ) ;               
        return p ;
    }
    
    public static int clearReadInt (String prompt )                            
    {
    	kbd.nextLine();
        int p = 0 ;
        do{
            boolean wrongType ;                                 //flag used in validation
            do {
                wrongType = true ;                              //flag given value of true
                System.out.print ( prompt ) ;
                if ( kbd.hasNextInt ( ) )   
                {
                    wrongType = false ;                         //flag changes value if user inputs an int
                    p = kbd.nextInt ( ) ;   
                }
                    
                else 
                {
                    kbd.nextLine ( ) ;                          //otherwise clear kbd, print prompt
                    System.out.println ( "Invalid Entry.\n" )  ;
                }
            } while ( wrongType == true ) ;                     //loop while flag is set to true
        } while ( p < 0 ) ;               
        return p ;
    }

     public static double readDouble( String prompt )          //receives String from calling method
    {
        double p ;
        do{
            boolean wrongType ;                             //flag used in validation
            do {
                wrongType = true ;                           //flag given value of true
                System.out.print ( prompt ) ;
                if ( kbd.hasNextDouble ( ) )
                    wrongType = false ;                      //flag changes value if user inputs an int
                else 
                {
                    kbd.nextLine ( ) ;                         //otherwise clear kbd, print prompt
                    System.out.println ( "Invalid Entry.\n" )  ;
                }
            } while ( wrongType == true ) ;                    //loop while flag is set to true
            p = kbd.nextDouble ( ) ;                              //reads integer
        } while ( p < 0 ) ;               
        kbd.nextLine();
        return p ;
    }
    
    /**
     * Validate String for data entry
     */
    public static String readString ( String prompt )
    {
        String s = "" ;

        while ( s.isEmpty ( ) )//validates that a string is entered, loops if not.
        {
            System.out.print ( prompt ) ;
            s = kbd.nextLine ( ) ;
        }
        return s ;
    }
    
     public static String clearReadString ( String prompt )
    {
        kbd.nextLine ( ) ;
        String s = "" ;

        while ( s.isEmpty ( ) )//validates that a string is entered, loops if not.
        {
            System.out.print ( prompt ) ;
            s = kbd.nextLine ( ) ;
        }
        return s ;
    }
}
