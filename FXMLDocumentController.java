import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Josh
 */
public class FXMLDocumentController implements Initializable {
    private SalesAssociate steve;
    @FXML
    private TextField partInfo;

    @FXML
    private TextField testbpDS;

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private TextArea display;

    @FXML
    private Button changescene;

    @FXML
    private Button Login;

    @FXML
    private Button Return;
    @FXML
    private Button rt;

    @FXML
    private TextField PartName;

    @FXML
    private TextField PartNumber;

    @FXML
    private TextField LoadFileName;

    @FXML
    private TextField Quantity;

    @FXML
    private Button logOut;

    @FXML
    void Sell(ActionEvent event) throws Exception {
        steve = new SalesAssociate("a", "b", "c", "d", "email");
        //steve.Sell();
        System.out.println(steve.getFirstName());
        if ((PartName.getText().isEmpty())) {
            steve.Sell("", Integer.parseInt(PartNumber.getText()), Integer.parseInt(Quantity.getText()));
        }else if((PartNumber.getText().isEmpty())){
            steve.Sell(PartName.getText(), -1, Integer.parseInt(Quantity.getText()));
        }
        else if(!(PartNumber.getText().isEmpty()&&PartName.getText().isEmpty())){
            steve.Sell(PartName.getText(), Integer.parseInt(PartNumber.getText()), Integer.parseInt(Quantity.getText()));
        }
        else{
            display.appendText("Please put in either a name or a number");
        }
    }

    @FXML
    void LoadFIle(ActionEvent event) {
        System.out.println("load");
    }

    @FXML
    void PrintInvoice(ActionEvent event) {
        System.out.println("prnt");
    }

    private ArrayList<BikePart> bpDS = new ArrayList<>();

    @FXML
    private void changeScene(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == Return) {
            //get reference to the button's stage
            stage = (Stage) Return.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        } else {
            System.out.println("Error");
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;

        if (event.getSource() == Login && Username.getText().equals("officeman") && Password.getText().equals("pass")) {
            //get reference to the button's stage
            stage = (Stage) Login.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        } else if (event.getSource() == Login && Username.getText().equals("sm") && Password.getText().equals("p")) {
            stage = (Stage) Login.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("SalesAssociate.fxml"));
        } else
            System.out.print("Error loading fxml");
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * findByName compares all BikeParts in the warehouse data set and returns a BikePart if its name is equal to the name given.
     *
     * @param name
     * @return returns BikePart b
     */
    private BikePart findByName(String name) {
        for (BikePart b : bpDS) //bpDS is the static arraylist of bikeparts
            if (b.getName().equals(name))
                return b;
        return null;
    }

    /**
     * readBPDS reads in the initial warehouse data set from a file given by the user.
     * It also displays all BikeParts in the warehouse and denotes what each value is.
     *
     * @param event
     * @throws FileNotFoundException
     * @throws IOException
     */
    @FXML
    private void testBPDS(ActionEvent event) throws FileNotFoundException, IOException {
        String s = testbpDS.getText();
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
                display.appendText("Part Name: " + pa[0] + "," + " Part Number: " + result + "," + " Price: $" + result2 + "," + " Sales Price: $" + result3 + "," + " On Sale: " + result4 + "," + " Quantity: " + result5 + "\n");
            }
        }
    }


    /**
     * Displays a specific BikePart by comparing user input to BikePart names.
     *
     * @param event
     */
    @FXML
    public void examineButtonMethod(ActionEvent event) {
        String s = partInfo.getText();

        for (BikePart bp : bpDS)
            if (bp.getName().equals(s)) {
                if (bp.getonSale() == true)
                    display.appendText("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getSale() + "," + " Quantity: " + bp.getQuantity() + "\n");
                else
                    display.appendText("Part Name: " + bp.getName() + "," + " Current Price: $" + bp.getPrice() + "," + " Quantity: " + bp.getQuantity() + "\n");
            }
    }

    @FXML
    public void checkQuant(ActionEvent event) {

        for (BikePart bp : bpDS)
            if (bp.getQuantity() <= 10 && bp.getQuantity() > 5) {
                display.appendText("Quantity of " + bp.getName() + " is " + bp.getQuantity() + "," + " order more." + "\n");
            } else if (bp.getQuantity() <= 5) {
                display.appendText("Quantity of " + bp.getName() + " is " + bp.getQuantity() + "," + " order at least 20 now." + "\n");
            }
    }

    @FXML
    public void Quit(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

