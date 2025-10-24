package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void customerShouldStoreProvidedData() {
        Customer c = new Customer("C001", "Assil", "0701234567", "Assil@gmail.com");
        
        assertEquals("C001", c.getCustomerId());
        assertEquals("Assil", c.getName());
        assertEquals("0701234567", c.getPhoneNumber());
        assertEquals("Assil@gmail.com", c.getEmail());
    }

    @Test
    void customerWithoutContactInfoShouldHaveNullFields() {
        Customer c = new Customer("C002", "Bob");

        assertEquals("C002", c.getCustomerId());
        assertEquals("Bob", c.getName());
        assertNull(c.getPhoneNumber());
        assertNull(c.getEmail());
    }

    @Test
    void toStringShouldContainNameAndId() {
        Customer c = new Customer("C003", "Alex", "0700000000", "Alex@gmail.com");
        String text = c.toString();

        assertTrue(text.contains("Alex"));
        assertTrue(text.contains("C003"));
    }
}
