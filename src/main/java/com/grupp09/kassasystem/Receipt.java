package com.grupp09.kassasystem;

import java.util.*;

public class Receipt {

    // har valt att betrakta två av samma produkt men olika weight unit som olika produktrader i kvitto, t.ex. skulle (kassa scannar) milk 1 st, milk 3 st, alla hamna på samma kvittorad och söga milk 4st
    // men om man först skannar apple 3,2kg och sedan skannar apple 120 gram kommer de att hamna på olika rader.

    // har just nu tom konstruktor, men om vi t.ex. vill implementera kassa / personal, skulle det kunna vara parametrar i en konstruktor


    private final Map<ReceiptItem, Double> nrOfItemsByName = new HashMap<>();
    private final List<ReceiptItem> items = new ArrayList<>();

    // Lägger till eller ökar kvantiteten
    public void addItem(Item item, double quantity, WeightUnit unit) {
        ReceiptItem ri = new ReceiptItem(item, unit);

        if (!nrOfItemsByName.containsKey(ri)) {
            items.add(ri);
        }

        nrOfItemsByName.compute(ri, (k, v) -> v == null ? quantity : v + quantity);
    }

    // Tar bort hela eller del av kvantiteten, returnerar borttagen mängd
    public double removeItem(Item item, double quantity, WeightUnit unit) {
        ReceiptItem key = new ReceiptItem(item, unit);
        Double currentQuantity = nrOfItemsByName.get(key);

        if (currentQuantity == null) {
            throw new NoSuchElementException("Item not on receipt");
        }

        double removed;
        if (quantity >= currentQuantity) {
            removed = currentQuantity;
            nrOfItemsByName.remove(key);
            items.remove(key);
        } else {
            removed = quantity;
            nrOfItemsByName.put(key, currentQuantity - quantity);
        }

        return removed;
    }

    // Beräknar totalpris
    public Money calculateTotal() {
        Money total = Money.toMoney(0.0);

        for (ReceiptItem ri : items) {
            double quantity = nrOfItemsByName.get(ri);
            total.add(ri.getSubTotal(quantity));
        }

        return total;
    }

    // Skriver ut kvittot
    public void printReceipt() {
        System.out.println(createReceipt());
    }

    private String createReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt\n");
        sb.append("####################\n");

        for (ReceiptItem ri : items) {
            double quantity = nrOfItemsByName.get(ri);
            Money subTotal = ri.getSubTotal(quantity);

            String line = ri.getDescription(quantity) + "  " + subTotal;
            sb.append(line).append("\n");
        }

        sb.append("----------------------------\n");
        sb.append(String.format("%-20s %s\n", "Total:", calculateTotal()));

        return sb.toString();
    }
}
