package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ItemGroupsTest {
    @Test
    void test_alcohol_requires_ageRestriction() {
        assertTrue(ItemGroups.DRYCK_ALKOHOL.isAgeRestricted());
        assertFalse(ItemGroups.DRYCK_ALKOHOL.isAllowedFor(17));
        assertTrue(ItemGroups.DRYCK_ALKOHOL.isAllowedFor(19));

        System.out.println("Alcohol age restriction tests passed.");
    }

    @Test
    void test_tobacco_requires_ageRestriction() {
        assertTrue(ItemGroups.TOBAK.isAgeRestricted());
        assertFalse(ItemGroups.TOBAK.isAllowedFor(17));
        assertTrue(ItemGroups.TOBAK.isAllowedFor(21));

        System.out.println("Tobacco age restriction tests passed.");
    }

    @Test // Non-age-restricted item groups
    void test_non_age_restricted_items() {
        for (ItemGroups group : ItemGroups.values()) {
            if (group != ItemGroups.DRYCK_ALKOHOL && group != ItemGroups.TOBAK) {
                assertFalse(group.isAgeRestricted());
                assertTrue(group.isAllowedFor(0));
                assertTrue(group.isAllowedFor(100));
            }
        }
    }

    @Test
    void test_exact_boundary_age_allowed() {
        assertTrue(ItemGroups.TOBAK.isAllowedFor(18));
        assertTrue(ItemGroups.DRYCK_ALKOHOL.isAllowedFor(18));
    }

    @Test
    void test_validateMinimumAge_throws_exception_for_negative() {
        assertThrows(IllegalArgumentException.class, () -> ItemGroups.validateMinimumAge(-5));
    }

    @Test
    void test_validateMinimumAge_accepts_zero_and_positive() {
        assertEquals(0, ItemGroups.validateMinimumAge(0));
        assertEquals(27, ItemGroups.validateMinimumAge(27));
    }

}
