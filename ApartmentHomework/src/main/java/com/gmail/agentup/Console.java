package com.gmail.agentup;

import java.util.Scanner;

public class Console {
    static String[] params = new String[]{"all", "district", "address", "area", "rooms", "price"};
    // static Scanner sc = new Scanner(System.in);

    public static String setParam() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select parameter:");
        System.out.println("0: all");
        System.out.println("1: district");
        System.out.println("2: address");
        System.out.println("3: area");
        System.out.println("4: number of rooms");
        System.out.println("5: price");
        System.out.print("-> ");
        return params[sc.nextInt()];

    }

    public static String setValue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value: ");

        return sc.nextLine();
    }
}
