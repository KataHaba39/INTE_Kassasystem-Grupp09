package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class TaxCategoryTest {

    @Test
    public void testingCategoryIdForItemGroups() {
        FixedTaxCategories tax = new FixedTaxCategories();
        assertEquals(2, tax.categoryIdFor(ItemGroups.FARDIGMAT));
        assertEquals(3, tax.categoryIdFor(ItemGroups.TOBAK));
        System.out.println("The category ID of bread is " + tax.categoryIdFor(ItemGroups.BROD));
    }

    @Test
    public void testingVatBpsForItemGroups() {
        FixedTaxCategories tax = new FixedTaxCategories();
        assertEquals(1200, tax.vatBpsFor(ItemGroups.FARDIGMAT));
        assertEquals(2500, tax.vatBpsFor(ItemGroups.TOBAK));
        System.out.println("The VAT bps of bread is " + tax.vatBpsFor(ItemGroups.BROD));
    }
    
}
