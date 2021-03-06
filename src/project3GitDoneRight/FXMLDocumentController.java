package project3GitDoneRight;

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
import javafx.scene.control.PasswordField;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBox;


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
    private PasswordField Password;
    @FXML
    private TextArea display;
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
    private Button createAccountButton;
    @FXML
    private TextField userTypeTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField deleteUserTextField;
    @FXML
    private TextField resetUserTextField;
    @FXML
    private TextField resetPasswordTextField;
    @FXML
    CheckBox nam;
    @FXML
    private CheckBox num;
    @FXML
    private CheckBox quant;
    //todo move methods to their respective classes. so that the controller isnt a giant monster of a class
    //This Employee is used to store the currently logged in employee
    private Employee usersName;//todo implement this so that we can reference individual employees from a list
    private ArrayList<BikePart> bpDS = new ArrayList<>();
    
    /**
     * This is the handler for the sales associate sell function
     *
     * @param event the button click
     * @throws Exception any number of exceptions could be thrown here
     */
    @FXML
    void Sell(ActionEvent event) throws Exception {
        steve = new SalesAssociate("a", "b", "bb", "c", "d", "email", "phonenumber");
        //steve.Sell();
        System.out.println(steve.getFirstName());
        if ((PartName.getText().isEmpty())) {
            steve.Sell("", Integer.parseInt(PartNumber.getText()), Integer.parseInt(Quantity.getText()));
        } else if ((PartNumber.getText().isEmpty())) {
            steve.Sell(PartName.getText(), -1, Integer.parseInt(Quantity.getText()));
        } else if (!(PartNumber.getText().isEmpty() && PartName.getText().isEmpty())) {
            steve.Sell(PartName.getText(), Integer.parseInt(PartNumber.getText()), Integer.parseInt(Quantity.getText()));
        } else {
            display.appendText("Please put in either a name or a number");
        }
    }

    /**
     * This is the handler for the sales associate adding something to their van
     *
     * @param event the button being clicked
     */
    @FXML
    void LoadFIle(ActionEvent event) {

        //todo create a salse associate such that i can access the correct one in this method
        SalesAssociate hardcoded = new SalesAssociate("default", "error", "error", "error", "ShouldntBeUsingThis", "really now. please stop", "password");
    }

    /**
     * This is the handler for the print invoice command. it only needs to call invoice.close on the Sales associate you want an invoice from
     *
     * @param event on button press
     */

    @FXML
    void PrintInvoice(ActionEvent event) {
        //needs to be able to reference a Sales associate. a capability we don't currently have
    }

    /**
     * @author joseph bermingham
     * @param event the button is clicked
     * this class will take from the text field and add that file to the warehouse
     */
    @FXML
