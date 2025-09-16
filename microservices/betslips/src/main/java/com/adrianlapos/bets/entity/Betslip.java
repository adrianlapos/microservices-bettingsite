package com.adrianlapos.bets.entity;

import com.adrianlapos.bets.model.Bet;
import com.adrianlapos.bets.model.State;
import com.adrianlapos.bets.model.Tip;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class Betslip {
    @Id
    @GeneratedValue
    private Long id;
    private double stake;
    //@OneToMany(mappedBy = "betslip" , cascade = CascadeType.ALL , orphanRemoval = true)
//    @ElementCollection
//    @CollectionTable(name = "bets", joinColumns = @JoinColumn(name = "match_id"))
//    @MapKeyColumn(name = "match_id") // the Long key
//    @Enumerated(EnumType.STRING)   // how to persist Tip
//    @Column(name = "tip")          // column for the enum value
//    private Map<Long, Tip> bets;

    @ElementCollection
    @CollectionTable(name = "bets", joinColumns = @JoinColumn(name = "betslip_id"))
    private List<Bet> bets = new ArrayList<>();
    private Long userId;
    private Date createdAt;
    private State state;
    public Betslip(){
        this.createdAt = new Date();
    }

    public Betslip(double stake,List<Bet> bets,Long userId) {
        this.stake = stake;
        this.bets = bets;
        this.userId = userId;
        this.createdAt = new Date();
        this.state = State.PRIDANY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public Long getUser() {
        return userId;
    }

    public void setUser(Long user) {
        this.userId = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}