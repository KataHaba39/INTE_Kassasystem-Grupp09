package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MembershipTest {
    
    @Test
    void test() {
        Membership m = new Membership("namn", 23131, 8987327, "address");
        assertEquals("address", m.getAddress());
    }

    @Test
    void test2() {

    }
}
