package com.grupp09.kassasystem;

public class FixedPriceItem implements Item {
    private String itemName;
    private Money price;
    private final ItemGroups itemGroup;
    private Supplier supplier;

    public FixedPriceItem(String itemName, Money price, ItemGroups itemGroup) {
        this.itemName = itemName;
        this.price = price;
        this.itemGroup = itemGroup;
    }

    @Override
    public Money getPricePerUnit(){
        return price;
    }

    @Override
    public Money getTotalPrice(double quantity, WeightUnit unit) {//kan ändras, tar just nu null, mer för att kunna ha polymorfism
        return price.multiply(quantity);
    }

    @Override
    public String getItemName(){
        return itemName;
    }

    @Override
    public ItemGroups getItemGroup() {
        return itemGroup;
    }

    @Override
    public boolean setSupplier(Supplier supplier) {
        if (this.supplier != null) {
            return false;
        }
        this.supplier = supplier;
        return true;
    }

    @Override
    public boolean removeSupplier() {
        if (supplier == null) {
            return false;
        }
        supplier = null;
        return true;
    }

    @Override
    public Supplier getSupplier() {
        return supplier;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FixedPriceItem)) {
            return false;
        }

        FixedPriceItem oF = (FixedPriceItem) other;

        return itemName.equals(oF.itemName) &&
                price.equals(oF.price) &&
                itemGroup == oF.itemGroup;  // en enum så kan jämföras med ==
    }

    @Override
    public int hashCode() {
        int result = itemName != null ? itemName.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (itemGroup != null ? itemGroup.hashCode() : 0);
        return result;
    }
}
