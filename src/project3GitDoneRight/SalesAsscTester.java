package project3GitDoneRight;

public class SalesAsscTester{
    private static SalesAssociate bob;

    public static void main(String[] args) {

        bob = new SalesAssociate("Bob", "Brinkle", "manager", "bbrinkle", "t", "email", "867-867-5309");
        addstuff();
        sellstuff();
        invoicing();
    }

    private static void sellstuff() {
        bob.Sell("", 1, 10);
        bob.Sell("AAA_FIRST", -1, 1);
        bob.Sell("10spFrontDerailuer",-1,2);
        bob.Sell("spdPedals",-1,3);
        bob.Sell("",1234567910,4);
    }

    private static void addstuff() {
        bob.LoadFile("initialInv.txt");
    }
    private static void invoicing(){
        Object a = bob.closeInvoice("marko");
    }
}
