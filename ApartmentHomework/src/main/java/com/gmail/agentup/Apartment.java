package com.gmail.agentup;

public class Apartment {
    private String district;
    private String address;
    private int area;
    private int rooms;
    private int price;

    public Apartment(String district, String address, int area, int rooms, int price) {
        this.district = district;
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.price = price;
    }

    public Apartment() {
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}
