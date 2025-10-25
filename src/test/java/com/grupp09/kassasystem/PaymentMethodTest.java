package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PaymentMethodTest {
    @Test
    void test_cash_payment_change() {
        Money total = Money.toMoney(100.0);
        Money paid = Money.toMoney(150.0);
        Payment p = new PaymentMethod("cash", total, paid);

        assertTrue(p.isPaidEnough());
        assertEquals(50.0, p.getChange().getValueAsDouble());
    }

    @Test
    void test_card_payment_no_change() {
        Payment p = new PaymentMethod("card", Money.toMoney(100.0), Money.toMoney(100.0));
        assertTrue(p.isPaidEnough());
        assertEquals(0.0, p.getChange().getValueAsDouble());
    }

    @Test
    void test_invalid_method_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new PaymentMethod("crypto", Money.toMoney(100.0), Money.toMoney(100.0)));
    }

}
