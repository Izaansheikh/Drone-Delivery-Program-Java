/*
 *Author: Izaan Sheikh                              
 *Purpose: creating DSAHashTable class
 *Date: 26-02-2021                                    */
 
 /**** This class is taken from Practical 7 submitted by me, however some additional functionalities have been added for assignment needs ****/
import java.io.*;
public class DSAHashTable implements Serializable
{
	//class constants
	public static final double UPPERBOUND = 0.75;
	public static final double LOWERBOUND = 0.5;
	
    //private class fields
	private DSAHashEntry[ ] hashArray;
	private int count;
	
	/************************************************************
     *Alternative Constructor:
     *IMPORT: tableSize (Integer)
	 *EXPORT: none
     *ASSERTION: Initializes the variables.
     *************************************************************/
	public DSAHashTable(int tableSize)
	{
		int actualSize = nextPrime(tableSize);
		hashArray = new DSAHashEntry[actualSize];
		for (int i = 0; i < actualSize; i++)
		{
			hashArray[i] = new DSAHashEntry();
		}
	}
	
	//ACCESSORS
	public int getCount()
	{
		return count;
	}

    /********************************************************************
     *SUBMODULE: put
	 *IMPORT: inKey (String), inValue (Integer)
	 *EXPORT: none
	 *ASSERTION: inserts value according to key in hash table.
     *********************************************************************/
	public void put(String inStore, String inKey, int inValue)
	{
		if (getLoadFactor() > UPPERBOUND)
		{
			resize(hashArray.length);
			int hashVal = hash(inKey);
			while ((hashArray[hashVal] != null) && (!hashArray[hashVal].getKey().equals("")))
			{
				hashVal = hashVal + 1;
				hashVal %= hashArray.length;
			}
			hashArray[hashVal] = new DSAHashEntry(inStore, inKey, inValue);
			count = count + 1;
		}
		else
		{
			int hashVal = hash(inKey);
			while ((hashArray[hashVal] != null) && (!hashArray[hashVal].getKey().equals("")))
			{
				hashVal = hashVal + 1;
				hashVal %= hashArray.length;
			}
			hashArray[hashVal] = new DSAHashEntry(inStore, inKey, inValue);
			count = count + 1;
		}
	}
	
    /********************************************************************
     *SUBMODULE: get
	 *IMPORT: inKey (String)
	 *EXPORT: retValue (Integer)
	 *ASSERTION: finds the value according to key.
     *********************************************************************/
	public int get(String inKey)
	{
		int hashIndex = hash(inKey);
		int origIdx = hashIndex;
		boolean found = false;
		boolean giveUp = false;
		int retValue;
		while ((!found) && (!giveUp))
		{
			if (hashArray[hashIndex].getState() == 0)
			{
				giveUp = true;
			}
			else if (hashArray[hashIndex].getKey().equals(inKey))
			{
				found = true;
			}
			else 
			{
				hashIndex = (hashIndex + 1) % hashArray.length;
				if (hashIndex == origIdx)
				{
					giveUp = true;
				}
			}
		}
		if (!found)
		{
			throw new IllegalArgumentException("No value not found with key: " + inKey);
		}
		retValue = hashArray[hashIndex].value;
		return retValue;
	}

    /********************************************************************
     *SUBMODULE: remove
	 *IMPORT: inKey (String)
	 *EXPORT: temp (DSAHashEntry)
	 *ASSERTION: removes the value according to key.
     *********************************************************************/
	public DSAHashEntry remove(String inKey)
	{
		int hashVal = hash(inKey);
		DSAHashEntry temp = null;
		while ((hashArray[hashVal] != null) && (!hashArray[hashVal].getKey().equals("")))
		{
			if (hashArray[hashVal].getKey().equals(inKey))
			{
				temp = hashArray[hashVal];
				hashArray[hashVal] = null;
				count = count - 1;
			}
			hashVal = hashVal + 1;
			hashVal %= hashArray.length;
		}
		if (temp == null)
		{
			throw new IllegalArgumentException("Key not found.");
		}
		resize(hashArray.length);
		return temp;
	}

    /********************************************************************
     *SUBMODULE: getLoadFactor
	 *IMPORT: none
	 *EXPORT: loadFactor (Double)
	 *ASSERTION: calculates loadFactor.
     *********************************************************************/
	public double getLoadFactor()
	{
		double loadFactor;
		loadFactor = (double)count/hashArray.length;
		return loadFactor;
	}
	
