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
    void member_test_without_name() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("", 23131, 8987327, "address"));
    }

    // Expecting IllegalArgumentException, because id is 0
    @Test
    void member_test_without_id() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("namn", 0, 8987327, "address"));
    }

    @Test
    void constructor_rejects_blank_name() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("   ", 239731, 467028327, "address"));
    }

    @Test
    void constructor_rejects_zero_id() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("Namn", 0, 8987327, "address"));
    }

    @Test
    void constructor_rejects_negative_phone_number() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("Namn", 23131, -12345, "address"));
    }

    @Test
    void constructor_rejects_blank_address() {
        assertThrows(IllegalArgumentException.class,
                () -> new Membership("Namn", 23131, 8987327, "   "));
    }

    @Test
    void constructor_accepts_valid_parameters() {
        Membership m = new Membership("Namn", 23131, 8987327, "Address");
        assertEquals("Namn", m.getName());
        assertEquals(23131, m.getId());
        assertEquals(8987327, m.getPhoneNumber());
        assertEquals("Address", m.getAddress());
    }
}
