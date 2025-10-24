package com.grupp09.kassasystem;

public class WeightedItem implements Item {
    private final String itemName;
    private final Money pricePerUnit;
    private final WeightUnit weightUnit;
    private final ItemGroup itemGroup;
    private Supplier supplier;

    public WeightedItem(String itemName, Money pricePerUnit, WeightUnit weightUnit, ItemGroup itemGroup) {
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
    public ItemGroup getItemGroup() {
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

        if (this == other) {
            return true;
        }

        if (!(other instanceof WeightedItem)) {
            return false;
        }

        WeightedItem oW = (WeightedItem) other;

        return itemName.equals(oW.itemName) &&
                pricePerUnit.equals(oW.pricePerUnit) &&
                weightUnit == oW.weightUnit && // enums, kan jämföras med ==
                itemGroup == oW.itemGroup;
    }

    @Override
    public int hashCode() {
        int result = itemName.hashCode();
        result = 31 * result + pricePerUnit.hashCode();
        result = 31 * result + weightUnit.hashCode();
        result = 31 * result + itemGroup.hashCode();
        return result;
    }

}
