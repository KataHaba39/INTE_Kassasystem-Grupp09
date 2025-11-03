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
    void blankCustomerId_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer(" ", "Enes Celik", "0046707777777", "enes@gmail.com"));
    }

    @Test
    void nullCustomerId_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer(null, "Enes Celik", "0046707777777", "ence@gmail.com"));
    }

    @Test
    void customerId_lessThan4Digits_orMoreThan8Digits_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("123", "Enes Celik", "0046707777777", "mail@mail.com"));
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("123456789", "Enes Celik", "0046707777777", "mail@mail.com"));
    }

    @Test
    void customerIdContainsLetter_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("123a", "Enes Celik", "0046707777777", "mail@mail.com"));
    }

    @Test
    void blankName_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "  ", "0046707777777", "mail@mail.com"));
    }

    @Test
    void nullName_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", null, "0046707777777", "mail@mail.com"));
    }

    @Test
    void nameLongerThan_20Characters_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "EnesEnes CelikCelikCelik", "0046707777777", "mail@mail.com"));
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

    //Detta är genererat av ChatGpt
    @Test
    void nameWithoutSpace_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Enes", "0046707777777", "enes@gmail.com"));
    }

    @Test
    void nameTooShort_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Al", "0046707777777", "al@gmail.com"));
    }

    @Test
    void phoneNumberContainsLetters_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046abc1234567", "test@gmail.com"));
    }

    @Test
    void phoneNumberWrongLength_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "004670123456", "test@gmail.com"));
    }

    @Test
    void phoneNumberDoesNotStartWith0046_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0045601234567", "test@gmail.com"));
    }

    @Test
    void nullPhoneNumber_throwsNullPointer() {
        assertThrows(NullPointerException.class,
                () -> new Customer("1234", "Test Person", null, "test@gmail.com"));
    }

    @Test
    void nullEmail_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", null));
    }

    @Test
    void blankEmail_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", "   "));
    }

    @Test
    void emailWithoutAt_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", "mailmail.com"));
    }

    @Test
    void emailMissingPartBeforeAt_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", "@gmail.com"));
    }

    @Test
    void emailMissingPartAfterAt_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", "user@"));
    }

    @Test
    void emailPartBeforeAtTooLong_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", "averyveryverylongname@gmail.com"));
    }

    @Test
    void emailPartAfterAtTooShort_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", "user@.com"));
    }

    @Test
    void emailPartAfterAtTooLong_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("1234", "Test Person", "0046701234567", "user@averyveryveryverylongdomainname.com"));
    }

    @Test
    void activateMembershipShouldAssignMembershipCorrectly() {
        Customer c = new Customer("2222", "Test Person", "0046701111222", "testperson@gmail.com");
        Membership m = new Membership("Silver", 1, 123456789, "Testgatan 1");

        c.activateMembership(m);

        assertEquals(m, c.getMembership());
        assertTrue(c.hasMembership());
    }

    @Test
    void activateMembershipWithNullMembershipShouldSetToNull() {
        Customer c = new Customer("3333", "Test Person", "0046703334444", "testperson@gmail.com");
        c.activateMembership(null);

        assertNull(c.getMembership());
        assertFalse(c.hasMembership());
    }

}

