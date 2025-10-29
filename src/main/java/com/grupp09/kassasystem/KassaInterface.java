package com.grupp09.kassasystem;

import java.math.BigDecimal;
import java.util.Scanner;

public class KassaInterface {
    private static Receipt receipt;

    public static void main(String[] args) {
        Customer customer = registerCustomer();
        boolean choice = customerContinuesPurchase();
        boolean continueToPayment = false;

        if (choice == true) {
            receipt = new Receipt(customer);
            continueToPayment = handleItems(receipt);
        }

        if (continueToPayment) {
            handlePayment(receipt);
        }
    }

    static Customer registerCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ID");
        String id = input.nextLine();
        System.out.println("Enter name");
        String name = input.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = input.nextLine();
        System.out.println("Enter mail");
        String mail = input.nextLine();
        Customer customer = new Customer(id, name, phoneNumber, mail);
        System.out.println("Customer registered");

        return customer;
    }

    static boolean customerContinuesPurchase() {
        Scanner input = new Scanner(System.in);
        System.out.println("1 to continue, 2 to cancel");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            return true;
        }

        System.out.println("Purchase cancelled");
        return false;
    }

    static boolean handleItems(Receipt receipt) {
        Scanner input = new Scanner(System.in);
        String itemChoice;

        do {
            printItemMenu();

            itemChoice = input.nextLine();

            switch (itemChoice) {
                case "1":
                    receipt.addItem(new FixedPriceItem("Milk", Money.toMoney(12.0d), ItemGroups.MEJERI), 1, null);
                    System.out.println("Product was added");
                    break;
                case "2":
                    receipt.removeItem(new FixedPriceItem("Milk", Money.toMoney(12.0d), ItemGroups.MEJERI), 1, null);
                    System.out.println("Product was removed");
                    break;
                case "5":
                    System.out.println("Cancelled purchase");
                    return false;
                case "6":
                    if (receipt.getItems().isEmpty()) {
                        System.out.println("Error: Receipt must contain at least one items to continue");
                        return false;
                    }
                    System.out.println("Continues to payment");
                    return true;
                default:
                    break;
            }
        } while (!itemChoice.equals("5") && !itemChoice.equals("6"));

        return false;
    }

    private static void printItemMenu(){
        System.out.println("1. Add Milk");
        System.out.println("2. Remove Milk");
        System.out.println("3. Add Banana");
        System.out.println("4. Remove Banana");
        System.out.println("5. Cancel purchase");
        System.out.println("6. Go to payment");
    }

    static boolean handlePayment(Receipt receipt) {
        Scanner input = new Scanner(System.in);
        String paymentChoice;

        do {
            printPaymentMenu();

            paymentChoice = input.nextLine();

            switch (paymentChoice) {
                case "1":
                    return handleCashPayment(receipt, input);
                case "2":
                    return handleCardOrSwishPayment(receipt, input);
                case "3":
                    System.out.println("Purchase cancelled");
                    return false;
                default:
                    System.out.println("Choose valid payment option");
            }
        } while (true);

    }

    private static void printPaymentMenu(){
        System.out.println("Choose payment option");
        System.out.println("1. Cash");
        System.out.println("2. Card or Swish");
        System.out.println("3. Purchase was cancelled");
    }

    static boolean handleCashPayment(Receipt receipt, Scanner input) {
        Money total = receipt.calculateTotal();
        System.out.println("Total amount to pay: " + total);
        System.out.print("Enter amount paid: ");
        BigDecimal paidValue = new BigDecimal(input.nextLine());
        Money paid = Money.toMoney(paidValue);

        PaymentMethod payment = new PaymentMethod("cash", total, paid);
        if (!payment.isPaidEnough()) {
            System.out.println("Payment failed. Amount entered was not enough");
            return false;

        } else if (paid.compareTo(total) > 0) {
            Money change = paid.subtract(total);
            System.out.println("Payment was successful! Change: " + change);
            receipt.printReceipt();
            return true;

        } else {
            System.out.println("Payment was successful!");
            receipt.printReceipt();
            return true;
        }
    }

    private static double random() {
        return Math.random();
    }

    static boolean handleCardOrSwishPayment(Receipt receipt, Scanner input) {
        System.out.println("Processing payment");
        boolean success = random() < 0.9;

        if (success) {
            PaymentMethod payment = new PaymentMethod("card", receipt.calculateTotal(), receipt.calculateTotal());
            System.out.println("Payment was succesful!");
            System.out.println(payment);
            receipt.printReceipt();
            return true;

        } else {
            System.out.println("Payment failed! Try again (Y/N)?");
            String retry = input.nextLine();
            if (retry.equalsIgnoreCase("Y")) {
                return handleCardOrSwishPayment(receipt, input);
            } else {
                System.out.println("Transaction cancelled.");
                return false;
            }
        }
    }
}
