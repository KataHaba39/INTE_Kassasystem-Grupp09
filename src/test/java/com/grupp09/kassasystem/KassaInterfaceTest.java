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
        KassaInterface.main(new String[]{});

        // Läs all output
        String output = outContent.toString();

        // Kontrollera att rätt steg skedde
        assertTrue(output.contains("Enter ID"), "Should ask for ID");
        assertTrue(output.contains("Enter name"), "Should ask for name");
        assertTrue(output.contains("Enter phone number"), "Should ask for phone number");
        assertTrue(output.contains("Enter mail"), "Should ask for mail");
        assertTrue(output.contains("Customer registered"), "Should confirm registration");
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
