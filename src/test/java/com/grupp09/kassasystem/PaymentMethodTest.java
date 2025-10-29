package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PaymentMethodTest {

    @Test
    void constructorRejects_null_or_blank_method() {
        assertThrows(IllegalArgumentException.class,
                () -> new PaymentMethod(null, Money.toMoney(100), Money.toMoney(100)));
        assertThrows(IllegalArgumentException.class,
                () -> new PaymentMethod("   ", Money.toMoney(100), Money.toMoney(100)));
    }

    @Test
    void constructorRejects_unknown_method() {
        assertThrows(IllegalArgumentException.class,
                () -> new PaymentMethod("crypto", Money.toMoney(100.0), Money.toMoney(100.0)));
    }

    @Test
    void constructorRejects_null_amounts() {
        assertThrows(IllegalArgumentException.class,
                () -> new PaymentMethod("cash", null, Money.toMoney(100.0)));
        assertThrows(IllegalArgumentException.class,
                () -> new PaymentMethod("cash", Money.toMoney(100.0), null));
    }

    @Test
    void totalAmount_returnsCorrectValue() {
        PaymentMethod payment = new PaymentMethod("swish", Money.toMoney(150), Money.toMoney(150));
        assertEquals(payment.getTotalAmount(), Money.toMoney(150));
    }

    @Test
    void amountPaid_returnsCorrectValue() {
        PaymentMethod payment = new PaymentMethod("cash", Money.toMoney(120), Money.toMoney(150));
        assertEquals(payment.getAmountPaid(), Money.toMoney(150));
    }

    @Test
    void cashPayment_getChange() {
        Money total = Money.toMoney(100.0);
        Money paid = Money.toMoney(150.0);
        Payment p = new PaymentMethod("cash", total, paid);

        assertTrue(p.isPaidEnough());
        assertEquals(50.0, p.getChange().getValueAsDouble());
    }

    @Test
    void test_cashPayment_is_not_enough() {
        Money total = Money.toMoney(100.0);
        Money paid = Money.toMoney(50.0);
        Payment payment = new PaymentMethod("cash", total, paid);

        assertFalse(payment.isPaidEnough());
    }

    @Test
    void cashPayment_insufficient_funds_throws_exception() {
        Money total = Money.toMoney(100.0);
        Money paid = Money.toMoney(50.0);
        Payment p = new PaymentMethod("cash", total, paid);

        assertThrows(IllegalStateException.class, () -> p.getChange());
    }

    @Test
    void test_payment_method_case_insensitivity() {
        Payment p1 = new PaymentMethod("CaSh", Money.toMoney(100.0), Money.toMoney(150.0));
        Payment p2 = new PaymentMethod("CARD", Money.toMoney(100.0), Money.toMoney(100.0));
        Payment p3 = new PaymentMethod("swisH", Money.toMoney(50.0), Money.toMoney(50.0));

        assertEquals("cash", p1.getMethod());
        assertEquals("card", p2.getMethod());
        assertEquals("swish", p3.getMethod());
    }

    @Test
    void test_card_payment_no_change() {
        Payment p = new PaymentMethod("card", Money.toMoney(100.0), Money.toMoney(100.0));
        assertTrue(p.isPaidEnough());
        assertEquals(0.0, p.getChange().getValueAsDouble());
    }
}
