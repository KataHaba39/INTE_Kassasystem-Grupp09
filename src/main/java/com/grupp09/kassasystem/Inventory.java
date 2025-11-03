// Klass med AI

package com.grupp09.kassasystem;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<Item, Double> stock = new HashMap<>();

    public void addStock(Item item, double amount) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative amount to stock");
        }
        stock.put(item, getStock(item) + amount);
    }

    public void reduceStock(Item item, double amount) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot reduce negative amount from stock");
        }

        double current = getStock(item);
        if (current < amount) {
            throw new IllegalStateException("Not enough stock available");
        }

        stock.put(item, current - amount);
    }

    public double getStock(Item item) {
        return stock.getOrDefault(item, 0.0);
    }
}