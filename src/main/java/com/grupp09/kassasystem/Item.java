package com.grupp09.kassasystem;

public class Item {
    private String itemName;
    private double price;
    private final ItemGroups itemGroup;

    public Item(String itemName, double price, ItemGroups itemGroup) {
        this.itemName = itemName;
        this.price = price;
        this.itemGroup = itemGroup;
    }

    public double getPrice(){
        return price;
    }

    public String getItemName(){
        return itemName;
    }

    public ItemGroups getItemGroup() {
        return itemGroup;
    }
}
