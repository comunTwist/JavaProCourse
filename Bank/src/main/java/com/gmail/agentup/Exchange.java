package com.gmail.agentup;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Exchanges.findAll", query = "SELECT c FROM Exchange c"),
        @NamedQuery(name = "Exchange.findByLastDate", query = "SELECT c FROM Exchange c ORDER BY c.date DESC")
})
public class Exchange {
    @Id
    @GeneratedValue
    private Long id;
    private double usd;
    private double eur;
    private double uah;
    private Date date;

    public Exchange(double usd, double eur, double uah, Date date) {
        this.usd = usd;
        this.eur = eur;
        this.uah = uah;
        this.date = date;
    }

    public Exchange() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public double getUah() {
        return uah;
    }

    public void setUah(double uah) {
        this.uah = uah;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
