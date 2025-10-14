package com.grupp09.kassasystem;

// Skapade två olika sätt att hantera moms kategorier
// Vi kan ta bort en av dem senare om vi vill
public interface TaxCategories {
    int categoryIdFor(ItemGroups group);
    int vatBpsFor(ItemGroups group); // Basis points of percentage (t.ex 100 bps = 1%)
}