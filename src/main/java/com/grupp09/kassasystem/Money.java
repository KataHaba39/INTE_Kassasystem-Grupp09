package com.grupp09.kassasystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final static String CURRENCY = "SEK";
    private final BigDecimal value;

    private Money(BigDecimal value){
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Money toMoney(double value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money toMoney(BigDecimal value) {
        return new Money(value);
    }

    public String getCurrency() {
        return CURRENCY;
    }

    public BigDecimal getValue(){
        return value;
    }

    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    public Money subtract(Money other) {
        return new Money(this.value.subtract(other.value));
    }

    public Money multiply(double multiplier) {
        return new Money(this.value.multiply(BigDecimal.valueOf(multiplier)));
    }

    public double getValueAsDouble() {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return value + " " + CURRENCY;
    }
}
