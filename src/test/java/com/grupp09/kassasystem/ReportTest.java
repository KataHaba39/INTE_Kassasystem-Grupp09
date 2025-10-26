package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    
    @Test
    void generateCustomerReportCreatesFile() throws IOException {
        List<Customer> customers = List.of(
            new Customer("C001", "Assil", "0701234567", "Assil@gmail.com"),
            new Customer("C002", "Namn", "0709876543", "Namn@gmail.com")
        );

        Path tempFile = Files.createTempFile("customer-report", ".html");
        Report.generateCustomerReport(customers, tempFile.toString());

        assertTrue(Files.size(tempFile) > 0);
    }

    @Test 
    void generateReceiptReportCreatesFileWithSummaryAndTotals() throws Exception {
        List<Receipt> receipts = TestData.exampleReceiptsTwoCustomers();

        Path tempFile = Files.createTempFile("receipt-report", ".html");

        Report.generateReceiptReport(receipts, tempFile.toString());

        String html = Files.readString(tempFile);

        assertTrue(html.contains("Receipt Report"), "Rubriken 'Receipt report' saknas");
        assertTrue(html.contains("Summary"), "Sammanfattningsdelen ('Summary') saknas");

        assertTrue(html.contains("Number of receipts:"), "Antal kvitton saknas i rapportens summary");
        assertTrue(html.contains("Total sales:"), "Total sales saknas i rapportens summary");
        
        assertTrue(html.contains("35.00"), "Totalförsäljningen 35.00 borde stå i rapporten");

    }

    @Test

    void generateReceiptReportShouldThrowOnEmptyReceiptList() throws Exception {
        List<Receipt> emptyReceipts = List.of();
        Path tempFile = Files.createTempFile("empty-report", ".html");

        assertThrows(IllegalArgumentException.class, () -> { 
            Report.generateReceiptReport(emptyReceipts, tempFile.toString()); 
        }, "Tom kvittolista ska inte få generera en kvittorapport");
    }

    @Test 
    void findBestCustomerReturnsCustomerWithHighestSpending() {
        Receipt low = TestData.lowSpenderReceipt();
        Receipt high = TestData.highSpenderReceipt();

        List<Receipt> receipts = List.of(low, high);

        Customer best = Report.findBestCustomer(receipts);

        assertEquals( high.getCustomer(), best, "Kunden som spenderat mest totalt ska bli best customer");
    
    }

    @Test
    void findBestCustomerReturnsNullForEmptyList() {
        List<Receipt> noReceipts = List.of(); 
        Customer best = Report.findBestCustomer(noReceipts);
        assertNull(best, "Om inga kvtton finns ska best customer vara null");
    }

    @Test 
    void buildCustomerReportHtmlReplacesNullFieldsWithDash() {
        Customer c = new Customer("C999", "Okänd");

        String html = Report.buildCustomerReportHtml(List.of(c));

        assertTrue(html.contains("<td>C999</td>"), "ID ska finnas i HTML");
        assertTrue(html.contains("<td>Okänd</td>"), "Namn ska finnas i HTML");

        assertTrue(html.contains("<td>-</td>"), "Null phone ska bli '-'");
        assertTrue(html.contains("<td>-</td>"), "Null email ska bli '-'");
    }

}
