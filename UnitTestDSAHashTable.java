import java.util.*;
public class UnitTestDSAHashTable
{	    
	public static void main(String[] args)
    {
		DSAHashTable hash = new DSAHashTable(2);
		Order order = new Order(8);																				//order needed to test export and search by name.
		System.out.println("***Welcome to Unit Testing of DSAHashTable.***\n");
			
		// TEST 1 : Resize
		try 
		{
			System.out.println("Inserting 5 values with array of size 2.");
			System.out.print("Testing Resize: ");
			hash.put("Nike", "shirt", 50);
			hash.put("Messi Special", "shorts", 200);
			hash.put("Gucci-Gang", "belts", 500);
			hash.put("Samsung Store", "smart watch", 1000);
			hash.put("Samsung Store", "washing Machine", 40);
			hash.put("Samsung Store", "s21 ultra", 1000);
			hash.put("iPhone", "12 pro max", 250);
			System.out.println("Passed.");																		//resize test passes as the length of table defined above is 2 whereas we are inserting 5 values so table gets resized.
        } 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		// TEST 2 : Put
		try 
		{
			System.out.println("\nInserting 6 Values.");
			System.out.print("Testing Put: ");
			hash.put("Sony-Bravia", "TV(4k-HDR)", 10);
			hash.put("LG", "air conditioner", 100);
			hash.put("Playstation", "PS5", 5);
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		// TEST 3 : Get
        try 
		{
			System.out.print("\nTesting Get: ");
			if (hash.get("smart watch") != 1000)
			{
				throw new IllegalArgumentException("FAILED.");
			}
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		// TEST 4 : Remove
		try
		{
			System.out.print("\nTesting Remove: ");
			hash.remove("air conditioner");
			if (hash.get("air conditioner") != 100)
			{
				throw new IllegalArgumentException("FAILED.");
			}
			System.out.println("FAILED.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("Passed."); 
		}
		
		// TEST 5 : Count
		try
		{
			int temp = hash.getCount();
			System.out.print("\nTesting Count: ");
			if (hash.getCount() != temp)
			{
				throw new IllegalArgumentException("FAILED.");
			}
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("Failed."); 
		}
		
		// TEST 6 : LoadFactor
		System.out.print("\nTesting LoadFactor: ");
		System.out.println(hash.getLoadFactor());
		
		// TEST 7 : export
		System.out.print("\nTesting export: \n");
		order.addOrder("belts", 3);																						//adding order so that it displays accordingly
		order.addOrder("TV(4k-HDR)", 2);
		order.addOrder("shorts", 5);
		order.addOrder("smart watch", 10);
		order.addOrder("s21 ultra", 2);
		System.out.println(hash.export(order));
		
		// TEST 8 : displayByName
		System.out.println("\nTesting displayByName: ");
		System.out.println(hash.displayByName("samsung store", order));
	}
}