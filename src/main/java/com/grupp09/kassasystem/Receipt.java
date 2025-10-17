package com.grupp09.kassasystem;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items = new ArrayList<>();

    public void addItem(Item item, double quantity, WeightUnit unit) {
        items.add(new ReceiptItem(item, quantity, unit));
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public Money calculateTotal() {
        Money total = Money.toMoney(0.0);

        for (ReceiptItem ri: items) {
            total.add(ri.getSubTotal());
        }

        return total;
    }

    public void printReceipt(){}
}
