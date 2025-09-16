package com.adrianlapos.bets.entity;

import javax.persistence.*;
import com.adrianlapos.bets.model.Role;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private double balance;
    //@OneToMany(mappedBy = "user" , orphanRemoval = true, cascade= CascadeType.ALL)
    @ElementCollection
    private List<Long> betslips;
    private Role role;

    public User(){}

    public User(String username, String email,String password, int balance,Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.betslips = new ArrayList<>();
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Long> getBetslips() {
        return betslips;
    }

    public void setBetslips(List<Long> history) {
        this.betslips = history;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
