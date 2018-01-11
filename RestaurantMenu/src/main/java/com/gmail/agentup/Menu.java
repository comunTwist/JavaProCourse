
package com.gmail.agentup;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String title;
    private double price;
    private int weight;
    private boolean sale;

    public Menu(String title, double price, int weight, boolean sale) {
        this.title = title;
        this.price = price;
        this.weight = weight;
        this.sale = sale;
    }

    public Menu() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", sale=" + sale +
                '}';
    }
}
