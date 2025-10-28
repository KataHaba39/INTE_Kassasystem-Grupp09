package com.grupp09.kassasystem;

import java.util.List;

public class TestData {
    
    public static Customer customer(String id, String name, String phoneNumber,  String email) {
        return new Customer(id,name, phoneNumber, email);
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
        Customer c1 = customer("1234", "Assil Aldabak", "0046701234567", "Assil@gmail.com");
        Customer c2 = customer("2345", "Enes Celik", "0046709876543", "Namn@gmail.com" );

        Receipt r1 = receiptWithLineItems(c1, new String[]{"Milk"}, new double[]{20.00});

        Receipt r2 = receiptWithLineItems(c2, new String[]{"Bread"}, new double[]{15.00});

        return List.of(r1, r2);
    }

    public static Receipt lowSpenderReceipt() {
        Customer low = customer("2222", "Liten Kund", "0046701111222", "litenkund@gmail.com");

        return receiptWithLineItems(low, new String[]{"Apple", "Banana"}, new double[]{10.00, 5.00});
    }

    public static Receipt highSpenderReceipt() {
        Customer high = customer("3333", "Stor Kund", "0046703333444", "storkund@gmail.com");

        return receiptWithLineItems(high, new String[]{"Steak", "Wine"}, new double[]{120.00, 230.00});
    }
}
