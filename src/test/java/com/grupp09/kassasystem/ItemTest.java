package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testItem(){
        Item i = new Item("Apple", 10.0);
        assertEquals(10.0, i.getPrice());
        assertEquals("Apple", i.getItemName());
    }

}