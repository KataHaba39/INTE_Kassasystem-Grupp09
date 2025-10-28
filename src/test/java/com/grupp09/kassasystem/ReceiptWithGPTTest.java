package com.grupp09.kassasystem;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class ReceiptWithGPTTest {
    @Test
    void testDiscountForMember() {
        // Skapa ett medlemskap, t.ex. med 10% rabatt
        Membership membership = new Membership("name", 10, 2616212, "home");
        Customer memberCustomer = new Customer("1212", "Bengt Bengtsson", "0046705556666", "bengtkungen@gmail.com");
        memberCustomer.setMembership(membership);

        ReceiptWithGPT receipt = new ReceiptWithGPT(memberCustomer);

        Item milk = new FixedPriceItem("Milk", Money.toMoney(20), ItemGroups.MEJERI);
        receipt.addItem(milk, 2, null); // Totalt 40 SEK

        // Kontrollera att total utan rabatt är korrekt
        assertEquals(Money.toMoney(40), receipt.calculateTotal());

        // Kontrollera att total med rabatt är korrekt (10% rabatt)
        Money expectedDiscounted = Money.toMoney(40).multiply(0.9); // 36 SEK
        assertEquals(expectedDiscounted, receipt.calculateTotalWithDiscount());

        // Kontrollera att kvittot innehåller rabatten
        String receiptString = receipt.createReceipt();
        assertTrue(receiptString.contains("Discounted Total:"));
        assertTrue(receiptString.contains("36.00")); // eller Money.toMoney(36).toString()
    }

    @Test
    void testAddFixedPriceItem() {
        Customer customer = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);

        receipt.addItem(milk, 2, null); // 2 st milk
        receipt.addItem(milk, 3, null); // 3 st milk

        Money expectedTotal = Money.toMoney(10 * 5);
        assertEquals(expectedTotal, receipt.calculateTotal());
    }

    @Test
    void testAddWeightedItemsDifferentUnits() {
        Customer customer = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);
        Item appleKg = new WeightedItem("Apple", Money.toMoney(50), WeightUnit.KILO, ItemGroups.FRUKT_GRONT);
        Item appleG = new WeightedItem("Apple", Money.toMoney(50), WeightUnit.KILO, ItemGroups.FRUKT_GRONT);

        receipt.addItem(appleKg, 1, WeightUnit.KILO); // 1 kg
        receipt.addItem(appleG, 500, WeightUnit.GRAM); // 500 g = 0.5 kg

        double totalPrice = 50 * 1.5; // 1 kg + 0.5 kg = 1.5 kg * 50 SEK/kg
        assertEquals(Money.toMoney(totalPrice), receipt.calculateTotal());
        assertEquals(2, receipt.getItems().size()); // två rader eftersom units skiljer sig
    }

    @Test
    void testRemoveItemPartialQuantity() {
        Customer customer = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);

        receipt.addItem(milk, 5, null);
        double removed = receipt.removeItem(milk, 2, null);

        assertEquals(2, removed);
        assertEquals(Money.toMoney(10 * 3), receipt.calculateTotal()); // 3 kvar
    }

    @Test
    void testRemoveItemEntireQuantity() {
        Customer customer = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);

        receipt.addItem(milk, 3, null);
        double removed = receipt.removeItem(milk, 5, null); // tar mer än finns

        assertEquals(3, removed);
        assertEquals(Money.toMoney(0), receipt.calculateTotal());
        assertEquals(0, receipt.getItems().size());
    }

    @Test
    void testRemoveNonExistingItemThrows() {
        Customer customer = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);

        assertThrows(NoSuchElementException.class, () -> receipt.removeItem(milk, 1, null));
    }

    @Test
    void testReceiptOrderPreserved() {
        Customer customer = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);
        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);
        Item bread = new FixedPriceItem("Bread", Money.toMoney(15), ItemGroups.BROD);

        receipt.addItem(milk, 1, null);
        receipt.addItem(bread, 1, null);

        assertEquals("Milk", receipt.getItems().get(0).getItem().getItemName());
        assertEquals("Bread", receipt.getItems().get(1).getItem().getItemName());
    }

    @Test
    void testReceiptWithCustomer() {
        Customer customer = new Customer("0001", "Anna Andersson", "0046701234567", "annaaa@example.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);

        assertEquals(customer, receipt.getCustomer());
        assertEquals("Anna Andersson", receipt.getCustomer().getName());
        assertEquals("0001", receipt.getCustomer().getCustomerId());

        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);
        receipt.addItem(milk, 1, null);

        assertEquals(Money.toMoney(10), receipt.calculateTotal());
    }

    @Test
    void testPrintReceiptWithCustomer() {
        Customer customer = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        ReceiptWithGPT receipt = new ReceiptWithGPT(customer);

        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);
        receipt.addItem(milk, 2, null);

        // Testa att kvittot skrivs ut utan fel
        assertDoesNotThrow(() -> receipt.printReceipt());

        // Kontrollera att kundens namn finns i kvittosträngen
        String receiptString = receipt.createReceipt();
        assertTrue(receiptString.contains("Anna Andersson"));
        assertTrue(receiptString.contains("Customer:"));
    }

    @Test
    void testReceiptWithNullCustomer() {
        // Test med null customer (om det ska vara möjligt)
        ReceiptWithGPT receipt = new ReceiptWithGPT(null);

        assertNull(receipt.getCustomer());

        Item bread = new FixedPriceItem("Bread", Money.toMoney(15), ItemGroups.BROD);
        receipt.addItem(bread, 1, null);

        // Kvittot ska skrivas ut utan fel även utan kund
        assertDoesNotThrow(() -> receipt.printReceipt());

        String receiptString = receipt.createReceipt();
        assertFalse(receiptString.contains("Customer:"));
    }

    @Test
    void testMultipleCustomerReceipts() {
        Customer customer1 = new Customer("0000", "Anna Andersson", "0046700000000", "andersson_anna@gmail.com");
        Customer customer2 = new Customer("1212", "Bengt Bengtsson", "0046705556666", "bengtkungen@gmail.com");

        ReceiptWithGPT receipt1 = new ReceiptWithGPT(customer1);
        ReceiptWithGPT receipt2 = new ReceiptWithGPT(customer2);

        Item milk = new FixedPriceItem("Milk", Money.toMoney(10), ItemGroups.MEJERI);

        receipt1.addItem(milk, 1, null);
        receipt2.addItem(milk, 2, null);

        assertEquals("Anna Andersson", receipt1.getCustomer().getName());
        assertEquals("Bengt Bengtsson", receipt2.getCustomer().getName());
        assertEquals(Money.toMoney(10), receipt1.calculateTotal());
        assertEquals(Money.toMoney(20), receipt2.calculateTotal());
    }
}
