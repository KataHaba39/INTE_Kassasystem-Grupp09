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

    @Test
    void customerShouldInitiallyHaveNoMembership() {
        Customer c = new Customer("C004", "Maria");

        assertNull(c.getMembership());
        assertFalse(c.hasMembership(null));
    }

    @Test
    void customerShouldBeAbleToSetMembership() {
        Customer c = new Customer("C005", "Erik");
        Membership membership = new Membership("Erik Eriksson", 1, 123456789, "Borjarfjordsgatan 12");

        c.setMembership(membership);

        assertNotNull(c.getMembership());
        assertEquals(membership, c.getMembership());
        assertTrue(c.hasMembership(membership));
    }

    @Test
    void customerWithMembershipShouldShowInToString() {
        Customer c = new Customer("C006", "Lisa");
        Membership membership = new Membership("Lisa Andersson", 2, 987654321, "Kistagårdsbäg 2");
        
        c.setMembership(membership);
        String text = c.toString();
        
        assertTrue(text.contains("membership=Yes"));
    }

    @Test
    void customerWithoutMembershipShouldShowInToString() {
        Customer c = new Customer("C007", "Johan");
        String text = c.toString();
        
        assertTrue(text.contains("membership=No"));
    }

    @Test
    void customerConstructorWithMembershipShouldWork() {
        Membership membership = new Membership("Anna Svensson", 3, 555666777, "Valhallavägen 3");
        Customer c = new Customer("C008", "Anna", "0701111111", "anna@gmail.com", membership);
        
        assertEquals("C008", c.getCustomerId());
        assertEquals("Anna", c.getName());
        assertEquals("0701111111", c.getPhoneNumber());
        assertEquals("anna@gmail.com", c.getEmail());
        assertEquals(membership, c.getMembership());
        assertTrue(c.hasMembership(membership));
    }


}
