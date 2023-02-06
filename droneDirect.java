/*
 *Author: Izaan Sheikh                              
 *Purpose: creating instance of droneDirect class
 *Date: 10-03-2021                                                                         */

 /**** This class directs user to appropriate mode or displays error ****/
class droneDirect
{
	public static void main(String [] args)
    {
		userInterface user;
		report rep;
		if (args.length < 1)
		{
			System.out.println("Please run the command properly.\nUsage: droneDirect -x");
			System.out.println("\twhere x is one of ");
			System.out.println("\ti - Interactive Mode");
			System.out.println("\tr - Report Mode");
		}
		else
		{
			if (args[0].equals("-i"))
			{
				user = new userInterface();
				user.menu();
			}
			else if (args[0].equals("-r"))
			{
				if (args.length < 4)
				{
					System.out.println("Please enter the file names in following order:");
					System.out.println("Usage: droneDirect -r <store_file> <inventory_file> <order_file>");
				}
				else 
				{
					rep = new report();
					rep.createReport(args[1], args[2], args[3]);
				}
			}
		}
	}
}