package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KassaInterfaceTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;
    private Customer customer = new Customer("12345", "Alice", "234567", "mail");
    private Receipt receipt = new Receipt(customer);

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void testMainCustomerRegistration() {
        // Simulerad användarinmatning (alla steg direkt)
        String simulatedInput = "145\nAnna\n0701234567\nanna@mail.com\n";
        provideInput(simulatedInput);

        // Kör hela programmet
        //KassaInterface.main(new String[]{});
        KassaInterface.registerCustomer();

        // Läs all output
        String output = outContent.toString();

        // Kontrollera att rätt steg skedde
        assertTrue(output.contains("Enter ID"), "Should ask for ID");
        assertTrue(output.contains("Enter name"), "Should ask for name");
        assertTrue(output.contains("Enter phone number"), "Should ask for phone number");
        assertTrue(output.contains("Enter mail"), "Should ask for mail");
        assertTrue(output.contains("Customer registered"), "Should confirm registration");
    }

    @Test
    void customer_canCancelAfterRegistration(){
        provideInput("2");

        //KassaInterface.main(new String[]{});
        KassaInterface.customerContinuesPurchase();

        String output = outContent.toString();

        assertTrue(output.contains("Purchase cancelled"));
    }
    
    @Test
    void canAddItem(){
        provideInput("1");

        KassaInterface.handleItems(receipt);

        String output = outContent.toString();

        assertTrue(output.contains("Product was added"));
    }

    @Test
    void canRemoveItem(){
        receipt.addItem(new FixedPriceItem("Milk", Money.toMoney(12.0d), ItemGroups.MEJERI), 1, null);

        provideInput("2");

        KassaInterface.handleItems(receipt);
        
        String output = outContent.toString();

        assertTrue(output.contains("Product was removed"));
    }

    @Test
    void customer_CanCancelwhenHandlingItems(){

        provideInput("5");

        KassaInterface.handleItems(receipt);
        
        String output = outContent.toString();

        assertTrue(output.contains("Cancelled purchase"));

    }

    @Test
    void customer_CanContinueToPayment(){
        receipt.addItem(new FixedPriceItem("Milk", Money.toMoney(12.0d), ItemGroups.MEJERI), 1, null);

        provideInput("6");

        KassaInterface.handleItems(receipt);
        
        String output = outContent.toString();

        assertTrue(output.contains("Continues to payment"));

    }

    @Test
    void receiptWithZeroItems_canNotContinueToPayment(){
        Receipt emptyReceipt = new Receipt(customer);

        provideInput("6");

        KassaInterface.handleItems(emptyReceipt);
        
        String output = outContent.toString();

        assertTrue(output.contains("Error: Receipt must contain at least one items to continue"));
    }

    /*@BeforeEach
    void setUp() throws Exception {
        Customer customer = new Customer("12345", "Alice", "234567", "mail");
        Receipt receipt = new Receipt(customer);
    }

    @Test
    void registerCustomer_worksCorrectly() {
        provideInput("Customer ID");
        provideInput("Name");
        provideInput("Phone number");
        provideInput("Mail");

        String output = out.toS

        assertTrue("Customer registered");
    }

    @Test
    void test2() {

    }
    // Receipt - Customer
    @Test
    void testingCustomer() {
    }

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    ByteArrayOutputStream provideOutput(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }
    @Test
    void testingName() {
        provideInput("Baeldung");
        String input = KassaInterface.readName();
        assertEquals("Name: Baeldung", input);
    }
*/
}
