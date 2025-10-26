package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    @Test
    void testSupplierCreatedCorrectly() {
        Supplier s = new Supplier("S001", "Arla", "kontakt@arla.se");

        assertEquals("S001", s.getSupplierId());
        assertEquals("Arla", s.getName());
        assertEquals("kontakt@arla.se", s.getContactInfo());
        assertTrue(s.isActive(), "Leverantören är aktiv från start");
    }

    @Test
    void testDeactivateAndActivate() {
        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");

        s.deActivate();
        assertFalse(s.isActive(), "Leverantören ska bli inaktiv efter deActivate()");

        s.activate();
        assertTrue(s.isActive(), "Leverantören ska bli aktiv efter activate()");
    }

    @Test
    void TestEmptySupplierIdThrowsError() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Supplier("", "Arla", "kontakt@arla.se");
        });
        assertEquals("Leverantörs ID får ej vara tom", e.getMessage());
    }

    @Test
    void testEmptyNameThrowsError() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Supplier("S003", "  ", "kontakt@arla.se");
        });
        assertEquals("Leverantörers namn får ej vara tom", e.getMessage());
    }

    @Test
    void getItems_ListIsEmpty_BeforeAddingItemToSupplier() {
        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");

        assertTrue(s.getItems().isEmpty(), "New supplier list not empty");
    }

    @Test
    void getItems_ContainsItem_AfterAddingItemToSupplier() {  //
        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);

        i.setSupplier(s);
        s.addItem(i);

        assertTrue(s.getItems().contains(i), "");
    }

    @Test
    void ifItemDoesNotHaveSupplier_addItemShouldFail() {  //
        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);

        assertFalse(s.addItem(i), "Item was added even though Item had no supplier");
    }

    // Kollar att listan hos supplyer inte innehåller en item, och man har först tagit bort supplyer från item
    @Test
    void doesNotContainItem_AfterRemovingItemFromSupplier_SupplierRemovedFromItemFirst() {  // Detta test är beroende av att testet över (getItems_ContainsItem_AfterAddingItemToSupplier) är godkänt
        getItems_ContainsItem_AfterAddingItemToSupplier();

        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);

        s.addItem(i);
        s.removeItem(i);

        assertFalse(s.getItems().contains(i), "Item was not removed");

    }

    @Test
    void ifItemStillHasTheSupplier_removeSupplierShouldFail() {
        getItems_ContainsItem_AfterAddingItemToSupplier();

        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);
        i.setSupplier(s);
        s.addItem(i);

        assertFalse(s.removeItem(i), "Item was removed without removing supplier first");
    }

    @Test
    void addItem_SameItemTwice_ShouldReturnFalseAndNotDuplicate() {
        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);
        i.setSupplier(s);

        assertTrue(s.addItem(i));
        assertFalse(s.addItem(i)); // second add should fail
        assertEquals(1, s.getItems().size());
    }

    @Test
    void removeItem_ItemNotInSupplier_ShouldReturnFalse() {
        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");
        Item i = new FixedPriceItem("Banana", Money.toMoney(50.0), ItemGroups.FRUKT_GRONT);
        i.setSupplier(s);

        Item j = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);
        j.setSupplier(s);

        assertFalse(s.removeItem(j), "Removing item not in supplier should fail");
    }

    @Test
    void getItems_ShouldReturnUnmodifiableSet() {
        Supplier s = new Supplier("S002", "Axfood", "info@axfood.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);
        i.setSupplier(s);
        s.addItem(i);

        List<Item> items = s.getItems();
        assertThrows(UnsupportedOperationException.class, () -> items.add(i));
        assertThrows(UnsupportedOperationException.class, () -> items.remove(i));
    }

    @Test
    void addItem_ItemWithDifferentSupplier_ShouldFail() {
        Supplier s1 = new Supplier("S001", "Axfood", "info@axfood.se");
        Supplier s2 = new Supplier("S002", "Coop", "info@coop.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);
        i.setSupplier(s1); // assigned to s1

        assertFalse(s2.addItem(i), "Cannot add item to a supplier that doesn't match item's supplier");
    }

    @Test
    void deActivateShouldWorkWhenSupplierHasNoItems() {
        Supplier s = new Supplier("S100", "ICA", "ica@ica.se");

        s.deActivate();

        assertFalse(s.isActive(), "Leverantör utan produkter ska kunna avaktiveras");
    }

    @Test
    void deActivateShouldThrowIfSupplierStillHasItems() {
        Supplier s = new Supplier("S200", "Willys", "kontakt@willys.se");
        Item i = new FixedPriceItem("Apple", Money.toMoney(100.0), ItemGroups.FRUKT_GRONT);

        i.setSupplier(s);
        s.addItem(i);

        assertThrows(IllegalStateException.class, s::deActivate, "Leverantör med produkter ska inte kunna avaktiveras");
    }

}