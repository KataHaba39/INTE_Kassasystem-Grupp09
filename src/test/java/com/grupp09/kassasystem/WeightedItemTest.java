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

    @Test
    void setSupplier_WhenNoSupplierAssigned_ShouldReturnTrue() {
        Supplier s = new Supplier("S001", "Axfood", "info@axfood.se");
        assertTrue(item.setSupplier(s));
        assertEquals(s, item.getSupplier());
    }

    @Test
    void setSupplier_WhenSupplierAlreadyAssigned_ShouldReturnFalse() {
        Supplier s1 = new Supplier("S001", "Axfood", "info@axfood.se");
        Supplier s2 = new Supplier("S002", "Coop", "contact@coop.se");

        item.setSupplier(s1);
        assertFalse(item.setSupplier(s2));
        assertEquals(s1, item.getSupplier());
    }

    @Test
    void removeSupplier_WhenSupplierAssigned_ShouldReturnTrue() {
        Supplier s = new Supplier("S001", "Axfood", "info@axfood.se");
        item.setSupplier(s);

        assertTrue(item.removeSupplier());
        assertNull(item.getSupplier());
    }

    @Test
    void removeSupplier_WhenNoSupplierAssigned_ShouldReturnFalse() {
        assertFalse(item.removeSupplier());
        assertNull(item.getSupplier());
    }

    @Test
    void getSupplier_WhenSupplierAssigned_ShouldReturnCorrectSupplier() {
        Supplier s = new Supplier("S001", "Axfood", "info@axfood.se");
        item.setSupplier(s);

        assertEquals(s, item.getSupplier());
    }

    @Test
    void getSupplier_WhenNoSupplierAssigned_ShouldReturnNull() {
        assertNull(item.getSupplier());
    }
}