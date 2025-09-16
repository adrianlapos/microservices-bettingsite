package com.adrianlapos.bets.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Bet {

    private Long matchId;

    @Enumerated(EnumType.STRING)
    private Tip tip;

    public Bet(){}

    public Bet(Long matchId, Tip tip) {
        this.matchId = matchId;
        this.tip = tip;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }
}
