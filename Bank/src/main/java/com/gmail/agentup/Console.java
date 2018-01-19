package com.gmail.agentup;

import java.util.Scanner;

public class Console {
    private Service service;
    private Scanner scanner;

    public Console(Service service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public Console() {
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void user() {
        System.out.println("Select action:");
        System.out.println("1: create user");
        System.out.println("2: set user money");
        System.out.println("3: create transaction");
        System.out.println("4: add exchange");
        System.out.print("-> ");
        userAction(Integer.parseInt(scanner.nextLine()));
    }

    private void userAction(int action) {
        switch (action) {
            case 1:
                System.out.println("Input user name:");
                service.createNewUser(scanner.nextLine());
                break;
            case 2:
                System.out.println("Input user id:");
                userMoney(Long.parseLong(scanner.nextLine()));
                break;
            case 3:
                System.out.println("Input user id:");
                userTransactions(Long.parseLong(scanner.nextLine()));
                break;
            case 4:
                addExchange();
                break;
            default:
                return;
        }
    }

    private void userMoney(long id) {
        System.out.println("Input USD sum:");
        double usd = Double.parseDouble(scanner.nextLine());
        System.out.println("Input EUR sum:");
        double eur = Double.parseDouble(scanner.nextLine());
        System.out.println("Input UAH sum:");
        double uah = Double.parseDouble(scanner.nextLine());
        service.updateAccountMoney(id, usd, eur, uah);
    }

    private void userTransactions(long id) {
        System.out.println("Input send currency (usd/eur/uah):");
        String send = scanner.nextLine();
        System.out.println("Input sum:");
        double sum = Double.parseDouble(scanner.nextLine());
        System.out.println("Input get currency (usd/eur/uah):");
        String get = scanner.nextLine();
        service.createNewTransaction(id, send, sum, get);
    }

    private void addExchange() {
        System.out.println("Input USD rate:");
        double usd = Double.parseDouble(scanner.nextLine());
        System.out.println("Input EUR rate:");
        double eur = Double.parseDouble(scanner.nextLine());
        System.out.println("Input UAH rate:");
        double uah = Double.parseDouble(scanner.nextLine());
        service.createNewExchange(usd, eur, uah);
    }
}
