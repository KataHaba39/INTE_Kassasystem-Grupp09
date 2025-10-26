package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MembershipTest {
    private Membership member;

    @BeforeEach
    void setUp() {
        member = new Membership("Namn", 23131, 8987327, "Address");
    }

    @Test
    void constructorAccepts_validParameters() {
        assertEquals("Namn", member.getName());
        assertEquals(23131, member.getId());
        assertEquals(8987327, member.getPhoneNumber());
        assertEquals("Address", member.getAddress());
    }

    @Test
    void constructorRejects_blankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership(" ", 23131, 8987327, "address"));
    }

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
    void testGetName() {
        assertEquals("Namn", member.getName());
    }

    @Test
    void testGetId() {
        assertEquals(12345, member.getId());
    }

    @Test
    void testGetAddress() {
        assertEquals("address", member.getAddress());
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals(5551234, member.getPhoneNumber());
    }

    @Test
    void getMemberInfo_returnsCorrectFormat() {
        assertEquals("Namn (ID: 12345)", member.getMemberInfo());
    }
}
