package com.gmail.agentup;

import java.util.List;

public interface OrderDAO {
    void init();
    void makeOrder(int clientID, int goodID);
    void deleteOrder(int id);
    List<Order> getAll();
    long count();
}
