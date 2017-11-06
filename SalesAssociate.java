package Project3GitDoneRight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sample Skeleton for 'SalesAssociate.fxml' Controller Class
 */

public class SalesAssociate extends Employee {

    /**
     * This Should Sell your part by name or number
     * Should update the van, and add to a file that is already created with todays date
     */

    void Sell(String partName, int partNumber, int quantity) throws Exception {
        //actually get file, but i am in limbo on that
        ArrayList<BikePart> sellList = new ArrayList<>();
//Add everything from my file onto an arraylist
        String a = super.getFirstName();
        double cost = 0.0;
        Scanner in = new Scanner(new File(a + ".txt"));
        while (in.hasNext()) {
            String t = in.nextLine();
            String[] broken = t.split(",");
            sellList.add(new
                    BikePart(broken[0], Integer.parseInt(broken[1]),
                    Double.parseDouble(broken[2]), Double.parseDouble(broken[3]),
                    Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5])));
        }
        /**
         * if the part name field is empty use this version of seller to decrement the quantity and get the cost of the sale
         * */
        if (partName.equals("")) {
            for (int i = 0; i < sellList.size(); i++) {
                if (sellList.get(i).getNumber() == partNumber) {
                    int g = sellList.get(i).getQuantity();
                    sellList.get(i).setQuantity(g - quantity);
                    cost += (sellList.get(i).getPrice() * quantity);
                    thisInvoice.add(sellList.get(i));
                }
            }
        }

        /**
         * if part number is "empty" (-1) use this one
         * */
        else {
            for (int i = 0; i < sellList.size(); i++) {
                if (sellList.get(i).getName() == partName) {
                    int g = sellList.get(i).getQuantity();
                    sellList.get(i).setQuantity(g - quantity);
                    cost += (sellList.get(i).getPrice() * quantity);
                    thisInvoice.add(sellList.get(i));
                }
            }
        }


        System.out.println();
        for (int i = 0; i < sellList.size(); i++) {
            System.out.println(sellList.get(i));
            // invoice.println(sellList.get(i));
        }
        System.out.println(cost + " cost");

    }

    /**
     * when you enter this method it gets the text from the text field and Adds the contents of a file to add to this sales associate van
     */
    @FXML
    void LoadFile(String fileName) {
        System.out.println("SalesAssociate.LoadFile has been entered");
        File loadFile = new File(fileName);
        try {
            Scanner in;
            in = new Scanner(loadFile);
            ArrayList<String> addList = new ArrayList<>();
            while (in.hasNext()) {
                String partString = in.nextLine();

                // System.out.println(t + "       t");
                String[] broken = partString.split(",");
                //adds a bike part to the addList
                addList.add((new BikePart(broken[0],
                        Integer.parseInt(broken[1]),
                        Double.parseDouble(broken[2]), Double.parseDouble(broken[3]),
                        Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5])).toString()));
                System.out.println(partString + "This is tester in SAsc LoadFile");
            }

            for (int i = 0; i < addList.size(); i++) {
                writer.println(addList.get(i));
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    private PrintWriter writer;
    private PrintWriter invoice;
    private Invoice thisInvoice=null;

    public SalesAssociate(String fName, String lName, String uName, String Pword, String Email) throws Exception {
        super(fName, lName, uName, Pword, Email);
        try {
            writer = new PrintWriter(new FileWriter(fName + ".txt", true));
            Invoice thisInvoice = new Invoice(fName);
        } catch (java.io.FileNotFoundException gg) {
            System.out.println("uhhhh");
        } catch (java.io.IOException rr) {
            System.out.println("darn");
        }
    }

    /**
     * Main method, for testing
     */
    public static void main(String[] args) throws Exception {
        SalesAssociate andy = new SalesAssociate("andy", "b", "c", "d", "email");
        Invoice andyInoice = new Invoice("Andy");
        andy.LoadFile("test.txt");
        andy.Sell("ZZZ_APPEARS_LAST", -1, 2);
        andy.Sell("", 1, 2);
        andyInoice.close("tom");
    }
}