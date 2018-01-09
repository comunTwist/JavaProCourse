package com.gmail.agentup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Console {

    static Scanner sc = new Scanner(System.in);

    public static void getInit(Connection conn) {
        GoodDAO good = new GoodDAOImpl(conn);
        good.init();
        ClientDAO client = new ClientDAOImpl(conn);
        client.init();
        OrderDAO order = new OrderDAOImpl(conn);
        order.init();
    }

    public static void selectAction(Connection conn) throws SQLException {
        while (true) {
            System.out.println("1: edit goods");
            System.out.println("2: edit clients");
            System.out.println("3: edit orders");
            System.out.print("-> ");
            String se = sc.nextLine();
            switch (se) {
                case "1":
                    editGoods(conn);
                    break;
                case "2":
                    editClients(conn);
                    break;
                case "3":
                    editOrders(conn);
                    break;
                default:
                    return;
            }
        }
    }

    public static void editGoods(Connection conn) throws SQLException {

        try {
            GoodDAO dao = new GoodDAOImpl(conn);

            switch (selectGoods()) {
                case "1":
                    System.out.print("Enter good title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter good price: ");
                    String sPrice = sc.nextLine();
                    int price = Integer.parseInt(sPrice);

                    dao.addGood(title, price);
                    break;
                case "2":
                    List<Good> list = dao.getAll();
                    for (Good good : list) {
                        System.out.println(good);
                    }
                    break;
                case "3":
                    System.out.println(dao.count());
                    break;
                case "4":
                    System.out.print("Enter good id: ");
                    String sId = sc.nextLine();
                    int id = Integer.parseInt(sId);

                    dao.deleteGood(id);
                    break;
                default:
                    return;
            }

        } finally {
            if (conn != null) conn.close();
        }
    }

    public static void editClients(Connection conn) throws SQLException {
        try {
            ClientDAO dao = new ClientDAOImpl(conn);

            switch (selectClients()) {
                case "1":
                    System.out.print("Enter client name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter client age: ");
                    String sAge = sc.nextLine();
                    int age = Integer.parseInt(sAge);

                    dao.addClient(name, age);
                    break;
                case "2":
                    List<Client> list = dao.getAll();
                    for (Client client : list) {
                        System.out.println(client);
                    }
                    break;
                case "3":
                    System.out.println(dao.count());
                    break;
                case "4":
                    System.out.print("Enter client id: ");
                    String sId = sc.nextLine();
                    int id = Integer.parseInt(sId);

                    dao.deleteClient(id);
                    break;
                default:
                    return;
            }

        } finally {
            if (conn != null) conn.close();
        }
    }

    public static void editOrders(Connection conn) throws SQLException {
        try {
            OrderDAO dao = new OrderDAOImpl(conn);

            switch (selectOrders()) {
                case "1":
                    System.out.print("Enter client id: ");
                    String cId = sc.nextLine();
                    int clientID = Integer.parseInt(cId);
                    System.out.print("Enter good id: ");
                    String gId = sc.nextLine();
                    int goodID = Integer.parseInt(gId);

                    dao.makeOrder(clientID, goodID);
                    break;
                case "2":
                    List<Order> list = dao.getAll();
                    for (Order order : list) {
                        System.out.println(order);
                    }
                    break;
                case "3":
                    System.out.println(dao.count());
                    break;
                case "4":
                    System.out.print("Enter order id: ");
                    String sId = sc.nextLine();
                    int id = Integer.parseInt(sId);

                    dao.deleteOrder(id);
                    break;
                default:
                    return;
            }

        } finally {
            if (conn != null) conn.close();
        }
    }

    public static String selectGoods() {

        System.out.println("1: add good");
        System.out.println("2: view good");
        System.out.println("3: view count");
        System.out.println("4: delete good");
        System.out.print("-> ");

        return sc.nextLine();
    }

    public static String selectClients() {

        System.out.println("1: add client");
        System.out.println("2: view clients");
        System.out.println("3: view count");
        System.out.println("4: delete client");
        System.out.print("-> ");

        return sc.nextLine();
    }

    public static String selectOrders() {

        System.out.println("1: make order");
        System.out.println("2: view orders");
        System.out.println("3: view count");
        System.out.println("4: delete orders");
        System.out.print("-> ");

        return sc.nextLine();
    }
}
