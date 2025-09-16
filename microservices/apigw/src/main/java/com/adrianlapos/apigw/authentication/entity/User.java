//package com.adrianlapos.apigw.authentication.entity;
//
//import com.adrianlapos.apigw.authentication.model.Role;
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "app_user")
//public class User {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private Long id;
//    private String username;
//    private String email;
//    private String password;
//    private Role role;
//    private double balance;
//    @ElementCollection
//    private List<Long> betslips;
//
//    public User(String username, String email, String password, double balance,Role role, List<Long> betslips) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.balance = balance;
//        this.role = role;
//        this.betslips = betslips;
//    }
//
//    public User(){}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
//
//    public List<Long> getBetslips() {
//        return betslips;
//    }
//
//    public void setBetslips(List<Long> betslips) {
//        this.betslips = betslips;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//}
