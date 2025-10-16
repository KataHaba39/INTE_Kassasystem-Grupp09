package com.grupp09.kassasystem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyTest {
    @Test
    void testToMoneyFromDouble() {
        Money money = Money.toMoney(10.45);
        assertEquals(new BigDecimal("10.45"), money.getValue());
    }

    @Test
    void testToMoneyFromBigDecimal() {
        Money money = Money.toMoney(new BigDecimal("15.67"));
        assertEquals(new BigDecimal("15.67"), money.getValue());
    }

    @Test
    void testToMoneyHasCorrectDecimalPoints() {
        Money money = Money.toMoney(10.456);
        assertEquals(new BigDecimal("10.46"), money.getValue());
    }

    @Test
    void testAddSumIsCorrect() {
        Money m1 = Money.toMoney(10.45);
        Money m2 = Money.toMoney(9.15);
        Money result = m1.add(m2);

        assertEquals(new BigDecimal("19.60"), result.getValue());
    }

    @Test
    void testSubtractDifferenceIsCorrect() {
        Money m1 = Money.toMoney(10.45);
        Money m2 = Money.toMoney(9.15);
        Money result = m1.subtract(m2);

        assertEquals(new BigDecimal("1.30"), result.getValue());
    }

    @Test
    void testMultiplyProductIsCorrect() {
        Money m1 = Money.toMoney(10.45);
        Money result = m1.multiply(1.5);

        assertEquals(new BigDecimal("15.68"), result.getValue());
    }

    @Test
    void valueAsDoubleIsCorrect(){
        Money m1 = Money.toMoney(12.34);
        assertEquals(12.34, m1.getValueAsDouble());
    }

    @Test
    void toStringOutputIsCorrect() {
        Money m = Money.toMoney(21.00);
        assertEquals("21.00 SEK", m.toString());
    }

    @Test
    void testImmutability() {
        Money m1 = Money.toMoney(10.00);
        Money m2 = m1.add(Money.toMoney(5.00));
        assertNotSame(m1, m2); // ska inte vara samma objekt
    }
}



