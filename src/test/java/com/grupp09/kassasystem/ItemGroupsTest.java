package com.grupp09.kassasystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ItemGroupsTest {

    @Test
    void test_alcoholRequires_ageRestriction() {
        assertTrue(ItemGroups.DRYCK_ALKOHOL.isAgeRestricted());
        assertFalse(ItemGroups.DRYCK_ALKOHOL.isAllowedFor(17));
        assertTrue(ItemGroups.DRYCK_ALKOHOL.isAllowedFor(19));
    }

    @Test
    void test_tobaccoRequires_ageRestriction() {
        assertTrue(ItemGroups.TOBAK.isAgeRestricted());
        assertFalse(ItemGroups.TOBAK.isAllowedFor(17));
        assertTrue(ItemGroups.TOBAK.isAllowedFor(21));
    }

    @Test
    void test_nonAgeRestricted_itemGroups() {
        for (ItemGroup group : ItemGroups.getAllGroups()) {
            if (group != ItemGroups.DRYCK_ALKOHOL && group != ItemGroups.TOBAK) {
                assertFalse(group.isAgeRestricted(), group.getName() + " should not be age restricted");
                assertTrue(group.isAllowedFor(0), group.getName() + " should allow age 0");
                assertTrue(group.isAllowedFor(100), group.getName() + " should allow age 100");
            }
        }
    }

    @Test
    void test_allAgeRestricted_itemGroups_require_age_over_17() {
        for (ItemGroup group : ItemGroups.getAllGroups()) {
            if (group.isAgeRestricted()) {
                assertFalse(group.isAllowedFor(17), group.getName() + " should not allow age 17");
                assertTrue(group.isAllowedFor(18), group.getName() + " should allow age 18");
            }
        }
    }

    @Test
    void test_exact_boundaryAge_allowed() {
        assertTrue(ItemGroups.TOBAK.isAllowedFor(18));
        assertTrue(ItemGroups.DRYCK_ALKOHOL.isAllowedFor(18));
    }

    @Test
    void testGetName() {
        ItemGroup group = new ItemGroup("KOTT", 0);
        assertEquals("KOTT", group.getName());
    }

    @Test
    void testGetMinimumAge() {
        ItemGroup group = new ItemGroup("DRYCK_ALKOHOL", 18);
        assertEquals(18, group.getMinimumAge());
    }

    @Test
    void testIsAllowedFor_exactMatch_minimumAge() {
        ItemGroup group = new ItemGroup("ItemGroupWithAgeRestriction", 21);
        assertTrue(group.isAllowedFor(21));
    }

    @Test
    void test_validateMinimumAge_throws_exception_for_negative() {
        assertThrows(IllegalArgumentException.class, () -> ItemGroup.validateMinimumAge(-5));
    }

    @Test
    void test_validateMinimumAge_accepts_zero_and_positive() {
        assertEquals(0, ItemGroup.validateMinimumAge(0));
        assertEquals(21, ItemGroup.validateMinimumAge(21));
    }

    @Test
    void test_toString_contains_name_and_minimumAge() {
        ItemGroup group = new ItemGroup("TOBAK",18);
        String result = group.toString();
        assertTrue(result.contains("TOBAK"));
        assertTrue(result.contains("18"));
        String expected = "TOBAK (min age: 18)";
        assertEquals(expected, group.toString());
    }

    @Test
    void test_getAllGroups_returns_unmodifiable_list() {
        var groups = ItemGroups.getAllGroups();
        assertThrows(UnsupportedOperationException.class, () -> groups.add(new ItemGroup("TEST", 0)));
    }
}
