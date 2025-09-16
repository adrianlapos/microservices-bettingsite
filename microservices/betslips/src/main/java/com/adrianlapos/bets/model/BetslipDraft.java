package com.adrianlapos.bets.model;

import java.util.ArrayList;
import java.util.List;

public class BetslipDraft {
    private List<Bet> tips = new ArrayList<>();
    private double stake;


    public BetslipDraft(List<Bet> tips, double stake) {
        this.tips = tips;
        this.stake = stake;
    }

    public BetslipDraft() {
        this.tips = new ArrayList<>();
    }

    public List<Bet> getTips() {
        return tips;
    }

    public void setTips(List<Bet> tips) {
        this.tips = tips;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }
}
