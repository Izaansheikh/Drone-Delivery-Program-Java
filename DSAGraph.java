/*
 *Author: Izaan Sheikh                              
 *Purpose: creating DSAGraph class
 *Date: 19-02-2021                                    */
 
 /**** This class is taken from Practical 6 submitted by me, however some additional functionalities have been added for assignment needs ****/
import java.util.*;
import java.io.*;
public class DSAGraph implements Serializable
{
	private int vertices;
    private LinkedList<DSAGraphWeight> adjacencylist[ ];
	private int count;
	
	@SuppressWarnings("unchecked")
	/************************************************************
     *Alternative Constructor:
     *IMPORT: none
	 *EXPORT: none
     *ASSERTION: declares the values.
     *************************************************************/
    public DSAGraph(int vertices) 
	{
		this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];
        for (int i = 0; i <vertices ; i++) 
		{
			adjacencylist[i] = new LinkedList();																	//initialize adjacency lists for all the vertices
        }
	}
	
	//ACCESSORS
	public int getCount()
	{
		return count;
	}
	
	/********************************************************************
	 *SUBMODULE: addEdge
	 *IMPORT: label1 (String), label2 (String), distance (double)
	 *EXPORT: none
	 *ASSERTION: adds the edge and its weight.
	 *********************************************************************/
	public void addEdge(String label1, String label2, double distance)
	{	
		int source = getIntForString(label1);
		int destination = getIntForString(label2);
		DSAGraphWeight weight = new DSAGraphWeight(source, destination, distance);
        adjacencylist[source].addFirst(weight); 																	//for undirected graph
		count++;
	}

	/********************************************************************
	 *SUBMODULE: getStringForNumber
	 *IMPORT: i (Integer)
	 *EXPORT: alphabet[i] (String)
	 *ASSERTION: converts number to String.
	 *********************************************************************/
	private String getStringForNumber(int i) 
	{
		String[ ] location = {"Home", "CablesRUs", "LeadsRUs", "TechIsUs", "gadgetsRUs"};
		return location[i];
	}
	
	/********************************************************************
	 *SUBMODULE: getIntForString
	 *IMPORT: a (String)
	 *EXPORT: num (Integer)
	 *ASSERTION: converts String to number.
	 *********************************************************************/
	private int getIntForString(String s)
	{
		int num = 5;
		String[ ] location = {"Home", "CablesRUs", "LeadsRUs", "TechIsUs", "gadgetsRUs"};
		for (int i = 0; i < location.length; i++)
		{
			if (s.equals(location[i]))
			{
				num = i;
			}
		}
		return num;
	}
	
	/********************************************************************
	 *SUBMODULE: printGraph
	 *IMPORT: none
	 *EXPORT: output (String)
	 *ASSERTION: prints out the graph.
	 *********************************************************************/
	public String printGraph()
	{
		String output = "";
		if (getCount() == 0)
		{
			output = "Please load up the store first.";
		}
		else 
		{
			for (int i = 0; i < vertices ; i++) 
			{
				LinkedList<DSAGraphWeight> list = adjacencylist[i];
				for (int j = 0; j < list.size() ; j++) 
				{
					output = output + getStringForNumber(i) + " is near " + getStringForNumber(list.get(j).destination) + " with distance " +  list.get(j).weight + "\n";
				}
			}
		}
		return output;
	}
	
	/********************************************************************
	 *SUBMODULE: printGraph
	 *IMPORT: loc1 (String), loc2 (String)
	 *EXPORT: output (String)
	 *ASSERTION: prints out the distance between two stores.
	 *********************************************************************/
	public String distance(String loc1, String loc2)
	{
		String output = "";
		if (getCount() == 0)
		{
			output = "Please load up the store first.";
		}
		else 
		{
			String store1, store2;
			for (int i = 0; i < vertices ; i++) 
			{
				LinkedList<DSAGraphWeight> list = adjacencylist[i];
				for (int j = 0; j < list.size() ; j++) 
				{
					store1 = getStringForNumber(i);
					store2 = getStringForNumber(list.get(j).destination);
					if (((store1.toLowerCase()).equals(loc1) && (store2.toLowerCase()).equals(loc2)) || ((store1.toLowerCase()).equals(loc2) && (store2.toLowerCase()).equals(loc1)))
					{
						output = "\n" + store1 + " is near " + store2 + " with distance " +  list.get(j).weight;
					}
				}
			}
			if (output.equals(""))
			{
				output = "No distance found between " + loc1 + " and " + loc2;
			}
		}
		return output;
	}

	/********************DSAGraphWeight Class********************/
	private class DSAGraphWeight implements Serializable
	{
		private int source;
		private int destination;
		private double weight;
		
		public DSAGraphWeight(int source, int destination, double weight)
		{
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
	}
}
