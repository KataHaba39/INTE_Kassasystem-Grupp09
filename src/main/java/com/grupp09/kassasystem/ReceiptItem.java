package com.grupp09.kassasystem;

public class ReceiptItem {
    private final Item item;
    private final double quantity;
    private final WeightUnit unit;

    public ReceiptItem(Item item, double quantity, WeightUnit unit) {
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Money getSubTotal() {
        return item.getTotalPrice(quantity, unit);
    }

    public String getDescription() {
        if (unit == null) {
            return item.getItemName() + " x" + (int) quantity;
        } else {
            return item.getItemName() + " " + quantity + " " + unit;
        }
    }

    public Item getItem() {
        return item;
    }

    // beaktar endast Item och weight unit, quantity spelar ingen  roll
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ReceiptItem)) {
            return false;
        }
        ReceiptItem o = (ReceiptItem) other;
        return this.item.equals(o.item) && this.unit == o.unit;
    }

}
