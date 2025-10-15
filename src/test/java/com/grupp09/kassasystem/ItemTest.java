package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testItem(){
        Item i = new Item("Apple", 10.0, ItemGroups.FRUKT_GRONT); // Vi skapar den tredje argumenten, itemGroup, så detta test ändrar jag.
        assertEquals(10.0, i.getPrice());
        assertEquals("Apple", i.getItemName());
        assertEquals(ItemGroups.FRUKT_GRONT, i.getItemGroup());
    }

    @Test
    void item_has_group_and_price(){
        Item bröd = new Item("Bröd", 5.0, ItemGroups.BROD);
        assertEquals(ItemGroups.BROD, bröd.getItemGroup());
        assertEquals(5.0, bröd.getPricePerUnit());
        System.out.println("Item group: " + bröd.getItemGroup() + ", Price: " + bröd.getPrice());
    }
}