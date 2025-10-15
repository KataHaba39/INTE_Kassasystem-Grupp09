package com.grupp09.kassasystem;

public class WeightedItem {
    private final String itemName;
    private final double pricePerUnit;
    private final WeightUnit weightUnit;
    private final ItemGroups itemGroup;

    public WeightedItem(String itemName, double pricePerUnit, WeightUnit weightUnit, ItemGroups itemGroup) {
        this.itemName = itemName;
        this.pricePerUnit = pricePerUnit;
        if (weightUnit == null)  throw new NullPointerException("Weight unit is required!");
        this.weightUnit = weightUnit;
        this.itemGroup = itemGroup;
    }

    public String getName() {
        return itemName;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    public ItemGroups getItemGroup() {
        return itemGroup;
    }

    public double totalPrice(double amount, WeightUnit unit) {
        if (unit == null) throw new NullPointerException("Weight unit is required!");
        
        double pricePerGram = pricePerUnit / weightUnit.gramsPerUnit();
        double grams = unit.toGrams(amount); 
        double raw = grams * pricePerGram;
        return Math.round(raw * 100.0) / 100.0;
    }
}
