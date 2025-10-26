package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MembershipTest {

    @Test
    void member_test() {
        Membership m = new Membership("namn", 23131, 8987327, "address");
        assertEquals("address", m.getAddress());
    }

    // Expecting IllegalArgumentException, because name is blank
    @Test
    void constructorRejects_blankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership(" ", 23131, 8987327, "address"));
    }

    // Expecting IllegalArgumentException, because id is 0
    @Test
    void constructorRejects_when_idIs_Zero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("namn", 0, 8987327, "address"));
    }

    // Detta test ska ändras om vi ändrar phoneNumber till String
    @Test
    void constructorRejects_negative_phone_number() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("Namn", 23131, -12345, "address"));
    }

    @Test
    void constructorRejects_blankAddress() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("Namn", 23131, 8987327, "   "));
    }

    @Test
    void getMemberInfo_returnsCorrectFormat() {
        Membership m = new Membership("Namn", 12345, 5551234, "address");
        assertEquals("Namn (ID: 12345)", m.getMemberInfo());
    }

    @Test
    void constructorAccepts_validParameters() {
        Membership m = new Membership("Namn", 23131, 8987327, "Address");
        assertEquals("Namn", m.getName());
        assertEquals(23131, m.getId());
        assertEquals(8987327, m.getPhoneNumber());
        assertEquals("Address", m.getAddress());
    }
}
