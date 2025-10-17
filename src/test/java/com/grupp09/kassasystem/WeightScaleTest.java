package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class WeightScaleTest {

    // Efter uppdatering av Money och ITem fungerar inte denna testklass längre och behöver fixas
    /*
    @Test
    void weightedItem_kilogram_test() {
        WeightedItem wi = new WeightedItem("Apple", Money.toMoney(29.9), WeightUnit.KILO, ItemGroups.FRUKT_GRONT);
        Money priceForHalfKg = wi.getTotalPrice(0.5, WeightUnit.KILO);
        assertEquals(priceForHalfKg, Money.toMoney(14.95));
    }

    @Test
    void weightedItem_hekta_test() {
        WeightedItem wi1 = new WeightedItem("Lösgodis", 12.9, WeightUnit.HEKTO, ItemGroups.GODIS);
        double priceFor13hk = wi1.totalPrice(13, WeightUnit.HEKTO);
        assertEquals(priceFor13hk, 167.7);
    }

    @Test
   void constructor_rejects_null_weightUnit() {
        assertThrows(NullPointerException.class,
            () -> new WeightedItem("Lösgodis", 10.0, null, ItemGroups.GODIS));
    }
    */
}
