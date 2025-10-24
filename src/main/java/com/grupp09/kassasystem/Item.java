package com.grupp09.kassasystem;

public interface Item {
    boolean setSupplier(Supplier supplier);
    boolean removeSupplier();
    Supplier getSupplier();
    String getItemName();
    ItemGroup getItemGroup();
    Money getPricePerUnit();
    Money getTotalPrice(double amount, WeightUnit unit);
}
