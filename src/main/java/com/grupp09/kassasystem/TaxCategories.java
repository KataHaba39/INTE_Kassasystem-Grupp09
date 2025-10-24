package com.grupp09.kassasystem;

// Skapade två olika sätt att hantera moms kategorier
// Vi kan ta bort en av dem senare om vi vill
public interface TaxCategories {
    int categoryIdFor(ItemGroup group);
    int vatBpsFor(ItemGroup group); // Basis points of percentage (t.ex 100 bps = 1%)
}