package com.grupp09.kassasystem;

public class ReceiptItem {
    private final Item item;
    private final WeightUnit unit;

    public ReceiptItem(Item item, WeightUnit unit) {
        this.item = item;
        this.unit = unit;
    }

    public Item getItem() {
        return item;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public Money getPledgeValue() {
        if (item instanceof FixedPriceItem fixedPriceItem) {
           return Pledge.pledgeOf(fixedPriceItem);
        }
        return null;
    }

    // Subtotal beräknas med kvantitet som skickas in från Receipt
    public Money getSubTotal(double quantity) {
        return item.getTotalPrice(quantity, unit);
    }

    public String getDescription(double quantity) {
        if (unit == null && (item.getItemGroup() == ItemGroups.DRYCK || item.getItemGroup() == ItemGroups.DRYCK_ALKOHOL)) {
            return item.getItemName() + " x" + (int) quantity + "\n Pant: " + getPledgeValue();
        } else if (unit == null) {
            return item.getItemName() + " x" + (int) quantity;
        } else  {
            return item.getItemName() + " " + quantity + " " + unit;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ReceiptItem)) {
            return false;
        }
        ReceiptItem o = (ReceiptItem) other;
        boolean itemsEqual = this.item.equals(o.item);
        boolean unitsEqual = this.unit == o.unit;
        return itemsEqual && unitsEqual;
    }

    @Override
    public int hashCode() {
        return item.hashCode() * 31 + (unit != null ? unit.hashCode() : 0);
    }
}

