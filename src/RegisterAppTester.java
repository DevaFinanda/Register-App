import javax.swing.JOptionPane;

/**
 * Test class to run the Register App Class
 */

public class RegisterAppTester {
    public static void main(String[] args) {
        // variables
        double amountDue; // Total cost of purchase
        double amountPaid; // Amount Paid for purchases
        String input; // User input

        // Dialog Box - end-user inputs the amount due for purchase
        input = JOptionPane.showInputDialog("What is the total purchase?");

        // end-user enters amount due
        amountDue = Double.parseDouble(input);

        // Dialog Box - end-user enters amount paid for purchase
        input = JOptionPane.showInputDialog("How much did you pay?");

        // end-user enters amount paid
        amountPaid = Double.parseDouble(input);

        // Creating a RegisterApp object
        RegisterApp myRegisterApp = new RegisterApp(amountDue, amountPaid);

        // Calling the method to run computation
        myRegisterApp.computeChange();

        // Terminate program
        System.exit(0);
    }

}