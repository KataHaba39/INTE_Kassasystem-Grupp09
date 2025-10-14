package com.grupp09.kassasystem;

public class Item {
    private String itemName;
    private double price;

    public Item(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public String getItemName(){
        return itemName;
    }
}