void LoadToMain(ActionEvent event){
    //todo implement this
}
    /**
     * @author Josh Butler
     * This is how the fxml changes scenes from any user with a return button back to the login screen
     */
    @FXML
    private void changeScene(ActionEvent event) throws IOException {
        usersName = null;//should reset
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == Return) {
            //get reference to the button's stage
            stage = (Stage) Return.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Project3GitDoneRight/LogInScreen.fxml"));
        } else {
            System.out.println("Error");
        }
        //create a new scene with root and set the stage
        try {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception in line 145 of controller");
        }
    }

    /**
     * THis is the action that occurs when you click the logon button
     * @param event the click
     * @throws IOException When there is an io exception
     * @author Josh Butler
     * todo give it the ability to look through a list of logged on individuals to check for a valid user
     */
    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null; 
        //todo make this actually look through a list of users. needs to be System admin compliant
        //todo have these actually create a user and set the employee field to themselves so we can access it
        if (event.getSource() == Login && Username.getText().equals("officeman") && Password.getText().equals("pass")) {
            //get reference to the button's stage
            stage = (Stage) Login.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Project3GitDoneRight/OfficeManager.fxml"));
        } else if (event.getSource() == Login && Username.getText().equals("sm") && Password.getText().equals("p")) {
            stage = (Stage) Login.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Project3GitDoneRight/SalesAssociate.fxml"));
        } else if (event.getSource() == Login && Username.getText().equals("d") && Password.getText().equals("d")) {
            stage = (Stage) Login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Project3GitDoneRight/SysAdmin.fxml"));
        } else
            System.out.print("Error loading fxml");
        //create a new scene with root and set the stage
        //todo make sure that we check for a scene before we try and load it to prevent a null pointer exception
        try {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {
            System.out.println("no user by that name found");
        }
    }
    private final SysAdmin sysAdmin = new SysAdmin();
    /**
     * @param event the button is clicked
     * @author Zach Canton
     */
    @FXML
    void createAccountButton(ActionEvent event) {
        
        
        String firstName = userTypeTextField.getText();
        String lastName = lastNameTextField.getText();
        String userType = usernameTextField.getText();
        String username =  lastNameTextField.getText() + usernameTextField.getText(); //tells use the type of employee someone is
        String pass = passwordTextField.getText();
        String email = emailTextField.getText();
        String phoneNum = phoneNumberTextField.getText();

        sysAdmin.addUser(firstName, lastName, userType, username, pass, email, phoneNum);
        
        
        //SysAdmin.addUser(userType, phoneNum, email, username, pass);
    }

    /**
     * This should be the button that deletes an account that you have already added
     *
     * @param event when the button is clicked
     * @author zach caton
     */
    @FXML
    void deleteAccountButton(ActionEvent event) {
        String usernameDelete = deleteUserTextField.getText();
        //SysAdmin delAdmin = new SysAdmin();
        sysAdmin.deleteUser(usernameDelete);
    }

    /**
     * Changes the password of a user
     *
     * @param event The button is pressed
     * @author zach caton
     */
    @FXML
    void resetPasswordButton(ActionEvent event) {
        String usernameReset = resetUserTextField.getText();
        String passwordReset = resetPasswordTextField.getText();
        //SysAdmin resAdmin = new SysAdmin();
        try {
            sysAdmin.resetPassword(usernameReset, passwordReset);
        } catch (FileNotFoundException e) {
            System.out.println("File Not found Please Consult THe author");
        }
    }

    /**
     * findByName compares all BikeParts in the warehouse data set and returns a BikePart if its name is equal to the name given.
     * @param name The name of the part you are trying to find
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
     * @param event on button press
     */
    @FXML
    private void testBPDS(ActionEvent event){
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
        } catch (FileNotFoundException ex) {
            display.appendText("File not found, try a different file"+"\n");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            display.appendText("IO Exception"+"\n");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @author Josh Butler
     * Displays a specific BikePart by comparing user input to BikePart names.
     * @param event on button press
     */
    @FXML
    public void examineButtonMethod(ActionEvent event) {
        officeManager om = new officeManager("a", "b", "bb", "c", "d", "email", "867-867-5309");
        if(num.isSelected())
            display.appendText((om.examineButtonMethodNum(Integer.parseInt((partInfo.getText())), bpDS)));
        else if(quant.isSelected())
            display.appendText((om.examineButtonMethodQuant(Integer.parseInt((partInfo.getText())), bpDS)));
        else
            display.appendText((om.examineButtonMethodname(partInfo.getText(), bpDS)));
    }
       
    /**
     * @author Josh Butler
     * this method goes through the bike part warehouse and looks for parts with less than a hardcoded quantity (10)
     * @param event //todo addInv the ability to create a file of the needed parts
     */
    @FXML
    public void checkQuant(ActionEvent event) {
        officeManager om = new officeManager("a", "b", "bb", "c", "d", "email", "867-867-5309");
        for (BikePart bp : bpDS)
            if (bp.getQuantity() <= 10 && bp.getQuantity() > 5) {
                display.appendText("Quantity of " + bp.getName() + " is " + bp.getQuantity() + "," + " order more." + "\n");
            } else if (bp.getQuantity() <= 5) {
                display.appendText("Quantity of " + bp.getName() + " is " + bp.getQuantity() + "," + " order at least 20 now." + "\n");
            }
    }
    /**
     * @author Josh Butler
     * generates commission given invoice(and will print to a file for saving commissions)
     * @param inv
     */
    @FXML
    public void generateCommission(Invoice inv){  //may change param to a file in which invoice is printed
        //get variables from invoice and use associate + (sales*.15) to print commissions

    }

    /**
     * @author Josh Butler
     * exits the program on click
     * @param event on button press
     */
    @FXML
    public void Quit(ActionEvent event) {
        System.exit(1);
    }
//Overrides abstract method initialize(URL,ResourceBundle) in Initializable
//necessary for the execution of the program
    @Override 
    public void initialize(URL url, ResourceBundle rb) {       
    }
}