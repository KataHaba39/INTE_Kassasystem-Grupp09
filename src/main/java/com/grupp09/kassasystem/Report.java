package com.grupp09.kassasystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class Report {

    private Report() {
       
    }

    public static void generateCustomerReport(List<Customer> customers, String filePath) {
        String html = buildCustomerReportHtml(customers);
        try(FileWriter writer = new FileWriter(filePath)) {
            writer.write(html);
            System.out.println("Rapport skapad: " + filePath);
        } catch (IOException e) {
            System.err.println("Rapport kunde inte skrivas: " + e.getMessage());
        }
    }

    public static void printCustomerReport(List<Customer> customers) {
        System.out.println("====== Customer Report ======");
        for(Customer c : customers) {
            System.out.println("ID: " + safe(c.getCustomerId())
            + ", Name: " + safe(c.getName())
            + ", Phone: " + safe(c.getPhoneNumber())
            + ", Email: " + safe(c.getEmail()));
        }
        System.out.println("==========================");
    }

    public static String buildCustomerReportHtml(List<Customer> customers) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><meta charset=\"UTF-8\"><title>Customer Report</title><head><body>");
        html.append("<h1>Customer Report</h1>");
        html.append("<table border='1'><tr><th>ID</th><th>Name</th><th>Phone</th><th>Email</th></tr>");

        for(Customer c : customers) {
            html.append("<tr>")
                .append("<td>").append(safe(c.getCustomerId())).append("</td>")
                .append("<td>").append(safe(c.getName())).append("</td>")
                .append("<td>").append(safe(c.getPhoneNumber())).append("</td>")
                .append("<td>").append(safe(c.getEmail())).append("</td>")
                .append("</tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }

    public static void generateReceiptReport(List<Receipt> receipts, String filePath) {
        String html = buildReceiptReportHtml(receipts);
        try(FileWriter writer = new FileWriter(filePath)) {
            writer.write(html);
            System.out.println("Receipt report created: " + filePath);
        } catch (IOException e) {
            System.err.println("Receipt report could not be written: " + e.getMessage());
        }
    }

    public static void printReceiptReport(List<Receipt> receipts) {
        System.out.println("====== Receipt Report ======");
        Money totalSales = Money.toMoney(0.0);

        for(int i = 0; i < receipts.size(); i++) {
            Receipt r = receipts.get(i);
            Money receiptTotal = r.calculateTotal();
            System.out.println("Receipt: " + (i+1) + ": " + receiptTotal + " (" + r.getItems().size() + "items)");
            totalSales = totalSales.add(receiptTotal);
        }

        System.out.println("--------------------------");
        System.out.println("Total Sales: " + totalSales);
        System.out.println("Number of receipts: " + receipts.size());
        System.out.println("=============================");
    }

    public static String buildReceiptReportHtml(List<Receipt> receipts) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><meta charset=\"UTF-8\"><title>Receipt Report</title></head><body>");
        html.append("<h1>Receipt Report</h1>");

        Money totalSales = Money.toMoney(0.0);
        int totalItems = 0;

        html.append("<table border='1'><tr><th>Kvitto Nr</th><th>Antal varor</th><th>Summa</th></tr>");

        for(int i = 0; i < receipts.size(); i++) {
            Receipt r = receipts.get(i);
            Money receiptTotal = r.calculateTotal();
            totalSales = totalSales.add(receiptTotal);
            totalItems += r.getItems().size();

            html.append("<tr>")
                .append("<td>").append(i+1).append("</td>")
                .append("<td>").append(r.getItems().size()).append("</td>")
                .append("<td>").append(receiptTotal.toString()).append("</td>")
                .append("</tr>");
        }

        html.append("</table>");
        html.append("<h2>Summary</h2>");
        html.append("<p>Number of receipts: ").append(receipts.size()).append("</p>");
        html.append("<p>Total number of items: ").append(totalItems).append("</p>");
        html.append("<p>Total sales: ").append(totalSales.toString()).append("</p>");
        html.append("</body></html>");

        return html.toString();
    }

    public static Money calculateTotalSales(List<Receipt> receipts) {
        Money total = Money.toMoney(0.0);
        for(Receipt r : receipts) {
            total = total.add(r.calculateTotal());
        }
        return total;
    }

    public static int countTotalItems(List<Receipt> receipts) {
        int count = 0;
        for(Receipt r : receipts) {
            count += r.getItems().size();
        }
        return count;
    }

    public static Customer findBestCustomer(List<Receipt> receipts) {
        if(receipts.isEmpty()) {
            return null;
        }

        Customer bestCustomer = receipts.get(0).getCustomer();
        Money highestSpending = Money.toMoney(0.0);

        for(Receipt r : receipts) {
            if(r.getCustomer() != null && r.calculateTotal().getValue().compareTo(highestSpending.getValue()) > 0) {
                highestSpending = r.calculateTotal();
                bestCustomer = r.getCustomer();
            }
        }

        return bestCustomer;
    }

    private static String safe(String value) {
        return Objects.requireNonNullElse(value, "-");
    }
}
