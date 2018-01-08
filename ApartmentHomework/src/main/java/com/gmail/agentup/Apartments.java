package com.gmail.agentup;

import java.sql.*;

public class Apartments {
    public static void viewApartments(Connection connection, String param, String value) throws SQLException {
        PreparedStatement ps;
        String request = "SELECT * FROM Apartment";

        if (param.equals("all")) {
            ps = connection.prepareStatement(request);
        } else {
            request += " WHERE " + param + " = ?";
            ps = connection.prepareStatement(request);
            ps.setString(1, value);
        }

        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }
}
