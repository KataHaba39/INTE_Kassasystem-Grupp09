package com.grupp09.kassasystem;

/* Varje leverantör ska ett Id, namn , kontaktinformation (nummer eller email) och om leverantör är aktiv eller oaktiv.
 * Leverantör Id och namn får ej vara tomma.
 * Man ska kunna lägga en leverantör som aktiv eller oaktiv, detta för att snabbt kunna ta tillbaka en leverantör som är aktiv igen utan att ta bort dom från systemet.
 */

public class Supplier {
    private final String supplierId;
    private final String name;
    private final String contactInfo;
    private boolean active;

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

    public void deActivate() {
        this.active = false;
    }
}
