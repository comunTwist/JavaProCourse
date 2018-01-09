package com.gmail.agentup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    private String DB_USER = "root";
    private String DB_PASSWORD = "123db";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
