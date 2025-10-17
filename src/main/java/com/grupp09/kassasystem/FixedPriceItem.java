package com.grupp09.kassasystem;

public class FixedPriceItem implements Item {
    private String itemName;
    private Money price;
    private final ItemGroups itemGroup;

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
}
