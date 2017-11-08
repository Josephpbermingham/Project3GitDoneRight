package Project3GitDoneRight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;

public class officeManager extends Employee {
    private static ArrayList<BikePart> bpDS = new ArrayList<>();
    
    public officeManager(String a, String b, String c, String d, String e) {
        super(a, b, c, d, e);
    }
    
     /**
     * findByName compares all BikeParts in the warehouse data set and returns a BikePart if its name is equal to the name given.
     * @param name
     * @return returns BikePart b 
     */        
    private static BikePart findByName(String name){
        for (BikePart b : bpDS)
            if (b.getName().equals(name))  
                    return b;
        return null;
    }
     /**
     * this method goes through the bike part warehouse and looks for parts with less than a hardcoded quantity (10)
     * @param bpDS
     * //todo addInv the ability to create a file of the needed parts
     */
    public void checkQuant(ArrayList<BikePart> bpDS) {
        for (BikePart bp : bpDS)
            if (bp.getQuantity() <= 10 && bp.getQuantity() > 5) {
                System.out.println("Quantity of " + bp.getName() + " is " + bp.getQuantity() + "," + " order more." + "\n");
            } else if (bp.getQuantity() <= 5) {
                System.out.println("Quantity of " + bp.getName() + " is " + bp.getQuantity() + "," + " order at least 20 now." + "\n");
            }
    }
    // todo add methods for by partname and quantity
        public String examineButtonMethodname(String partname, ArrayList<BikePart> bpDS) {
        for (BikePart bp : bpDS)
            if (bp.getName().equals(partname)) {
                if (bp.getonSale())
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getSale() + "," + " Quantity: " + bp.getQuantity() + "\n");
                else
                    return
                            ("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getPrice() + "," + " Quantity: " + bp.getQuantity() + "\n");
            }
        return("");
    }
        
        private void testBPDS(ActionEvent event) throws IOException {
        String s = "test.txt";
        File f = new File(s);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) { //reads the file given by the user in the first textfield
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pa = line.split(",");
                int result = Integer.parseInt(pa[1]);
                double result2 = Double.parseDouble(pa[2]);
                double result3 = Double.parseDouble(pa[3]);
                Boolean result4 = Boolean.valueOf(pa[4]);
                int result5 = Integer.parseInt(pa[5]);
                BikePart b = new BikePart(pa[0], result, result2, result3, result4, result5);

                BikePart found = findByName(pa[0]);
                if (findByName(pa[0]) == null) {         //ensures that bikeparts with unique names are added to array
                    bpDS.add(b);
                } else {                              //doesn't actually affect file, as no prints are made, only displays and initializes BPDS.
                    found.setPrice(b.getPrice());
                    found.setSalesPrice(b.getSale());
                    found.setonSale(b.getonSale());
                    found.setQuantity(b.getQuantity());
                }
                System.out.println("Part Name: " + pa[0] + "," + " Part Number: " + result + "," + " Price: $" + result2 + "," + " Sales Price: $" + result3 + "," + " On Sale: " + result4 + "," + " Quantity: " + result5 + "\n");
            }
        }
    }
    
    
}
