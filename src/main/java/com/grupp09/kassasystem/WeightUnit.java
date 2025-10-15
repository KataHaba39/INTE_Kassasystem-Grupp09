package com.grupp09.kassasystem;

public enum WeightUnit {
    GRAM(1), HEKTO(100), KILO(1000);

    private final int gramsPerUnit;
    WeightUnit(int gramsPerUnit) {
        this.gramsPerUnit = gramsPerUnit;
    }

    public int gramsPerUnit() {
        return gramsPerUnit;
    }
    
    public double toGrams(double weight) {
        return weight * gramsPerUnit;
    }
}
    
