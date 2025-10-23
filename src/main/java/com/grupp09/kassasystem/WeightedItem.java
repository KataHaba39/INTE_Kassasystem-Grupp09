package com.grupp09.kassasystem;

public class WeightedItem implements Item {
    private final String itemName;
    private final Money pricePerUnit;
    private final WeightUnit weightUnit;
    private final ItemGroups itemGroup;

    public WeightedItem(String itemName, Money pricePerUnit, WeightUnit weightUnit, ItemGroups itemGroup) {
        this.itemName = itemName;
        this.pricePerUnit = pricePerUnit;
        if (weightUnit == null)  throw new NullPointerException("Weight unit is required!");
        this.weightUnit = weightUnit;
        this.itemGroup = itemGroup;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    @Override
    public Money getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public Money getTotalPrice(double amount, WeightUnit unit) {   //detta beror på hur Money är
        if (unit == null) throw new NullPointerException("Weight unit is required!");

        double pricePerGram = pricePerUnit.getValueAsDouble()/ weightUnit.gramsPerUnit();
        double grams = unit.toGrams(amount);

        Money raw = Money.toMoney(pricePerGram).multiply(grams); // vad är det som menas med raw? ursprungliga kodraden var double raw = grams * pricePerGram;
        return raw;
    }

    @Override
    public ItemGroups getItemGroup() {
        return itemGroup;
    }

}
