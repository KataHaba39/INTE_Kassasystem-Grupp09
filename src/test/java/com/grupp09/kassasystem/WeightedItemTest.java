package com.grupp09.kassasystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedItemTest {

    private WeightedItem item;
    private Money pricePerKg;

    @BeforeEach
    void setUp() {
        pricePerKg = Money.toMoney(120.0); // 120 SEK per kilogram
        item = new WeightedItem("Banana", pricePerKg, WeightUnit.KILO, ItemGroups.FRUKT_GRONT);
    }

    @Test
    void testGetItemName() {
        assertEquals("Banana", item.getItemName());
    }

    @Test
    void testGetItemGroup() {
        assertEquals(ItemGroups.FRUKT_GRONT, item.getItemGroup());
    }

    @Test
    void testGetWeightUnit() {
        assertEquals(WeightUnit.KILO, item.getWeightUnit());
    }

    @Test
    void testGetPricePerUnit() {
        assertEquals(0, pricePerKg.getValue().compareTo(item.getPricePerUnit().getValue()));
    }

    @Test
    void testGetTotalPriceSameUnit() {
        // 0.5 kg × 120 SEK/kg = 60 SEK
        Money total = item.getTotalPrice(0.5, WeightUnit.KILO);
        assertEquals(0, total.getValue().compareTo(Money.toMoney(60.0).getValue()));
    }

    @Test
    void testGetTotalPriceDifferentUnit() {
        // 500 g × 120 SEK/kg = 60 SEK
        Money total = item.getTotalPrice(500, WeightUnit.GRAM);
        assertEquals(0, total.getValue().compareTo(Money.toMoney(60.0).getValue()));
    }

    @Test
    void testGetTotalPriceThrowsOnNullUnit() {
        assertThrows(NullPointerException.class, () -> item.getTotalPrice(2, null));
    }

    @Test
    void testConstructorThrowsOnNullWeightUnit() {
        assertThrows(NullPointerException.class,
                () -> new WeightedItem("Apple", pricePerKg, null, ItemGroups.FRUKT_GRONT));
    }
}