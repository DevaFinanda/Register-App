/**
 * This app computes the change due and mimics the function of a cash register
 * it reduces the coin usage to the minimun when the user enters the cost of an
 * item purchase and the tendered amount.
 **/

 public class RegisterApp {
    // variables
    private double amountDue; // This is the total cost of the purchases
    private double amountTendered; // This is the amount paid
    private int dollarsDue; // number of dollar coins due
    private int quartersDue; // number of quarters due
    private int dimesDue; // number of dimes due
    private int nickelsDue; // number of nickels due
    private int penniesDue; // number of pennies due

    /**
     * Constructor
     * Constructs a register app object
     * 
     * @param totalDue       is the amount of the purchase
     * @param amountReceived is the received amount tendered paid
     */

    public RegisterApp(double totalDue, double amountReceived) {
        // initialized instance variables
        amountDue = totalDue;
        amountTendered = amountReceived;

        // The other instance variables are automatically initialized to zero
        System.out.printf("%nAmount Due: $%.2f", amountDue);
        System.out.printf("%nAmount Received: $%.2f", amountTendered);
    }

    /**
     * Computes the number of coins of each coin unit to be given as change
     */

    // This is a mutator or void method
    public void computeChange() {
        int changeDue; // total change due in cents

        // If the amount given is the exact amount of the bill, then there's no change
        if (amountDue == amountTendered) {
            System.out.println("\n This is exact change! Thank You and come back soon!");
        }
        // If the amount due is greater than the amount paid, then you owe money
        else if (amountDue > amountTendered) {
            System.out.printf("\nYou are short %.2f", amountDue - amountTendered);
        }
        // else if the amount amount due is less than the amount paid, then change is
        // due
        else {
            // change due is computed in cents for simpler calculations, so 100 represents
            // 100 pennies which equals a dollar.
            changeDue = (int) Math.round((amountTendered - amountDue) * 100);

            // dollars due computation - one dollar equals 100 pennies
            dollarsDue = changeDue / 100; // integer division
            changeDue = changeDue % 100; // modulus operation

            // quaters due computation - one quarter equals 25 pennies
            quartersDue = changeDue / 25; // integer division
            changeDue = changeDue % 25; // modulus operation

            // dimes due computation - one dime equals 10 pennies
            dimesDue = changeDue / 10; // integer divison
            changeDue = changeDue % 10; // modulus operation

            // nickel due computation - one nickel equals five pennies
            nickelsDue = changeDue / 10; // integer divison
            changeDue = changeDue % 10; // modulus operation

            // pennies due computation - one penny equals one penny
            // The change due here is less than or equals to four cents
            penniesDue = changeDue;

            // call the change engine method
            this.changeEngine();
        }
    }

    // This is a mutator or void method
    // This void method displays the change due to the customer
    private void changeEngine() {
        System.out.println("\n Your Change Due:");
        if (dollarsDue != 0) {
            System.out.println("\t" + dollarsDue + " Dollar(s)");
        }
        if (quartersDue != 0) {
            System.out.println("\t" + quartersDue + " Quarter(s)");
        }
        if (dimesDue != 0) {
            System.out.println("\t" + dimesDue + " Dime(s)");
        }
        if (nickelsDue != 0) {
            System.out.println("\t" + nickelsDue + " Nickel(s)");
        }
        if (penniesDue != 0) {
            System.out.println("\t" + penniesDue);
            if (penniesDue == 1) {
                System.out.println(" Penny");
            } else {
                System.out.println(" pennies");
            }
        }
        System.out.println("\nThank You!");
    }
}