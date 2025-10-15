package com.grupp09.kassasystem;

public interface Item {
    String getItemName();
    ItemGroups getItemGroup();
    Money getPricePerUnit();
    Money getTotalPrice(double amount, WeightUnit unit);
}
