package com.grupp09.kassasystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Testklass av Receipt helt genererad av AI, tänkt att användas för diskussion

class ReceiptTest {

    @Test
    void testAddFixedPriceItem() {
        Receipt receipt = new Receipt();
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroup.DRINK);

        receipt.addItem(milk, 2, null); // 2 st milk
        receipt.addItem(milk, 3, null); // 3 st milk

        Money expectedTotal = Money.toMoney(10 * 5);
        assertEquals(expectedTotal, receipt.calculateTotal());
    }

    @Test
    void testAddWeightedItemsDifferentUnits() {
        Receipt receipt = new Receipt();
        Item appleKg = new WeightedItem("Apple", Money.toMoney(50), WeightUnit.KG, ItemGroup.FRUIT);
        Item appleG = new WeightedItem("Apple", Money.toMoney(50), WeightUnit.KG, ItemGroup.FRUIT);

        receipt.addItem(appleKg, 1, WeightUnit.KG);  // 1 kg
        receipt.addItem(appleG, 500, WeightUnit.G);  // 500 g = 0.5 kg

        double totalPrice = 50 * 1.5; // 1 kg + 0.5 kg = 1.5 kg * 50 SEK/kg
        assertEquals(Money.toMoney(totalPrice), receipt.calculateTotal());
        assertEquals(2, receipt.getItems().size()); // två rader eftersom units skiljer sig
    }

    @Test
    void testRemoveItemPartialQuantity() {
        Receipt receipt = new Receipt();
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroup.DRINK);

        receipt.addItem(milk, 5, null);
        double removed = receipt.removeItem(milk, 2, null);

        assertEquals(2, removed);
        assertEquals(Money.toMoney(10 * 3), receipt.calculateTotal()); // 3 kvar
    }

    @Test
    void testRemoveItemEntireQuantity() {
        Receipt receipt = new Receipt();
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroup.DRINK);

        receipt.addItem(milk, 3, null);
        double removed = receipt.removeItem(milk, 5, null); // tar mer än finns

        assertEquals(3, removed);
        assertEquals(Money.toMoney(0), receipt.calculateTotal());
        assertEquals(0, receipt.getItems().size());
    }

    @Test
    void testRemoveNonExistingItemThrows() {
        Receipt receipt = new Receipt();
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroup.DRINK);

        assertThrows(NoSuchElementException.class, () -> {
            receipt.removeItem(milk, 1, null);
        });
    }

    @Test
    void testReceiptOrderPreserved() {
        Receipt receipt = new Receipt();
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroup.DRINK);
        Item bread = new FixedPriceItem("Bread", Money.toMoney(15), ItemGroup.BAKERY);

        receipt.addItem(milk, 1, null);
        receipt.addItem(bread, 1, null);

        assertEquals("Milk", receipt.getItems().get(0).getItemName());
        assertEquals("Bread", receipt.getItems().get(1).getItemName());
    }
}