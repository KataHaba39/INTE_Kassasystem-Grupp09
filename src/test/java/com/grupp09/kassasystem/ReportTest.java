package com.grupp09.kassasystem;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
