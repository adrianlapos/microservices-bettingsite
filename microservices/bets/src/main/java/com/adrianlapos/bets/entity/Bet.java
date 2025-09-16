package com.adrianlapos.bets.entity;

import com.adrianlapos.bets.model.Tip;

import jakarta.persistence.*;

@Entity
public class Bet {
    @Id
    @GeneratedValue
    private Long id;

    private Long matchId;
    private Tip tip;

    private Long betslipId;

    public Bet(){}

    public Bet(Long match, Tip tip) {
        this.matchId = match;
        this.tip = tip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long match) {
        this.matchId = match;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Long getBetslipId() {
        return betslipId;
    }

    public void setBetslipId(Long betslip) {
        this.betslipId = betslip;
    }
}