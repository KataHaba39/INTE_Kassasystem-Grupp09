package com.grupp09.kassasystem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    void money_class_sets_attributes(){
        Money money = new Money();
        assertEquals(0.0d, money.getMoney());
    }
}
