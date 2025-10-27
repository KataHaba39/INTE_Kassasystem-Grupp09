package com.grupp09.kassasystem;

import java.util.Scanner;

public class KassaInterface {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        registerCustomer();
    }

    private static Customer registerCustomer(){
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
}
