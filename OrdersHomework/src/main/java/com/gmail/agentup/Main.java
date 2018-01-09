//Создать проект «База данных заказов». Создать
//таблицы «Товары» , «Клиенты» и «Заказы».
//Написать код для добавления новых клиентов,
//товаров и оформления заказов.

package com.gmail.agentup;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Console.getInit(factory.getConnection());
        //Console.selectAction(factory.getConnection());
        while (true) {
            Console.editGoods(factory.getConnection());
            Console.editClients(factory.getConnection());
            Console.editOrders(factory.getConnection());
        }

    }
}
