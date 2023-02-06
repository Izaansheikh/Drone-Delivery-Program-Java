/*
 *Author: Izaan Sheikh                              
 *Purpose: creating instance of Order class
 *Date: 31-03-2021                                    */

 /**** This class is implemented using a idea from submission for another unit (OOPD) ****/
public class Order
{
    //private class fields
    private String prod[ ];
	private int qty[ ];
	private int count;

    /************************************************************
     *Alternate Constructor:
	 *IMPORT: maxCount (Integer)
	 *EXPORT: none
	 *ASSERTION: Initializes the variables.
     ************************************************************/
    public Order(int maxCount)
    {
		prod = new String [maxCount];	
		qty = new int [maxCount];
		count = 0;
    }

    //MUTATORS
    /************************************************************
     *SUBMODULE: addProd
	 *IMPORT: inProd (String)
	 *EXPORT: none
	 *ASSERTION: adds the orders product and quantity to the array.
     ************************************************************/
    public void addOrder(String inProd, int inQty)
    {
	    prod[count] = inProd;
	    qty[count] = inQty;
		count++;
    }
	
    //ACCESSORS
    public String getProd(int i)
    {
	    return prod[i];
    }

    public int getQty(int i)
    {
	    return qty[i];
    }

    public int getCount()
    {
	    return count;
    }
}



