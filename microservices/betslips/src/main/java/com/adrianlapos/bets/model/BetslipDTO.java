package com.adrianlapos.bets.model;

import com.adrianlapos.bets.entity.Betslip;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class BetslipDTO extends BetslipDraft{
//    private double stake;
//    private List<Bet> bets;
    private Date createdAt;
    private State state;
    private Long userId;

    public BetslipDTO(double stake, List<Bet> bets, Date createdAt, Long userId, State state) {
        super(bets,stake);
        this.createdAt = createdAt;
        this.userId = userId;
        this.state = state;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public  class BetslipDTOMapper{
        public static BetslipDTO mapToDTO(Betslip betslip){
            return new BetslipDTO(betslip.getStake(),betslip.getBets(),betslip.getCreatedAt(),betslip.getUserId(),betslip.getState());
        }
    }
}
