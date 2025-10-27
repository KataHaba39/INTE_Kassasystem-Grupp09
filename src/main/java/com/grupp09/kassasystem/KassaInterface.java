package com.grupp09.kassasystem;

import java.util.Scanner;

public class KassaInterface {
    private static Scanner input = new Scanner(System.in);
    private static Receipt receipt;

    public static void main(String[] args) {
        Customer customer = registerCustomer();
        boolean choice = customerContinuesPurchase();

        if (choice == true){
            receipt = new Receipt(customer);
            handleItems(receipt);
        }

    }

    static Customer registerCustomer(){
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

    static boolean customerContinuesPurchase(){
        System.out.println("1 to continue, 2 to cancel");
        String choice = input.nextLine();

        if(choice.equals("1")){
            return true;
        }
        
        System.out.println("Purchase cancelled");
        return false;
    }

    static boolean handleItems(Receipt receipt){
        String itemChoice;

        do{
            System.out.println("1. Add Milk");
            System.out.println("2. Remove Milk");
            System.out.println("3. Add Banan");
            System.out.println("4. Remove Banan");
            System.out.println("5. Cancel purchase");
            System.out.println("6. Go to payment");

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
                    System.out.println("Continues to payment");
                    break;
                default:
                    break;
            }

            return true;
            
        } while(itemChoice != "5" || itemChoice != "6");
        

    }
}
