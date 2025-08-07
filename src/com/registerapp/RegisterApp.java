package com.registerapp;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.PrintStream;

/**
 * This app computes the change due and mimics the function of a cash register
 * it reduces the coin usage to the minimun when the user enters the cost of an
 * item purchase and the tendered amount.
 **/

 public class RegisterApp {
    // Logger instance for internal logging
    private static final Logger LOGGER = Logger.getLogger(RegisterApp.class.getName());
    
    // Default display output factory to avoid direct System.out usage
    private static final DisplayOutputFactory DEFAULT_OUTPUT_FACTORY = new DisplayOutputFactory();
    
    // Display handler for user output (can be redirected for testing)
    private final PrintStream displayOutput;
    
    /**
     * Factory class for creating display output streams
     */
    private static class DisplayOutputFactory {
        /**
         * Creates the default console output stream
         * @return PrintStream for console output
         */
        public PrintStream createConsoleOutput() {
            // This is the only place where System.out is used, properly encapsulated
            return System.out;
        }
    }
    
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
        this(totalDue, amountReceived, getDefaultDisplayOutput());
    }
    
    /**
     * Gets the default display output stream
     * @return the default PrintStream for display output
     */
    private static PrintStream getDefaultDisplayOutput() {
        return DEFAULT_OUTPUT_FACTORY.createConsoleOutput();
    }
    
    /**
     * Constructor with custom output stream
     * Constructs a register app object with specified output stream
     * 
     * @param totalDue       is the amount of the purchase
     * @param amountReceived is the received amount tendered paid
     * @param output         the output stream for display
     */
    public RegisterApp(double totalDue, double amountReceived, PrintStream output) {
        this.displayOutput = output;
        
        // initialized instance variables
        amountDue = totalDue;
        amountTendered = amountReceived;

        // Log the transaction details only if INFO level is enabled
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(() -> String.format("New transaction - Amount Due: $%.2f, Amount Received: $%.2f", amountDue, amountTendered));
        }
        
        // Display to user
        displayOutput.printf("%nAmount Due: $%.2f%n", amountDue);
        displayOutput.printf("Amount Received: $%.2f%n", amountTendered);
    }

    /**
     * Computes the number of coins of each coin unit to be given as change
     */

    // This is a mutator or void method
    public void computeChange() {
        int changeDue; // total change due in cents

        // If the amount given is the exact amount of the bill, then there's no change
        if (amountDue == amountTendered) {
            LOGGER.info("Transaction: Exact change provided");
            displayOutput.printf("%n This is exact change! Thank You and come back soon!%n");
        }
        // If the amount due is greater than the amount paid, then you owe money
        else if (amountDue > amountTendered) {
            double shortage = amountDue - amountTendered;
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.warning(() -> String.format("Transaction: Insufficient payment - shortage: $%.2f", shortage));
            }
            displayOutput.printf("%nYou are short %.2f%n", shortage);
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
            nickelsDue = changeDue / 5; // integer divison
            changeDue = changeDue % 5; // modulus operation

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
        // Log the change calculation details only if INFO level is enabled
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(() -> String.format("Change calculation - Dollars: %d, Quarters: %d, Dimes: %d, Nickels: %d, Pennies: %d", 
                                           dollarsDue, quartersDue, dimesDue, nickelsDue, penniesDue));
        }
        
        // Display to user
        displayOutput.printf("%n Your Change Due:%n");
        if (dollarsDue != 0) {
            displayOutput.printf("\t%d Dollar(s)%n", dollarsDue);
        }
        if (quartersDue != 0) {
            displayOutput.printf("\t%d Quarter(s)%n", quartersDue);
        }
        if (dimesDue != 0) {
            displayOutput.printf("\t%d Dime(s)%n", dimesDue);
        }
        if (nickelsDue != 0) {
            displayOutput.printf("\t%d Nickel(s)%n", nickelsDue);
        }
        if (penniesDue != 0) {
            if (penniesDue == 1) {
                displayOutput.printf("\t%d Penny%n", penniesDue);
            } else {
                displayOutput.printf("\t%d pennies%n", penniesDue);
            }
        }
        displayOutput.printf("%nThank You!%n");
    }
}