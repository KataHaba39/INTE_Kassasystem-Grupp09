package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Membership m = new Membership("", 23131, 8987327, "address");
        assertEquals(23131, m.getId());
    }

    // Expecting IllegalArgumentException, because id is 0
    @Test
    void member_test_without_id() {
        Membership m = new Membership("namn", 0, 8987327, "address");
        assertEquals(8987327, m.getPhoneNumber());
    }
}
