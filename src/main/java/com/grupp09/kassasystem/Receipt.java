package com.grupp09.kassasystem;

import java.util.*;

public class Receipt {

    // har valt att betrakta två av samma produkt men olika weight unit som olika produktrader i kvitto, t.ex. skulle (kassa scannar) milk 1 st, milk 3 st, alla hamna på samma kvittorad och söga milk 4st
    // men om man först skannar apple 3,2kg och sedan skannar apple 120 gram kommer de att hamna på olika rader.
    // Kom inte på något smidigt sätt att lösa detta (olika weight unit konsolideras till 1 gemensam) inom Receipt klasser, men går sökert på något sätt

    // har just nu tom konstruktor, men om vi t.ex. vill implementera kassa / personal, skulle det kunna vara parametrar i en konstruktor
    // t.ex. new Receipt(Employee employee, Time time);

    private final Map<ReceiptItem, Double> nrOfItemsByName = new HashMap<>();
    private final List<ReceiptItem> items = new ArrayList<>();

    public boolean addItem(Item item, double quantity, WeightUnit unit) {
        ReceiptItem ri = new ReceiptItem(item, quantity, unit);
        nrOfItemsByName.compute(ri, (k, v) -> v == null ? quantity : v + quantity);
    }

    public Item removeItem(Item item) {
        ReceiptItem removed = nrOfItemsByName.remove(item);
        if (removed == null) {
            throw new NoSuchElementException();
        }
        return removed.getItem();
    }

    public Money calculateTotal() {
        Money total = Money.toMoney(0.0);

        for (ReceiptItem ri: items) {
            total.add(ri.getSubTotal());
        }

        return total;
    }

    public void printReceipt(){

    }
}
