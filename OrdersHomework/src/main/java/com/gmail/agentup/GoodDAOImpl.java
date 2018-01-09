package com.gmail.agentup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodDAOImpl implements GoodDAO {
    private final Connection conn;

    public GoodDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void init() {
        try {
            Statement st = conn.createStatement();
            try {
                st.execute("DROP TABLE IF EXISTS Goods");
                st.execute("CREATE TABLE Goods (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, title VARCHAR(20) NOT NULL, price INT)");
            } finally {
                st.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addGood(String title, int price) {
        try {
            try (PreparedStatement st = conn
                    .prepareStatement("INSERT INTO Goods (title, price) VALUES(?, ?)")) {
                st.setString(1, title);
                st.setInt(2, price);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteGood(int id) {
        try {
            try (PreparedStatement st = conn.prepareStatement("DELETE FROM Good WHERE id=?")) {
                st.setInt(1, id);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Good> getAll() {
        List<Good> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM Goods")) {
                    while (rs.next()) {
                        Good good = new Good();

                        good.setId(rs.getInt(1));
                        good.setTitle(rs.getString(2));
                        good.setPrice(rs.getInt(3));

                        res.add(good);
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
                try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Goods")) {
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
