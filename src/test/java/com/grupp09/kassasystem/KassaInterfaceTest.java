package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KassaInterfaceTest {

    @BeforeEach
    void setUp() throws Exception {
        Customer customer = new Customer("12345", "Alice", "234567", "mail");
        Customer memberCustomer = new Customer("54321", "Bob", "7654321", "mail2", new Membership("Bob", 1, 7654321, "Address"));
        Receipt receipt = new Receipt(customer);
        Receipt memberReceipt = new Receipt(memberCustomer);
    }

    @Test
    void test1() {
        
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

    @Test
    void testingName() {
        provideInput("Baeldung");
        String input = KassaInterface.readName();
        assertEquals("Name: Baeldung", input);
    }

}
