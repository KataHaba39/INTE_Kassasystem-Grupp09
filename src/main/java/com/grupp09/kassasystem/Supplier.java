package com.grupp09.kassasystem;

/* Varje leverantör ska ett Id, namn , kontaktinformation (nummer eller email) och om leverantör är aktiv eller oaktiv.
 * Leverantör Id och namn får ej vara tomma.
 * Man ska kunna lägga en leverantör som aktiv eller oaktiv, detta för att snabbt kunna ta tillbaka en leverantör som är aktiv igen utan att ta bort dom från systemet.
 * En supplier kan ha flera items, för att ta bort en item måste man först ta bort supplier hos item innan, annars får man inte
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class Supplier implements Iterable<Item> {
    private final String supplierId;
    private final String name;
    private final String contactInfo;
    private boolean active;
    private final List<Item> items;

    public Supplier(String supplierId, String name, String contactInfo) {
        if(supplierId == null || supplierId.trim().isEmpty()) {
            throw new IllegalArgumentException("Leverantörs ID får ej vara tom");
        }

        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Leverantörers namn får ej vara tom");
        }

        this.supplierId = supplierId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.active = true;
        this.items = new ArrayList<>();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

    private boolean hasItems() {
        return !items.isEmpty();
    }

    public void deActivate() {
        if(hasItems()) {
            throw new IllegalStateException("Leverantör kan ej avaktiveras om produkter fortfarande finns kvar");
        }
        this.active = false;
    }

    public boolean addItem(Item item) {
        if (item.getSupplier() == null || !item.getSupplier().equals(this)) {
            return false;
        }

        if(items.contains(item)) {
            return false;
        }

        return items.add(item);
    }

    public boolean removeItem(Item item) {
        Supplier currentSupplier = item.getSupplier();

        if (currentSupplier != null && currentSupplier.equals(this)) {
            return false;
        }

        return items.remove(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
