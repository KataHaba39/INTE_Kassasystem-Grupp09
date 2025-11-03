// Testklass helt gjord med AI

package com.grupp09.kassasystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private Item fixedItem;
    private Item weightedItem;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        fixedItem = new FixedPriceItem("Cola", Money.toMoney(15.00), ItemGroups.DRYCK);
        weightedItem = new WeightedItem("Apples", Money.toMoney(30.00), WeightUnit.KILO, ItemGroups.FRUKT_GRONT);
    }

    @Test
    void testAddStockForFixedItem() {
        inventory.addStock(fixedItem, 10);
        assertEquals(10.0, inventory.getStock(fixedItem));
    }

    @Test
    void testAddStockForWeightedItem() {
        inventory.addStock(weightedItem, 5.0);
        assertEquals(5.0, inventory.getStock(weightedItem));
    }

    @Test
    void testReduceStockAfterPurchase() {
        inventory.addStock(fixedItem, 10);
        inventory.reduceStock(fixedItem, 3);
        assertEquals(7.0, inventory.getStock(fixedItem));
    }

    @Test
    void testReduceStockCannotGoBelowZero() {
        inventory.addStock(fixedItem, 2);
        assertThrows(IllegalStateException.class, () -> inventory.reduceStock(fixedItem, 5));
    }

    @Test
    void testGetStockForUnknownItemIsZero() {
        assertEquals(0.0, inventory.getStock(weightedItem));
    }

    @Test
    void testAddNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> inventory.addStock(fixedItem, -2));
    }

    @Test
    void testReduceNegativeAmountThrowsException() {
        inventory.addStock(fixedItem, 5);
        assertThrows(IllegalArgumentException.class, () -> inventory.reduceStock(fixedItem, -1));
    }

    @Test
    void testAddNullItemThrowsException() {
        assertThrows(NullPointerException.class, () -> inventory.addStock(null, 5));
    }

    @Test
    void testReduceNullItemThrowsException() {
        assertThrows(NullPointerException.class, () -> inventory.reduceStock(null, 5));
    }
}