    /********************************************************************
     *SUBMODULE: export
	 *IMPORT: none
	 *EXPORT: output (String)
	 *ASSERTION: displays hash table.
     *********************************************************************/
	public String export(Order order)
	{
		String output = "";
		int oCount = order.getCount();
		if (oCount == 0)
		{
			output = "Please load up a order first.";
		}
		else
		{
			if (getCount() == 0)
			{
				output = "Please load up the inventories first.";
			}
			else
			{
				output = "Store   \tProduct \tStockOnHand\n";
				for (int i = 0; i < hashArray.length; i++)
				{
					if ((hashArray[i] != null) && (!hashArray[i].getKey().equals("")))
					{
						String store = hashArray[i].getStore();
						String key = hashArray[i].getKey();
						int value = hashArray[i].getValue();
						for (int j = 0; j < oCount; j++)
						{
							if (key.equals(order.getProd(j)))
							{
								output = output + (store + "\t" + key + "   \t" + value + "\n");
							}
						}
					}
				}
			}
		}
		return output;
	}
		
    /********************************************************************
     *SUBMODULE: displayByName
	 *IMPORT: name (String)
	 *EXPORT: output (String)
	 *ASSERTION: displays hash table according to input.
     *********************************************************************/
	public String displayByName(String name, Order order)
	{
		boolean found = false;
		String output = "";
		int oCount = order.getCount();
		if (oCount == 0)
		{
			output = "Please load up a order first.";
		}
		else 
		{
			if (getCount() == 0)
			{
				output = "Please load up the inventories first.";
			}
			else
			{
				for (int i = 0; i < hashArray.length; i++)
				{
					if ((hashArray[i] != null) && (!hashArray[i].getKey().equals("")))
					{
						String store = hashArray[i].getStore();
						if ((store.toLowerCase()).equals(name))
						{
							String key = hashArray[i].getKey();
							int value = hashArray[i].getValue();
							found = true;
							for (int j = 0; j < oCount; j++)
							{
								if (key.equals(order.getProd(j)))
								{
									output = output + (store + "\t" + key + "   \t" + value + "\n");
								}
							}
						}
					}
				}
				if (output.equals("") && found)
				{
					output = "The store " + name + " is irrelevant for this order.\n(No products needed from that store.)";
				}
				else
				{
					if (!found)
					{
						output = "No store found using the name: " + name;
					}
					else 
					{
						System.out.println("Store\t\tProduct\t\tStockOnHand");
					}
				}
			}
		}
		return output;
	}
			
    /********************************************************************
     *SUBMODULE: route
	 *IMPORT: order (Order), store (DSAGraph)
	 *EXPORT: output (String)
	 *ASSERTION: displays the route for order.
     *********************************************************************/
	public String route(Order order, DSAGraph store)
	{
		String output = "";
		String visited[ ] = new String[20];																		//array to make sure drone is efficient and no store is visited twice
		int vCount = 0;
		String prod = "";
		if (getCount() == 0)
		{
			output = "Please load up the inventories first.";
		}
		else 
		{
			int sCount = store.getCount();
			if (sCount == 0)
			{
				output = "Please load up a store first.";
			}
			else
			{
				int oCount = order.getCount();
				if (oCount == 0)
				{
					output = "Please load up a order first.";
				}
				else
				{
					for (int i = 0; i < hashArray.length; i++)
					{
						if ((hashArray[i] != null) && (!hashArray[i].getKey().equals("")))
						{
							for (int j = 0; j < oCount; j++)
							{
								String key = hashArray[i].getKey();
								if ((order.getProd(j)).equals(key))													//comparing product with inventory
								{
									String loc = hashArray[i].getStore();
									if (!prod.contains(order.getProd(j)))											//making sure one product is collected once
									{
										int value = hashArray[i].getValue();
										if (value >= order.getQty(j))												//if a store is low on quantity of a product, drone will visit another store
										{
											prod = prod + order.getProd(j) + " ";
										}
										visited[vCount] = loc;															//storing locations that are visited
										vCount++;
									}
								}
							}
						}
					}
					output = "Home -> ";
					for (int i = 0; i < vCount; i++)
					{
						if (!output.contains(visited[i]))
						{
							output = output + visited[i] + " -> ";
						}
					}
					output = output + "Home";
				}
			}
		}
		return output;
	}

