import java.util.*;
public class UnitTestfileManager
{	    
	public static void main(String[] args)
    {
		DSAHashTable hash = new DSAHashTable(2);
		DSAGraph graph = new DSAGraph(10);
		Order order = new Order(8);
		fileManager file = null;
		System.out.println("***Welcome to Unit Testing of File Manager.***");
		
		// TEST 1 : readInvt
        try 
		{
			System.out.println("\nTesting readInvt: ");
			file.readInvt("inventories.csv", hash);
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		// TEST 2 : readStore
        try 
		{
			System.out.println("\nTesting readStore: ");
			file.readStore("stores.csv", graph);
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
				
		// TEST 3 : readOrder
        try 
		{
			System.out.println("\nTesting readOrder: ");
			file.readOrder("order1.csv", order);
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
				
		// TEST 4 : writeFile
        try 
		{
			System.out.println("\nTesting writeFile: ");
			file.writeFile("test.csv", graph, hash, order);
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
				
		// TEST 5 : saveInvt
        try 
		{
			System.out.println("\nTesting saveInvt: ");
			file.saveInvt(hash, "test(invt).txt");
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		// TEST 6 : loadInvt
        try 
		{
			System.out.println("\nTesting loadInvt: ");
			file.loadInvt("test(invt).txt");
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
				
		// TEST 7 : saveStore
        try 
		{
			System.out.println("\nTesting saveStore: ");
			file.saveStore(graph, "test(store).txt");
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		// TEST 8 : loadStore
        try 
		{
			System.out.println("\nTesting loadStore: ");
			file.loadStore("test(store).txt");
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
	}
}