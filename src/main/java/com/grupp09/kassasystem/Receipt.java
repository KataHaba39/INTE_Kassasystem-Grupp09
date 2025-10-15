package com.grupp09.kassasystem;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items = new ArrayList<>();

    public void addItem(Item item, double quantity, WeightUnit unit) {
        items.add(new ReceiptItem(item, quantity, unit));
    }

    public Item removeItem(Item item) {
        if (!items.contains(item)) {
            return null;
        }
        return items.remove(item);
    }

    public Money calculateTotal() {
        double total = 0;

        for (ReceiptItem ri: items) {
            total = total + ri.getSubtotal().getValue(); // beror på Money
        }

        return new Money(total);
    }

    public void printReceipt(){}
}
