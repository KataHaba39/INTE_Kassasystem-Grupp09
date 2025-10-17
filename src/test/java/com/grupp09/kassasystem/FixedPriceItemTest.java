package com.grupp09.kassasystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FixedPriceItemTest {

    private FixedPriceItem item;
    private Money price;

    @BeforeEach
    void setUp() {
        price = Money.toMoney(100.0);
        item = new FixedPriceItem("Apple", price, ItemGroups.FRUKT_GRONT);
    }

    @Test
    void testGetItemName() {
        assertEquals("Apple", item.getItemName());
    }

    @Test
    void testGetItemGroup() {
        assertEquals(ItemGroups.FRUKT_GRONT, item.getItemGroup());
    }

    @Test
    void testGetPricePerUnit() {
        assertEquals(0, price.getValue().compareTo(item.getPricePerUnit().getValue()));
    }

    @Test
    void testGetTotalPrice() {
        Money total = item.getTotalPrice(2, null);
        BigDecimal expected = price.getValue().multiply(BigDecimal.valueOf(2));
        assertEquals(0, expected.compareTo(total.getValue()));
    }

    @Test
    void testCurrencyIsAlwaysSEK() {
        assertEquals("SEK", item.getPricePerUnit().getCurrency());
    }

}