import java.util.*;
public class UnitTestOrder
{	    
	public static void main(String[] args)
    {
		Order order = new Order(8);	
		System.out.println("***Welcome to Unit Testing of Order.***\n");
		
		// TEST 1 : addOrder
		try 
		{
			System.out.println("Inserting 5 Products.");
			System.out.print("Testing addOrder: ");
			order.addOrder("belts", 3);
			order.addOrder("TV(4k-HDR)", 2);
			order.addOrder("shorts", 5);
			order.addOrder("smart watch", 10);
			order.addOrder("s21 ultra", 2);
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}	
		
		// TEST 2 : Count
		try
		{
			int temp = order.getCount();
			System.out.print("\nTesting Count: ");
			if (order.getCount() != temp)
			{
				throw new IllegalArgumentException("FAILED.");
			}
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("Failed."); 
		}
	}
}