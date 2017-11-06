package Project3GitDoneRight;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private PrintWriter invoice;
    private ArrayList<BikePart> invoiceList = new ArrayList<>();
    private File invFile;
    private boolean created = false;
    private double cost = 0.0;

    /**
     * @param asscName Name of the associate creating the invoice
     * This class manages the formatting and output of arrays.
     * it is sales associate specific
     */
    public Invoice(String asscName) throws Exception {
        invoice = new PrintWriter(new FileWriter(asscName + "invoice.txt", true));//Should the append be true? that is a question i will answer later
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        invoice.println("Sales Invoice for " + asscName + "'s Van Sales, " + dateFormat.format(date));
        invoice.println("PartName   PartNumber  Price   Sales   Price    Quantity   TotalCost");
        created = true;

    }

    /**
     * @param Part The part that you want to add to your invoice
     * This method acts as .add for the invoice, could probubly rename it, but then it is less specifice
     */
    public void add(BikePart Part) {
        if (created) {
            invoiceList.add(Part);
            cost += Part.getPrice();
        }
        else{
            System.out.println("No invoice has been started for this user. Please Contact the Coders who gooned this up");
        }

    }

    /**
     *
     * @param name The Name of the Client/Person buying the parts
     * @return Returns a File With the invoice in it.CURRENTLY TRYING TO HAVE IT RETURN THE COST AS WELL in an ArrayList of objects
     * im not sure if the For Each loop works yet.
     */
    public ArrayList<Object> close(String name) {
        for(BikePart a:invoiceList){
            invoice.println(a);
        }
        invoice.println("Parts Purchased by "+name+" for $"+ cost+"\n");
        invoice.close();
        ArrayList<Object>retList = new ArrayList<>();
        retList.add(invFile);
        retList.add(cost);
        return retList;
    }
}



