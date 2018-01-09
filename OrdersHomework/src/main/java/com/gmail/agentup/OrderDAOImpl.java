package com.gmail.agentup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final Connection conn;

    public OrderDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void init() {
        try {
            Statement st = conn.createStatement();
            try {
                st.execute("DROP TABLE IF EXISTS Orders");
                st.execute("CREATE TABLE Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, clientID INT NOT NULL, goodID INT NOT NULL)");
            } finally {
                st.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void makeOrder(int clientID, int goodID) {
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO Orders (clientID, goodID) VALUES(?, ?)")) {
                st.setInt(1, clientID);
                st.setInt(2, goodID);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            try (PreparedStatement st = conn.prepareStatement("DELETE FROM Orders WHERE id=?")) {
                st.setInt(1, id);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM Orders")) {
                    while (rs.next()) {
                        Order order = new Order();

                        order.setId(rs.getInt(1));
                        order.setClientID(rs.getInt(2));
                        order.setGoodID(rs.getInt(3));

                        res.add(order);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public long count() {
        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Orders")) {
                    if (rs.next())
                        return rs.getLong(1);
                    else
                        throw new RuntimeException("Count failed");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