    /********************************************************************
     *SUBMODULE: resize
	 *IMPORT: currSize (Integer)
	 *EXPORT: none
	 *ASSERTION: help the array in growing and shrinking.
     *********************************************************************/
	private void resize(int currSize) 
	{
		if (getLoadFactor() > UPPERBOUND)
		{
			int tableSize = 2 * currSize;
			int actualSize = nextPrime(tableSize);
			DSAHashEntry[ ] oldArray = hashArray;
			hashArray = new DSAHashEntry[tableSize];
			count = 0;
			for (int i = 0; i < oldArray.length; i++)
			{
				if ((oldArray[i] != null) && (!oldArray[i].getKey().equals("")))
				{
					put(oldArray[i].getStore(), oldArray[i].getKey(), oldArray[i].getValue());
				}
			}
		}
		else if (getLoadFactor() < LOWERBOUND)
		{
			int tableSize = currSize / 2;
			int actualSize = nextPrime(tableSize);
			DSAHashEntry[ ] oldArray = hashArray;
			hashArray = new DSAHashEntry[tableSize];
			count = 0;
			for (int i = 0; i < oldArray.length; i++)
			{
				if ((oldArray[i] != null) && (!oldArray[i].getKey().equals("")))
				{
					put(oldArray[i].getStore(), oldArray[i].getKey(), oldArray[i].getValue());
				}
			}
		}
	}
	
    /********************************************************************
     *SUBMODULE: hash
	 *IMPORT: inKey (String)
	 *EXPORT: hashIndex (Integer)
	 *ASSERTION: calculates hash index.
     *********************************************************************/
	private int hash(String inKey)
	{
		int hashIndex = 0;
		for (int ii = 0; ii < inKey.length(); ii++)
		{
			hashIndex = (31 * hashIndex) + inKey.charAt(ii);
			while (hashIndex < 0)
			{
				hashIndex = hashIndex + 1;
			}
		}
		return hashIndex % hashArray.length;
	}
	
	/****************************************************
	*SUBMODULE: stepHash
	*IMPORT: key (Integer)
	*EXPORT: hashStep (Integer)
	*ASSERTION: calculates step hash value.
	*****************************************************/
	private int stepHash(int inKey)
	{
		int maxStep = nextPrime(hashArray.length / 4);		
		int hashStep = maxStep - (inKey % maxStep);
		return hashStep;
	}
	
	/****************************************************
	*SUBMODULE: nextPrime
	*IMPORT: startVal (Integer)
	*EXPORT: primeVal (integer)
	*ASSERTION: calculates the next prime number.
	*****************************************************/	
	private int nextPrime(int startVal)
	{
		int primeVal;
		boolean isPrime;
		int ii;
		double rootVal;
		if (startVal % 2 == 0)
		{
			primeVal = startVal - 1;
		}
		else 
		{
			primeVal = startVal;
		}
		isPrime = false;
		do
		{
			primeVal = primeVal + 2;
			ii = 3;
			isPrime = true;
			rootVal = Math.sqrt(primeVal);
			do
			{
				if (primeVal % ii == 0)
				{
					isPrime = false;
				}
				else
				{
					ii = ii + 2;
				}
			}while ((ii <= rootVal) && (isPrime));
		}while (!isPrime);
		return primeVal;
	}

	/********************DSAHashEntry Class********************/
	private class DSAHashEntry implements Serializable
	{
		//private class fields
		private String key;
		private String store;
		private int value;
		private int state;
		
		/************************************************************
		 *Default Constructor:
		 *IMPORT: none
		 *EXPORT: none
		 *ASSERTION: Initializes the variables.
		 *************************************************************/
		public DSAHashEntry()
		{
			key = "";
			store = "";
			value = 0;
			state = 0;
		}
		
		/************************************************************
		 *Alternate Constructor:
		 *IMPORT: inValue (Integer)
		 *EXPORT: none
		 *ASSERTION: Assigns key to inKey, value to inValue and state to 1.
		 ************************************************************/
		public DSAHashEntry(String inStore, String inKey, int inValue)
		{
			store = inStore;
			key = inKey;
			value = inValue;
			state = 1;
		}

		//ACCESSORS
		public String getStore()
		{
			return store;
		}
		public String getKey()
		{
			return key;
		}
		public int getValue()
		{
			return value;
		}
		public int getState()
		{
			return state;
		}
	}
}