package Project3GitDoneRight;

public class SalesAsscTester {
    public static void main(String[] args) {
        try {
            SalesAssociate bob = new SalesAssociate("Bob", "Brinkle", "bbrinkle", "t", "email");
            bob.LoadFile("initialInv.txt");


        } catch (Exception e) {
            System.out.println("this is an unexpected error");
        }
    }
}
