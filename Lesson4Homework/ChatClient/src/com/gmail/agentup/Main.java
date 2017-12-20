//1. Добавить ф-ю авторизации пользователей.
//2. Добавить ф-ю приватных сообщений.
//3. Добавить ф-ю получения списка всех пользователей.
//4. Добавить ф-ю чат-комнат.
//5. Добавить ф-ю проверки статуса пользователя.

package com.gmail.agentup;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {

            Service.authorization(scanner);
            //Service.rooms(scanner);
            Service.messages(scanner);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }


    }
}