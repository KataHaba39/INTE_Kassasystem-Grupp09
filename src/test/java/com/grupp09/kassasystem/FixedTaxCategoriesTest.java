package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FixedTaxCategoriesTest {

    private final FixedTaxCategories tax = new FixedTaxCategories();

    @Test
    void testCategoryIdFor_LowVat_Groups_returns1() {
        assertEquals(1, tax.categoryIdFor(ItemGroups.KOTT));
        assertEquals(1, tax.categoryIdFor(ItemGroups.SKALDJUR));
        assertNotEquals(1, tax.categoryIdFor(ItemGroups.TOBAK));
    }

    @Test
    void testCategoryIdFor_MediumVat_Groups_returns2() {
        assertEquals(2, tax.categoryIdFor(ItemGroups.FARDIGMAT));
        assertEquals(2, tax.categoryIdFor(ItemGroups.FRUKT_GRONT));
        assertEquals(2, tax.categoryIdFor(ItemGroups.MEJERI));
        assertEquals(2, tax.categoryIdFor(ItemGroups.BROD));
        assertNotEquals(2, tax.categoryIdFor(ItemGroups.GODIS));
        assertEquals(2, tax.categoryIdFor(ItemGroups.MEJERI));
    }

    @Test
    void testCategoryIdFor_HighVat_Groups_returns3() {
        assertEquals(3, tax.categoryIdFor(ItemGroups.TOBAK));
        assertEquals(3, tax.categoryIdFor(ItemGroups.GODIS));
        assertEquals(3, tax.categoryIdFor(ItemGroups.DRYCK));
        assertEquals(3, tax.categoryIdFor(ItemGroups.DRYCK_ALKOHOL));
        assertNotEquals(3, tax.categoryIdFor(ItemGroups.KOTT));
        System.out.println("The category ID of tobacco is " + tax.categoryIdFor(ItemGroups.TOBAK));
    }

    @Test
    void testAllCategoryBranches_Individually() {
        assertEquals(1, tax.categoryIdFor(ItemGroups.KOTT));
        assertEquals(1, tax.categoryIdFor(ItemGroups.SKALDJUR));

        assertEquals(2, tax.categoryIdFor(ItemGroups.FARDIGMAT));
        assertEquals(2, tax.categoryIdFor(ItemGroups.FRUKT_GRONT));
        assertEquals(2, tax.categoryIdFor(ItemGroups.MEJERI));
        assertEquals(2, tax.categoryIdFor(ItemGroups.BROD));

        assertEquals(3, tax.categoryIdFor(ItemGroups.TOBAK));
        assertEquals(3, tax.categoryIdFor(ItemGroups.GODIS));
        assertEquals(3, tax.categoryIdFor(ItemGroups.DRYCK));
        assertEquals(3, tax.categoryIdFor(ItemGroups.DRYCK_ALKOHOL));

        assertEquals(3, tax.categoryIdFor(new ItemGroup("Unknown", 0)));
    }


    @Test
    void testDefault_CategoryIdFor_UnknownGroup_returns3() {
        ItemGroup unknownGroup = new ItemGroup("Unknown", 0);
        assertEquals(3, tax.categoryIdFor(unknownGroup));
    }

    @Test
    void testVatBpsFor_Groups_returnsCorrectVatBps() {
        assertEquals(600, tax.vatBpsFor(ItemGroups.KOTT));
        assertEquals(1200, tax.vatBpsFor(ItemGroups.BROD));
        assertEquals(2500, tax.vatBpsFor(ItemGroups.TOBAK));
        assertEquals(2500, tax.vatBpsFor(ItemGroups.DRYCK_ALKOHOL));
    }

    @Test
    void testVatBpsFor_ItemGroups_returnsCorrectVatBps() {
        assertEquals(2500, tax.vatBpsFor(ItemGroups.TOBAK));
    }

    @Test
    void testAllVatBranches_Individually() {
        assertEquals(600, tax.vatBpsFor(ItemGroups.KOTT));
        assertEquals(600, tax.vatBpsFor(ItemGroups.SKALDJUR));

        assertEquals(1200, tax.vatBpsFor(ItemGroups.FARDIGMAT));
        assertEquals(1200, tax.vatBpsFor(ItemGroups.FRUKT_GRONT));
        assertEquals(1200, tax.vatBpsFor(ItemGroups.MEJERI));
        assertEquals(1200, tax.vatBpsFor(ItemGroups.BROD));

        assertEquals(2500, tax.vatBpsFor(ItemGroups.TOBAK));
        assertEquals(2500, tax.vatBpsFor(ItemGroups.GODIS));
        assertEquals(2500, tax.vatBpsFor(ItemGroups.DRYCK));
        assertEquals(2500, tax.vatBpsFor(ItemGroups.DRYCK_ALKOHOL));

        assertEquals(2500, tax.vatBpsFor(new ItemGroup("Unknown", 0)));
    }

    @Test
    void testDefault_vatBpsFor_UnknownGroup_returnsCorrectVatBps() {
        ItemGroup unknown = new ItemGroup("Unknown", 0);
        assertEquals(2500, tax.vatBpsFor(unknown));
    }
}
