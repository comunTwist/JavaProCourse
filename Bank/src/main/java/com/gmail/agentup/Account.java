package com.gmail.agentup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Accounts")
@NamedQueries({
        @NamedQuery(name = "Account.updateByID", query = "UPDATE Account c SET c.usd = :usd, c.eur = :eur, c.uah = :uah WHERE c.id = :id"),
        @NamedQuery(name = "Account.findByID", query = "SELECT c FROM Account c WHERE c.id = :id")
})
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private double usd;
    private double eur;
    private double uah;

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(double usd, double eur, double uah) {
        this.usd = usd;
        this.eur = eur;
        this.uah = uah;
    }

    public Account() {
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
