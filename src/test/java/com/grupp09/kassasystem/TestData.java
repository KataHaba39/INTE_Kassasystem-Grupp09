package com.grupp09.kassasystem;

import java.util.List;

public class TestData {
    
    public static Customer customer(String id, String name) {
        return new Customer(id,name, null, null);
    }

    public static Receipt receiptWithLineItems(Customer customer, String[] itemNames, double[] itemPrices) {
        Receipt receipt = new Receipt(customer);

        for(int i = 0; i < itemNames.length; i++) {
            Item item = new FixedPriceItem(
                itemNames[i],
                Money.toMoney(itemPrices[i]),
                ItemGroups.FRUKT_GRONT
            );

            receipt.addItem(item, 1.0, WeightUnit.KILO);
        }

        return receipt;
    }


    public static List<Receipt> exampleReceiptsTwoCustomers() {
        Customer c1 = customer("C001", "Assil");
        Customer c2 = customer("C002", "Jenna");

        Receipt r1 = receiptWithLineItems(c1, new String[]{"Milk"}, new double[]{20.00});

        Receipt r2 = receiptWithLineItems(c2, new String[]{"Bread"}, new double[]{15.00});

        return List.of(r1, r2);
    }

    public static Receipt lowSpenderReceipt() {
        Customer low = customer("C10", "Liten Kund");

        return receiptWithLineItems(low, new String[]{"Apple", "Banana"}, new double[]{10.00, 5.00});
    }

    public static Receipt highSpenderReceipt() {
        Customer high = customer("C20", "Stor Kund");

        return receiptWithLineItems(high, new String[]{"Steak", "Wine"}, new double[]{120.00, 230.00});
    }
}
