package com.gmail.agentup;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "Users.findAll", query = "SELECT c FROM User c"),
        @NamedQuery(name = "User.findByID", query = "SELECT c FROM User c WHERE c.id = :id")
})
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
//    private String login;
//    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
