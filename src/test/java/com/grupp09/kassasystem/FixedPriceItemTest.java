package com.grupp09.kassasystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FixedPriceItemTest {

    private FixedPriceItem item;
    private Money price;

    @BeforeEach
    void setUp() {
        price = Money.toMoney(100.0);
        item = new FixedPriceItem("Apple", price, ItemGroups.FRUKT_GRONT);
    }

    @Test
    void constructor_ShouldThrowException_whenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FixedPriceItem(null, Money.toMoney(50.0), ItemGroups.FRUKT_GRONT);
        });
    }

    @Test
    void constructor_throwsException_nameIsEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FixedPriceItem("", Money.toMoney(50.0), ItemGroups.FRUKT_GRONT);
        });
    }

    @Test
    void constructor_ShouldThrowException_WhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FixedPriceItem("Banana", Money.toMoney(-50.0), ItemGroups.FRUKT_GRONT);
        });
    }

    @Test
    void constructor_ShouldThrowException_WhenPriceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FixedPriceItem("Banana", null, ItemGroups.FRUKT_GRONT);
        });
    }

    @Test
    void constructor_ShouldHandleVeryLargePrice() {
        assertDoesNotThrow(() -> {
            new FixedPriceItem("T-Bone Steak", Money.toMoney(1_000_000.0), ItemGroups.KOTT);
        }, "Should handle large price values without overflow");
    }

    @Test
    void testGetItemName() {
        assertEquals("Apple", item.getItemName());
    }

    @Test
    void testGetItemGroup() {
        assertEquals(ItemGroups.FRUKT_GRONT, item.getItemGroup());
    }

    @Test
    void testGetPricePerUnit() {
        assertEquals(0, price.getValue().compareTo(item.getPricePerUnit().getValue()));
    }

    @Test
    void testGetTotalPrice() {
        Money total = item.getTotalPrice(2, null);
        BigDecimal expected = price.getValue().multiply(BigDecimal.valueOf(2));
        assertEquals(0, expected.compareTo(total.getValue()));
    }

    @Test
    void testGetTotalPrice_WithZeroQuantity() {
        Money total = item.getTotalPrice(0, null);
        assertEquals(0.0, total.getValueAsDouble(), "Total price for zero quantity should be zero");
    }

    @Test
    void testCurrencyIsAlwaysSEK() {
        assertEquals("SEK", item.getPricePerUnit().getCurrency());
    }

    @Test
    void setSupplier_WhenNoSupplierAssigned_ShouldReturnTrue() {
        Supplier s = new Supplier("S001", "Axfood", "info@axfood.se");
        assertTrue(item.setSupplier(s), "Setting supplier should succeed when none assigned");
        assertEquals(s, item.getSupplier(), "Supplier not correctly set");
    }

    @Test
    void setSupplier_WhenSupplierAlreadyAssigned_ShouldReturnFalse() {
        Supplier s1 = new Supplier("S001", "Axfood", "info@axfood.se");
        Supplier s2 = new Supplier("S002", "Coop", "contact@coop.se");

        item.setSupplier(s1);
        assertFalse(item.setSupplier(s2), "Cannot assign a new supplier if one already exists");
        assertEquals(s1, item.getSupplier(), "Original supplier should remain unchanged");
    }

    @Test
    void removeSupplier_WhenSupplierAssigned_ShouldReturnTrue() {
        Supplier s = new Supplier("S001", "Axfood", "info@axfood.se");
        item.setSupplier(s);

        assertTrue(item.removeSupplier(), "Removing assigned supplier should succeed");
        assertNull(item.getSupplier(), "Supplier should be null after removal");
    }

    @Test
    void removeSupplier_WhenNoSupplierAssigned_ShouldReturnFalse() {
        assertFalse(item.removeSupplier(), "Removing supplier when none assigned should fail");
        assertNull(item.getSupplier(), "Supplier should still be null");
    }

    @Test
    void getSupplier_WhenSupplierAssigned_ShouldReturnCorrectSupplier() {
        Supplier s = new Supplier("S001", "Axfood", "info@axfood.se");
        item.setSupplier(s);

        assertEquals(s, item.getSupplier(), "getSupplier should return assigned supplier");
    }

    @Test
    void getSupplier_WhenNoSupplierAssigned_ShouldReturnNull() {
        assertNull(item.getSupplier(), "getSupplier should return null if no supplier assigned");
    }

    @Test
    void equals_returnsTrue_sameInstance() {
        FixedPriceItem item = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        assertTrue(item.equals(item));
    }

    @Test
    void equals_returnsFalse_objectNotFixedPriceItem() {
        FixedPriceItem item = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        Object notItem = "Not an item";
        assertFalse(item.equals(notItem));
    }

    @Test
    void equals_returnsFalse_differentName() {
        FixedPriceItem apple = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem orange = new FixedPriceItem("Orange", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        assertFalse(apple.equals(orange));
    }

    @Test
    void equals_returnsFalse_differentPrice() {
        FixedPriceItem appleCheap = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem appleExpensive = new FixedPriceItem("Apple", Money.toMoney(15), ItemGroups.FRUKT_GRONT);
        assertFalse(appleCheap.equals(appleExpensive));
    }

    @Test
    void equals_returnsFalse_differentGroup() {
        FixedPriceItem apple = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem appleCandy = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.GODIS);
        assertFalse(apple.equals(appleCandy));
    }

    @Test
    void equals_returnsTrue_allSameValues() {
        FixedPriceItem i1 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem i2 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        assertTrue(i1.equals(i2));
    }

    @Test
    void hashCode_sameValues_sameHash() {
        FixedPriceItem i1 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem i2 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        assertEquals(i1.hashCode(), i2.hashCode());
    }

    @Test
    void hashCode_differentName_differentHash() {
        FixedPriceItem i1 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem i2 = new FixedPriceItem("Orange", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        assertNotEquals(i1.hashCode(), i2.hashCode());
    }

    @Test
    void hashCode_differentPrice_differentHash() {
        FixedPriceItem i1 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem i2 = new FixedPriceItem("Apple", Money.toMoney(15), ItemGroups.FRUKT_GRONT);
        assertNotEquals(i1.hashCode(), i2.hashCode());
    }

    @Test
    void hashCode_differentGroup_differentHash() {
        FixedPriceItem i1 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.FRUKT_GRONT);
        FixedPriceItem i2 = new FixedPriceItem("Apple", Money.toMoney(10), ItemGroups.GODIS);
        assertNotEquals(i1.hashCode(), i2.hashCode());
    }
}