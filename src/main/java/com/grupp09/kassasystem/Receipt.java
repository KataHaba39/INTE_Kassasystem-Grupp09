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
        Money total = new Money(0);

        for (ReceiptItem ri: items) {
            total.add(ri.getSubTotal());
        }

        return total;
    }

    public void printReceipt(){}
}
