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
            new Customer("1234", "Assil Aldabak", "0046701234567", "Assil@gmail.com"),
            new Customer("2345", "Enes Celik", "0046709876543", "Namn@gmail.com")
        );

        Path tempFile = Files.createTempFile("customer-report", ".html");
        Report.generateCustomerReport(customers, tempFile.toString());

        assertTrue(Files.size(tempFile) > 0);
    }

    @Test
    void generateCustomerReport_ShouldHandleIOException() {
        List<Customer> customers = List.of(new Customer("1234", "Anton A", "0046701234567", "Anton@gmail.com"));

        String invalidPath = "Z:/non_existing_folder/report.html";

        assertDoesNotThrow(() -> Report.generateCustomerReport(customers, invalidPath));
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

        assertThrows(IllegalArgumentException.class, () ->
                Report.generateReceiptReport(emptyReceipts, tempFile.toString()), "Tom kvittolista ska inte få generera en kvittorapport");
    }

    @Test
    void generateReceiptReport_ShouldHandleIOExceptionGracefully() {
        List<Receipt> receipts = TestData.exampleReceiptsTwoCustomers();
        String invalidPath = "Z:/invalid_folder/receipt.html";

        assertDoesNotThrow(() -> Report.generateReceiptReport(receipts, invalidPath));
    }

    @Test
    void generateReceiptReport_ShouldThrowOnNullList() {
        assertThrows(IllegalArgumentException.class,
                () -> Report.generateReceiptReport(null, "dummy.html"));
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
    void findBestCustomer_ShouldHandleCustomerWithLowertSpending() {
        Receipt high = TestData.highSpenderReceipt();
        Receipt low = TestData.lowSpenderReceipt();

        List<Receipt> receipts = List.of(high, low);

        Customer best = Report.findBestCustomer(receipts);

        assertEquals(high.getCustomer(), best);
    }

    @Test
    void findBestCustomerReturnsNullForEmptyList() {
        List<Receipt> noReceipts = List.of(); 
        Customer best = Report.findBestCustomer(noReceipts);
        assertNull(best, "Om inga kvtton finns ska best customer vara null");
    }

    @Test
    void findBestCustomer_ShouldReturnFirstWhenAllCustomersNull() {
        Receipt r1 = new Receipt(null);
        Receipt r2 = new Receipt(null);

        List<Receipt> receipts = List.of(r1, r2);

        Customer best = Report.findBestCustomer(receipts);
        assertNull(best);
    }

    @Test
    void findBestCustomer_ShouldSkipNullCustomers() {
        Receipt r1 = new Receipt(null);
        Receipt r2 = TestData.highSpenderReceipt();
        List<Receipt> receipts = List.of(r1, r2);

        Customer best = Report.findBestCustomer(receipts);
        assertEquals(r2.getCustomer(), best);
    }

    @Test
    void buildCustomerReportHtml_ReplacesNullsWithDash() {

        Customer c = new Customer("1234", "Hello Customer", "0046700000000", "ok@ex.com") {
            @Override public String getCustomerId()   { return null; }
            @Override public String getName()         { return null; }
            @Override public String getPhoneNumber()  { return null; }
            @Override public String getEmail()        { return null; }
        };

        String html = Report.buildCustomerReportHtml(List.of(c));

        assertTrue(html.contains("<td>-</td>"));
        long dashes = html.chars()
                .mapToObj(i -> (char)i)
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append)
                .toString()
                .split("<td>-</td>", -1).length - 1;
        assertEquals(4, dashes, "Alla fyra nul fält ska ersättas med '-'");
    }

    @Test
    void safe_ShouldReturnValueWhenNotNull() {
        Customer c = new Customer("1234", "Ass il", "0046701234567", "Assil@gmail.com");
        String html = Report.buildCustomerReportHtml(List.of(c));

        assertTrue(html.contains("Assil"));
        assertTrue(html.contains("0046701234567"));
        assertTrue(html.contains("Assil@gmail.com"));
    }

    @Test
    void printCustomerReport_ShouldNotThrow() {
        List<Customer> customers = List.of(new Customer("1111", " Jenna", "0046701234567", "anna@hotmail.com"));
        assertDoesNotThrow(() -> Report.printCustomerReport(customers));
    }

    @Test
    void printReceiptReport_ShouldNotThrow() {
        List<Receipt> receipts = TestData.exampleReceiptsTwoCustomers();
        assertDoesNotThrow(() -> Report.printReceiptReport(receipts));
    }

    @Test
    void calculateTotalSales_ShouldReturnCorrectSum() {
        List<Receipt> receipts = TestData.exampleReceiptsTwoCustomers();
        Money total = Report.calculateTotalSales(receipts);
        assertEquals(Money.toMoney(35.00), total);
    }

    @Test
    void countTotalItems_ShouldReturnCorrectCount() {
        List<Receipt> receipts = TestData.exampleReceiptsTwoCustomers();
        int totalItems = Report.countTotalItems(receipts);
        assertEquals(2, totalItems);
    }

}

