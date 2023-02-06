import java.util.*;
public class UnitTestDSAGraph
{	    
	public static void main(String[] args)
    {
		DSAGraph graph = new DSAGraph(10);
		System.out.println("***Welcome to Unit Testing of DSAGraph.***\n");
		
		// TEST 1 : addEdge
		try 
		{
			System.out.println("Inserting 6 Values.");
			System.out.print("Testing addEdge: ");
			graph.addEdge("Home","CablesRUs", 4);
			graph.addEdge("Home", "LeadsRUs", 5);
			graph.addEdge("Home", "TechIsUs", 2.5);
			graph.addEdge("Home", "gadgetsRUs", 3);
			graph.addEdge("CablesRUs", "LeadsRUs", 3);
			graph.addEdge("CablesRUs", "TechIsUs", 2.5);
			graph.addEdge("CablesRUs", "gadgetsRUs", 5);
			graph.addEdge("LeadsRUs", "TechIsUs", 2.5);
			graph.addEdge("LeadsRUs", "gadgetsRUs", 4);
			graph.addEdge("TechIsUs", "gadgetsRUs", 2.5);
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}	
		
		// TEST 2 : Count
		try
		{
			int temp = graph.getCount();
			System.out.print("\nTesting Count: ");
			if (graph.getCount() != temp)
			{
				throw new IllegalArgumentException("FAILED.");
			}
			System.out.println("Passed.");
		} 
		catch(Exception e) 
		{ 
			System.out.println("Failed."); 
		}
		
		// TEST 3 : printGraph
		System.out.println("\nTesting printGraph: ");
		System.out.println(graph.printGraph());
		
		// TEST 4 : distance
		System.out.print("Testing distance: ");
		System.out.println(graph.distance("techisus", "cablesrus"));
	}
}