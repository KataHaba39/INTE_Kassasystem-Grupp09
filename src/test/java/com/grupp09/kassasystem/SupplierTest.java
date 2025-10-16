package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    @Test
    void testSupplierCreatedCorrectly() {
        Supplier s = new Supplier("S001", "Arla", "konakt@arla.se");

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
        assertEquals("Leverantörs ID kan ej vara tom", e.getMessage());
    }

    @Test
    void testEmptyNameThrowsError() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Supplier("S003", "  ", "kontakt@arla.se");
        });
        assertEquals("Leverantörs namn kan ej vara tom", e.getMessage());
    }
}