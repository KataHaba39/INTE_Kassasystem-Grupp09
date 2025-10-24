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

    private static String safe(String value) {
        return Objects.requireNonNullElse(value, "-");
    }
}
