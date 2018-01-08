//Спроектировать базу «Квартиры». Каждая запись
//в базе содержит данные о квартире (район,
//адрес, площадь, кол. комнат, цена). Сделать
//возможность выборки квартир из списка по
//параметрам.

package com.gmail.agentup;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Apartments.viewApartments(factory.getConnection(), Console.setParam(), Console.setValue());

    }
}
