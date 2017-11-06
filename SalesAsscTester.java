package Project3GitDoneRight;

public class SalesAsscTester {
    static SalesAssociate bob;

    public static void main(String[] args) {

        bob = new SalesAssociate("Bob", "Brinkle", "bbrinkle", "t", "email");
        sellstuff();
        //addstuff();
    }

    public static void sellstuff() {
        // todo look at the sell and invoicing methods
        bob.Sell("", 1, 1);
        bob.Sell("AAA_FIRST", -1, 1);
    }

    public static void addstuff() {
        bob.LoadFile("initialInv.txt");
    }
}
