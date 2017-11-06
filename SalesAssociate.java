package Project3GitDoneRight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesAssociate extends Employee {

    /**
     * @author Joseph Bermingham
     * This Should Sell your part by name or number
     * Should update the van, and add to a file that is already created with todays date
     */

    public void Sell(String partName, int partNumber, int quantity) {
        try {
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
        } catch (IOException e) {
            System.out.println("There is an io exception in the sell class of sales associate");
        }
    }

    /**
     * @author Joseph Bermingham
     * when you enter this method it gets the text from the text field and Adds the contents of a file to add to this sales associate van
     */
    //todo make is so that when you add duplicates it adds their quantity instead of duplicating them
    void LoadFile(String fileName) {
        System.out.println("SalesAssociate.LoadFile has been entered");
        File loadFile = new File(fileName);
        try {
            System.out.println("Try has been entered");
            Scanner input = new Scanner(loadFile);
            ArrayList<BikePart> addList = new ArrayList<>();
            ArrayList<BikePart> check = this.moveToList();
            // System.out.println(check.get(0).toString());
            while (input.hasNext()) {
                String partString = input.nextLine();
                // System.out.println(t + "       t");
                String[] broken = partString.split(",");
                //adds a bike part to the addList
                addList.add((new BikePart(broken[0],
                        Integer.parseInt(broken[1]),
                        Double.parseDouble(broken[2]), Double.parseDouble(broken[3]),
                        Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5]))));
                //  System.out.println(partString + "This is tester in SAsc LoadFile");
            }

            for (BikePart in : addList) {
                boolean wasAdded = false;

                for (BikePart wh : check) {
                    if (wh != null)
                        if (wh.getName().equalsIgnoreCase(in.getName()) && in.getNumber() == wh.getNumber()) {
                            wh.setQuantity(wh.getQuantity() + in.getQuantity());
                            wh.setPrice(in.getTruePrice());
                            wh.setonSale(in.getonSale());
                            wh.setSalesPrice(in.getSale());
                            wasAdded = true;
                        }
                }
                if (!wasAdded) check.add(in);
            }
            for (BikePart add : check) {
                writer.println(add.toString());
                System.out.println(add.toString() + "A");
            }


            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found. Please make sure you are using the correct file in the correct location");
        }


    }

    private PrintWriter writer;
    private Invoice thisInvoice;

    /**
     * @param fName first name of sa
     * @param lName last name
     * @param uName username
     * @param Pword password
     * @param Email email
     * @author Joseph Bermingham
     * creates a sales associate and their invoice
     * pretty sure that the writer in here needs to not append
     */
    public SalesAssociate(String fName, String lName, String uName, String Pword, String Email) {
        super(fName, lName, uName, Pword, Email);
        try {
            writer = new PrintWriter(new FileWriter(fName + ".txt", false));
            Invoice thisInvoice = new Invoice(fName);
        } catch (java.io.FileNotFoundException gg) {
            System.out.println("uhhhh");
        } catch (java.io.IOException rr) {
            System.out.println("darn");
        }
    }

    /**
     * @return ArrayList<BikePart> of what you have in your warehouse
     * @author Joseph Bermingham
     * This is a private utility class
     */
    private ArrayList<BikePart> moveToList() {
        System.out.println("moveToList has been entered");
        ArrayList<BikePart> retList = new ArrayList<>();

        Scanner whlooker = null;
        try {
            whlooker = new Scanner(new File(this.getFirstName() + ".txt"));
        } catch (java.io.IOException g) {
            System.out.println("java.io.IOException in line 140 of the Sales Associate class.\n salesAssociatesName.txt not found");
        }
        try {
            while (whlooker.hasNext()) {
                String partString = whlooker.nextLine();

                // System.out.println(t + "       t");
                String[] broken = partString.split(",");
                //adds a bike part to the addList
                retList.add(new BikePart(broken[0], Integer.parseInt(broken[1]), Double.parseDouble(broken[2]), Double.parseDouble(broken[3]), Boolean.parseBoolean(broken[4]), Integer.parseInt(broken[5])));
                System.out.println(partString + "This is tester in Warehouse Manager LoadFile Method");
            }
        } catch (NullPointerException e) {
            System.out.println("null pointer exception in WarehouseManager line 114 \n Warehousedb.txt not found, whlooker Uninitialised");
        }

        return retList;
    }

    /**
     * Main method, for testing, replaced by SalesAsscTester
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