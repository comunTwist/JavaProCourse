package com.gmail.agentup;

public class Order {
    @Id
    private int id;
    private int clientID;
    private int goodID;

    public Order(int id, int clientID, int goodID) {
        this.id = id;
        this.clientID = clientID;
        this.goodID = goodID;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getGoodID() {
        return goodID;
    }

    public void setGoodID(int goodID) {
        this.goodID = goodID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientID=" + clientID +
                ", goodID=" + goodID +
                '}';
    }
}
