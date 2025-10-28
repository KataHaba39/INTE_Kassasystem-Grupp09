package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void customerShouldStoreProvidedData() { // Testfall 1
        Customer testCustomer = new Customer("0000", "Assil Aldabak", "0046701234567", "Assil@gmail.com");
        
        assertEquals("0000", testCustomer.getCustomerId());
        assertEquals("Assil Aldabak", testCustomer.getName());
        assertEquals("0046701234567", testCustomer.getPhoneNumber());
        assertEquals("Assil@gmail.com", testCustomer.getEmail());
    }

    @Test
    void nameContainsNumber_throwsException() { // Testfall 8
        assertThrows(IllegalArgumentException.class, 
        () -> new Customer("0000", "Enes1 23abc", "0046707714683", "enescelik@gmail.com"));
    }

    @Test
    void mailDoesNotEndWithDotCom(){ // Testfall 15
        assertThrows(IllegalArgumentException.class, 
        () -> new Customer("0000", "Namn Namn", "0046707714683", "namn@gmail.c"));
    }

    @Test
    void toStringShouldContainNameAndId() {
        Customer c = new Customer("0003", "Alex Aleksander", "0046720006666", "Alex@gmail.com");
        String text = c.toString();

        assertTrue(text.contains("Alex"));
        assertTrue(text.contains("0003"));
    }

    @Test
    void customerShouldInitiallyHaveNoMembership() {
        Customer c = new Customer("1234", "Maria Mariasdotter", "0046777777777", "maria-swe@gmail.com");

        assertNull(c.getMembership());
        assertFalse(c.hasMembership());
    }

    @Test
    void customerShouldBeAbleToSetMembership() {
        Customer c = new Customer("0005", "Erik Eriksson", "0046707714683", "eriksson_erik@hotmail.com");
        Membership membership = new Membership("Erik Eriksson", 1, 123456789, "Borjarfjordsgatan 12");

        c.setMembership(membership);

        assertNotNull(c.getMembership());
        assertEquals(membership, c.getMembership());
        assertTrue(c.hasMembership());
    }

    @Test
    void customerWithMembershipShouldShowInToString() {
        Customer c = new Customer("12334", "Lisa Andersson", "0046701122334", "anderssonlis@gmail.com");
        Membership membership = new Membership("Lisa Andersson", 2, 987654321, "Kistagårdsbäg 2");
        
        c.setMembership(membership);
        String text = c.toString();
        
        assertTrue(text.contains("membership=Yes"));
    }

    @Test
    void customerWithoutMembershipShouldShowInToString() {
        Customer c = new Customer("0007", "Johan Elmander", "0046701234567", "elmander__johan@hotmail.com");
        String text = c.toString();
        
        assertTrue(text.contains("membership=No"));
    }

    @Test
    void customerConstructorWithMembershipShouldWork() {
        Membership membership = new Membership("Anna Svensson", 3, 555666777, "Valhallavägen 3");
        Customer c = new Customer("0088", "Anna Svensson", "0046701111111", "anna@gmail.com", membership);
        
        assertEquals("0088", c.getCustomerId());
        assertEquals("Anna Svensson", c.getName());
        assertEquals("0046701111111", c.getPhoneNumber());
        assertEquals("anna@gmail.com", c.getEmail());
        assertEquals(membership, c.getMembership());
        assertTrue(c.hasMembership());
    }

    @Test
    void canActivateMembershipWhenContactInfoExists() {
        Customer c = new Customer("0931", "Lania Stark", "0046703334444", "laniaaa@gmail.com");

        Membership m = new Membership("Silver", 100, 0707070701, "kungsängen 3");

        c.activateMembership(m);

        assertTrue(c.hasMembership());
        assertEquals(m, c.getMembership());
    }
}
