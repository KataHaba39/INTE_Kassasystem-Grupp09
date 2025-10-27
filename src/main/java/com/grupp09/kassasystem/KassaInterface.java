package com.grupp09.kassasystem;

public class KassaInterface {
    public static final String NAME = "Name: ";
    
    static String readName() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        return NAME.concat(name);
    }

    public static void main(String[] args) {
        String result = readName();
        System.out.println(result);
    }
}
