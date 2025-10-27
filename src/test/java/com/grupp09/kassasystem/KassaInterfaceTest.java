package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KassaInterfaceTest {

    @BeforeEach
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
