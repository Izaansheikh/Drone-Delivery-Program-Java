/*
 *Author: Izaan Sheikh                              
 *Purpose: creating instance of userInterface class
 *Date: 10-03-2021                                    */

 /**** This class deals with interactive mode ****/
import java.util.*;
public class userInterface
{
    //Class Fields
	private DSAHashTable invt;
	private DSAGraph store;
	private Order order;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: address of new userInterface object
     *ASSERTION: 
     ************************************************************/
    public userInterface()
    {
		invt = new DSAHashTable(2);
		store = new DSAGraph(10);
		order = new Order(20);
    }

    /************************************************************
	 *SUBMODULE: menu
	 *IMPORT: none
	 *EXPORT: none
	 *ASSERTION: helps user select what they wanna do.
     ************************************************************/  
    public void menu()
    {
        int choice;
        do
        {
            System.out.println("\nPlease select one of the following options: \n1.Load Data.\n2.Find and display location inventory.\n3.Find and display distance between two locations.\n4.Find and display route for collecting order.\n5.Location Overview.\n6.Inventory Overview.\n7.Save Data (Serialized).\n8.Exit.");
            choice = intInput("Select an option.",1, 8);
            if (choice == 1)
            {
				load();
            }
            else if (choice == 2)
            {
				String store;
				store = stringInput("Please enter name of the store.");
				System.out.println(invt.displayByName(store.toLowerCase(), order));
            }
            else if (choice == 3)
            {
				String loc1, loc2;
				loc1 = stringInput("Please enter the name of first Location.");
				loc2 =  stringInput("Please enter the name of second Location.");
				System.out.println(store.distance(loc1.toLowerCase(), loc2.toLowerCase()));
            }
            else if (choice == 4)
            {
				System.out.println(invt.route(order, store));
            }
            else if (choice == 5)
            {
				System.out.println(store.printGraph());
            }
            else if (choice == 6)
            {
				System.out.println(invt.export(order));
            }
            else if (choice == 7)
            {
				fileManager.saveStore(store, "store.txt");
				fileManager.saveInvt(invt, "invt.txt");
            }
            else if (choice == 8)
            {
                System.out.println("Good Bye!");
            }
        }while (choice != 8);
    }
	
    /************************************************************
	 *SUBMODULE: load
	 *IMPORT: msg (string), low (real), max (real)
	 *EXPORT: num (real)
	 *ASSERTION: validates the user input.
     ************************************************************/
	 public void load()
	 {
        int choice;
		String filename;
		System.out.println("\nPlease select one of the following options: \n1.Location Data.\n2.Order Data.\n3.Serialized Data.\n4.Exit from load mode.");
		choice = intInput("Select an option.",1, 4);
		if (choice == 1)
        {
			filename = stringInput("Please enter store file name.");
            fileManager.readStore(filename, store);
            filename = stringInput("\nPlease enter inventory file name.");
            fileManager.readInvt(filename, invt);
		}
        else if (choice == 2)
        {
			filename = stringInput("Please enter store file name.");
            fileManager.readOrder(filename, order);
        }
        else if (choice == 3)
        {
			store = fileManager.loadStore("store.txt");
			invt = fileManager.loadInvt("invt.txt");
        }
		else if (choice == 4)
		{
			System.out.println("Exiting load mode.");
		}
	 }
	 
    /************************************************************
	 *SUBMODULE: intInput
	 *IMPORT: msg (string), low (int), max (int)
	 *EXPORT: num (int)
	 *ASSERTION: validates the user input.
     ************************************************************/
    public int intInput(String msg, int low, int max) 
    {
        Scanner sc = new Scanner(System.in);
        int num;
        String x = msg;
        do 
        {
            try 
            {
                System.out.print(x + "\n");
                num = sc.nextInt();
            }
            catch(InputMismatchException e) 
            {
                sc.nextLine(); 												//Clears the buffer
                num = low - 1;
            }
            x = "Please enter a valid value";
        }while( (num < low) || (num > max) );
        return num;
    }

    /************************************************************
	 *SUBMODULE: stringInput
	 *IMPORT: msg (string)
	 *EXPORT: x (String)
	 *ASSERTION: validates the user input.
     ************************************************************/
    public String stringInput(String msg) 
    {
        Scanner sc = new Scanner(System.in);
        String x = "";
        try 
        {
            System.out.println(msg);
            x = sc.nextLine();
        }
        catch(IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        return x;
    }
}        
