/*
 *Author: Izaan Sheikh                              
 *Purpose: creating instance of report class
 *Date: 30-03-2021                                    */

 /**** This class deals with report mode ****/
public class report
{
	//Class Fields
	private DSAHashTable invt;
	private DSAGraph store;
	private Order order;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
	 *EXPORT: address of new report object
     *ASSERTION: 
     ************************************************************/
    public report()
    {
		invt = new DSAHashTable(2);
		store = new DSAGraph(10);
		order = new Order(20);
    }
	
	public void createReport(String storeFile, String invtFile, String orderFile)
	{
        fileManager.readStore(storeFile, store);
		fileManager.readInvt(invtFile, invt);
		fileManager.readOrder(orderFile, order);
		fileManager.writeFile("Report.csv", store, invt, order);
	}
}