package com.gmail.agentup;

import java.io.IOException;
import java.util.Scanner;

public class Service {

    private static String login;
    private static String pass;
    private static String room = "global";
    private static boolean connect = true;

    public static void setConnect(boolean connect) {
        Service.connect = connect;
    }

    public static void authorization(Scanner scanner) throws IOException {

        System.out.println("Enter your/new login: ");
        login = scanner.nextLine();
        System.out.println("Enter password: ");
        pass = scanner.nextLine();

        Thread thread = new Thread(new GetUser(login, pass, "online"));
        thread.setDaemon(true);
        thread.start();

        User user = new User(login, pass, "online");
        int result = user.send("http://127.0.0.1:8080/user");

        if (result != 200) {
            System.out.println("HTTP error occured: " + result);
            return;
        }
    }
    public static void rooms(Scanner scanner) throws IOException {
        Thread thread = new Thread(new GetUser(login, pass, "online"));
        thread.setDaemon(true);
        thread.start();

        System.out.println("Choose room: ");
        room = scanner.nextLine();
    }
    public static void messages(Scanner scanner) throws IOException {

        Thread thread = new Thread(new GetMessage(login, room));
        thread.setDaemon(true);
        thread.start();
        if (connect) {
            System.out.println();
            System.out.println("Command: \"@getusers\" \"@username\" \"@logout\"");
            System.out.println();
            System.out.println("Enter your message: ");

            while (connect) {
                String text = scanner.nextLine();
                if (text.isEmpty()) text = "@logout";

                Message message = new Message(login, text);
                String to = "" + text.toCharArray()[0];
                if (to.equals("@")) {
                    to = text.split("[@ ]")[1];
                    message.setTo(to);
                }

                int res = message.send("http://127.0.0.1:8080/add");
                if (res != 200) {
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
                if (text.equals("@logout")) connect = false;
            }
        }
    }
}
