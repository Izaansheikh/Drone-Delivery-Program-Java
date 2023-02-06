/*
 *Author: Izaan Sheikh                              
 *Purpose: creating instance of FileManager class
 *Date: 15-03-2021                                    */

 /**** This class deals with file reading and writing (Normal & Serial) ****/
import java.io.*;
public class fileManager
{
    /************************************************************
	 *SUBMODULE: readInvt
	 *IMPORT: filename (String), ShipStorage storage
	 *EXPORT: none
	 *ASSERTION: reads inventory file.
     ************************************************************/
    public static void readInvt(String filename, DSAHashTable hash)
    {
        String line;   
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        try 
        {
            fileStrm = new FileInputStream(filename);   	    									//Open the file
            rdr = new InputStreamReader(fileStrm);		        									//Create a reader to read the stream
            bufRdr = new BufferedReader(rdr);		            										//To read the stream one line at a time
            lineNum = 0;
            line = bufRdr.readLine();			                												//Read the line and ignores it as it contains format
			line = bufRdr.readLine();			                												//Read the first useful line
            if (line == null)
            {
                System.out.println("File is Empty");
            }
            else
            {
                while (line != null)                            													//While not end-of-file, process and read lines
                {
					String [ ] tokens = line.split(",");
					hash.put(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                    lineNum++;
                    line = bufRdr.readLine();		             												//Read the next line
                }
            }
            fileStrm.close();				                    													//Clean up the stream 
			System.out.println("Inventories have been read and stored successfully.");
        }
        catch (IOException e) 
        {			                                           															//MUST catch IOExceptions
            if (fileStrm != null) 
            {		                                            														//Clean up the stream if it was opened
                try 
                { 
                    fileStrm.close(); 
                } 
                catch (IOException ex2) 
                { 
                } 																										//We can’t do anything more! 
            }
            System.out.println("Error while processing the file: " + e.getMessage());		//Or do a throw
        }   
    }
	
	/************************************************************
	 *SUBMODULE: readStore
	 *IMPORT: filename (String), ShipStorage storage
	 *EXPORT: none
	 *ASSERTION: reads inventory file.
     ************************************************************/
    public static void readStore(String filename, DSAGraph graph) 
    {
        String line;   
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        try 
        {
            fileStrm = new FileInputStream(filename);   	    									//Open the file
            rdr = new InputStreamReader(fileStrm);		        									//Create a reader to read the stream
            bufRdr = new BufferedReader(rdr);		            										//To read the stream one line at a time
            lineNum = 0;
            line = bufRdr.readLine();			                												//Read the first line
            if (line == null)
            {
                System.out.println("File is Empty");
            }
            else
            {
                while (line != null)                            													//While not end-of-file, process and read lines
                {
					String [ ] tokens = line.split(",");
					graph.addEdge(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
                    lineNum++;
                    line = bufRdr.readLine();		             												//Read the next line
                }
            }
            fileStrm.close();				                    													//Clean up the stream 
			System.out.println("Stores have been read and stored successfully.");
        }
        catch (IOException e) 
        {			                                           															//MUST catch IOExceptions
            if (fileStrm != null) 
            {		                                            														//Clean up the stream if it was opened
                try 
                { 
                    fileStrm.close(); 
                } 
                catch (IOException ex2) 
                { 
                } 																										//We can’t do anything more! 
            }
            System.out.println("Error while processing the file: " + e.getMessage());		//Or do a throw
        }   
    }
		
	/************************************************************
	 *SUBMODULE: readOrder
	 *IMPORT: filename (String), Order order
	 *EXPORT: none
	 *ASSERTION: reads inventory file.
     ************************************************************/
    public static void readOrder(String filename, Order order) 
    {
        String line;   
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        try 
        {
            fileStrm = new FileInputStream(filename);   	    									//Open the file
            rdr = new InputStreamReader(fileStrm);		        									//Create a reader to read the stream
            bufRdr = new BufferedReader(rdr);		            										//To read the stream one line at a time
            lineNum = 0;
            line = bufRdr.readLine();																			//Ignore the Date as not needed in program
			line = bufRdr.readLine();																			//Ignore the Contact as not needed in program
			line = bufRdr.readLine();																			//Ignore the Address as not needed in program
			line = bufRdr.readLine();			                												//Read the first useful line
            if (line == null)
            {
                System.out.println("File is Empty");
            }
            else
            {
                while (line != null)                            													//While not end-of-file, process and read lines
                {
					String [ ] tokens = line.split(",");
					order.addOrder(tokens[0], Integer.parseInt(tokens[1]));
                    lineNum++;
                    line = bufRdr.readLine();		             												//Read the next line
                }
            }
            fileStrm.close();				                    													//Clean up the stream 
			System.out.println("Order has been read and stored successfully.");
        }
        catch (IOException e) 
        {			                                           															//MUST catch IOExceptions
            if (fileStrm != null) 
            {		                                            														//Clean up the stream if it was opened
                try 
                { 
                    fileStrm.close(); 
                } 
                catch (IOException ex2) 
                { 
                } 																										//We can’t do anything more! 
            }
            System.out.println("Error while processing the file: " + e.getMessage());		//Or do a throw
        }   
    }
	
    /************************************************************
	 *SUBMODULE: writeFile
	 *IMPORT: filename (String), ShipStorage storage
	 *EXPORT: none
	 *ASSERTION: writes the file.
     ************************************************************/
    public static void writeFile(String filename, DSAGraph store, DSAHashTable invt, Order order)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
		String data1 = store.printGraph();
		String data2 = invt.export(order);
		String data3 = invt.route(order, store);
        try 
        {
            fileStrm = new FileOutputStream(filename);              								//Open the file for writing
            pw = new PrintWriter(fileStrm);                         										//Initialise writer
			pw.println("               ***Welcome to report***\n\nStore Information:");
			pw.append(data1);
            pw.println("\nInventory Information:");
			pw.append(data2);
			pw.println("\nRoute taken to deliver the order:");
			pw.append(data3);
            pw.close();                                             												//Clean up the stream
            System.out.println("Data has been processed and saved to the file: " + filename);
        }
        catch(IOException e)                                        											//MUST catch IOExceptions
        {
            if( fileStrm != null )                                  												//Clean up the stream if it was opened
            {
                try
                { 
                    fileStrm.close(); 
                } 
                catch (IOException ex2) 
                {
                }                                                   // We can’t do anything more!
            }
            throw new IllegalArgumentException("Error in writing to file: " + e.getMessage());//Or do a throw
        }
    }
	
	/************************************************************
	 *SUBMODULE: loadInvt
	 *IMPORT: filename (String)
	 *EXPORT: none
	 *ASSERTION: loads a serialized file.
     ************************************************************/  
	public static DSAHashTable loadInvt(String filename) throws IllegalArgumentException
	{
		FileInputStream fileStrm;
		ObjectInputStream objStrm;
		DSAHashTable inObj;
		inObj = null;
		try 
		{
			fileStrm = new FileInputStream(filename);												//Underlying stream
			objStrm = new ObjectInputStream(fileStrm);											//Object serialization stream
			inObj = (DSAHashTable)objStrm.readObject();											//Deserialize. Note the cast is needed
			objStrm.close();																						//Clean up
			System.out.println("Serialized inventory has been loaded.");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println("DSAHashTable not found" + e.getMessage());
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Loading failed.\nReason: File not found " + e.getMessage());
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Unable to load object from file");
		}
		return inObj;
	}
	
	/************************************************************
	 *SUBMODULE: saveInvt
	 *IMPORT: objToSave (DSABinarySearchTree), filename (String)
	 *EXPORT: none
	 *ASSERTION: save a serialized file.
     ************************************************************/  
	public static void saveInvt(DSAHashTable objToSave, String filename)
	{
		FileOutputStream fileStrm;
		ObjectOutputStream objStrm;
		try 
		{
			fileStrm = new FileOutputStream(filename);												//Underlying stream
			objStrm = new ObjectOutputStream(fileStrm);											//Object serialization stream
			objStrm.writeObject(objToSave);																//Serialize and save to filename. This will also save the DSALinkedList’ contained Location object
			objStrm.close();																						//Clean up
			System.out.println("Serialized inventory has been saved.");
		}
		catch (FileNotFoundException e)
		{
			throw new IllegalArgumentException("File not found." + e.getMessage());
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException("Input mismatch.  " + e.getMessage());
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Unable to save object to file");
		}
	}

	/************************************************************
	 *SUBMODULE: loadStore
	 *IMPORT: filename (String)
	 *EXPORT: none
	 *ASSERTION: loads a serialized file.
     ************************************************************/  
	public static DSAGraph loadStore(String filename) throws IllegalArgumentException
	{
		FileInputStream fileStrm;
		ObjectInputStream objStrm;
		DSAGraph inObj;
		inObj = null;
		try 
		{
			fileStrm = new FileInputStream(filename);												//Underlying stream
			objStrm = new ObjectInputStream(fileStrm);											//Object serialization stream
			inObj = (DSAGraph)objStrm.readObject();												//Deserialize. Note the cast is needed
			objStrm.close();																						//Clean up
			System.out.println("Serialized store has been loaded.");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println("DSAGraph not found" + e.getMessage());
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Loading failed.\nReason: File not found " + e.getMessage());
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Unable to load object from file");
		}
		return inObj;
	}
	
	/************************************************************
	 *SUBMODULE: saveStore
	 *IMPORT: objToSave (DSABinarySearchTree), filename (String)
	 *EXPORT: none
	 *ASSERTION: save a serialized file.
     ************************************************************/  
	public static void saveStore(DSAGraph objToSave, String filename)
	{
		FileOutputStream fileStrm;
		ObjectOutputStream objStrm;
		try 
		{
			fileStrm = new FileOutputStream(filename);												//Underlying stream
			objStrm = new ObjectOutputStream(fileStrm);											//Object serialization stream
			objStrm.writeObject(objToSave);																//Serialize and save to filename. This will also save the DSALinkedList’ contained Location object
			objStrm.close();																						//Clean up
			System.out.println("Serialized store has been saved.");
		}
		catch (FileNotFoundException e)
		{
			throw new IllegalArgumentException("File not found." + e.getMessage());
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException("Input mismatch.  " + e.getMessage());
		}
		catch (Exception e) 
		{
			throw new IllegalArgumentException("Unable to save object to file");
		}
	}
}


