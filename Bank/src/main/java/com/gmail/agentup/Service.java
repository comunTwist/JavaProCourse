package com.gmail.agentup;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class Service {
    private EntityManager em;

    public Service(EntityManager em) {
        this.em = em;
    }

    public Service() {
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void createNewUser(String name) {
        this.em.getTransaction().begin();
        try {
            User user = new User(name);
            Account account = new Account(10, 10, 10);
            user.setAccount(account);
            account.setUser(user);

            this.em.persist(user);
            this.em.persist(account);

            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            return;
        }
    }

    public void getUserList() {
        Query query = this.em.createNamedQuery("Users.findAll", User.class);
        List<User> userList = query.getResultList();
        for (User user : userList) {
            double usd = user.getAccount().getUsd();
            double eur = user.getAccount().getEur();
            double uah = user.getAccount().getUah();
            System.out.println("******************** Total money: "
                    + String.format("%.2f", totalUserMoneyUah(usd, eur, uah))
                    + " UAH *********************");
            System.out.println("id\t\t\tUser\t\tUSD\t\t\tEUR\t\t\tUAH");
            System.out.println(user.getId()
                    + "\t\t\t" + user.getName()
                    + "\t\t\t" + String.format("%.2f", usd)
                    + "\t\t\t" + String.format("%.2f", eur)
                    + "\t\t\t" + String.format("%.2f", uah)
            );
            getTransactionList(user);
            System.out.println();
        }
    }

    private User getUserByID(Long id) {
        Query query = em.createNamedQuery("User.findByID", User.class);
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        return user;
    }

    private double totalUserMoneyUah(double usd, double eur, double uah) {
        Query query = this.em.createNamedQuery("Exchange.findByLastDate", Exchange.class);
        Exchange exchange = (Exchange) query.getSingleResult();
        return exchange.getUsd() * usd + exchange.getEur() * eur + exchange.getUah() * uah;
    }

    private void getTransactionList(User user) {
        List<Transaction> transactions = user.getAccount().getTransactions();
        System.out.println("******************************************************************");
        System.out.println("Transactions by " + user.getName() + ":");
        for (Transaction transaction : transactions) {
            //System.out.println(transaction.getId() + " " + transaction.getMessage());
            System.out.println(transaction.getMessage());
        }
    }

    public void createNewTransaction(Long id, String send, double sendSum, String get) {
        try {
            double getSum = createExchange(send, sendSum, get);
            Account account = getUserByID(id).getAccount();

            updateSendCurrency(account, send, sendSum);
            updateGetCurrency(account, get, getSum);

            String message = "Send -> " + String.format("%.2f", sendSum) + " " + send + System.lineSeparator()
                    + "Get  <- " + String.format("%.2f", getSum) + " " + get;
            Transaction transaction = new Transaction(message);
            account.addTransaction(transaction);
        } catch (NoResultException ex) {
            System.out.println("Exchange not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique exchange found!");
            return;
        }
    }

    public void getExchangeList() {
        Query query = this.em.createNamedQuery("Exchanges.findAll", Exchange.class);
        List<Exchange> exchangeList = query.getResultList();
        System.out.println();
        System.out.println("*************************** Exchanges ****************************");
        for (Exchange exchange : exchangeList) {
            System.out.println(exchange.getId()
                    + " [" + exchange.getDate()
                    + "]: USD " + exchange.getUsd()
                    + " | EUR " + exchange.getEur()
                    + " | UAH " + exchange.getUah());
        }
        System.out.println();
    }

    public void createNewExchange(double usd, double eur, double uah) {
        this.em.getTransaction().begin();
        try {
            Exchange exchange = new Exchange(usd, eur, uah, new Date());
            this.em.persist(exchange);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            return;
        }
    }

    private double createExchange(String send, double sum, String get) {
        Query query = this.em.createNamedQuery("Exchange.findByLastDate", Exchange.class);
        //query.setFirstResult(1);
        Exchange exchange = (Exchange) query.getSingleResult();
        return selectCurrency(exchange, send) / selectCurrency(exchange, get) * sum;
    }

    public void updateAccountMoney(Long id, double usd, double eur, double uah) {
        try {
            updateAccount(getUserByID(id).getAccount(), usd, eur, uah);
        } catch (NoResultException ex) {
            System.out.println("User not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique user found!");
            return;
        }
    }

    private void updateAccount(Account account, double usd, double eur, double uah) {
        this.em.getTransaction().begin();
        try {
            account.setUsd(usd);
            account.setEur(eur);
            account.setUah(uah);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            return;
        }
    }

    private double selectCurrency(Exchange exchange, String currency) {
        double result = 0;
        switch (currency) {
            case "usd":
                result = exchange.getUsd();
                break;
            case "eur":
                result = exchange.getEur();
                break;
            case "uah":
                result = exchange.getUah();
                break;
            default:
                break;
        }
        return result;
    }

    private void updateSendCurrency(Account account, String currency, double sum) {
        double result;
        switch (currency) {
            case "usd":
                result = account.getUsd() - sum;
                account.setUsd(result);
                break;
            case "eur":
                result = account.getEur() - sum;
                account.setEur(result);
                break;
            case "uah":
                result = account.getUah() - sum;
                account.setUah(result);
                break;
            default:
                return;
        }
    }

    private void updateGetCurrency(Account account, String currency, double sum) {
        double result;
        switch (currency) {
            case "usd":
                result = account.getUsd() + sum;
                account.setUsd(result);
                break;
            case "eur":
                result = account.getEur() + sum;
                account.setEur(result);
                break;
            case "uah":
                result = account.getUah() + sum;
                account.setUah(result);
                break;
            default:
                return;
        }
    }

}
