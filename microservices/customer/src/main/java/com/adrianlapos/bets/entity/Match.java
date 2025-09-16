package com.adrianlapos.bets.entity;

import com.adrianlapos.bets.model.Sport;
import com.adrianlapos.bets.model.Tip;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.EnumMap;
import java.util.Map;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue
    private Long id;

    private Long team1Id;

    private Long team2Id;
    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Tip,Double> odds = new EnumMap<>(Tip.class);
    private Timestamp time;
    private Sport sport;
   // @OneToOne
   // private Result result = new Result(0,0);
    private int homeGoals;
    private int awayGoals;
    public Match(){}

    public Match(Long team1, Long team2, Double home, Double draw, Double away, Sport sport, Timestamp timestamp) {
        this.team1Id = team1;
        this.team2Id = team2;
        this.odds.put(Tip.HOME,home);
        this.odds.put(Tip.DRAW,draw);
        this.odds.put(Tip.AWAY,away);
        //this.result = new Result(0,0);
        this.homeGoals = 0;
        this.awayGoals = 0;
        this.sport = sport;
        this.time = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeam1() {
        return team1Id;
    }

    public void setTeam1(Long team1) {
        this.team1Id = team1;
    }

    public Long getTeam2() {
        return team2Id;
    }

    public void setTeam2(Long team2) {
        this.team2Id = team2;
    }

    public Map<Tip, Double> getOdds() {
        return odds;
    }

    public void setOdds(EnumMap<Tip, Double> odds) {
        this.odds = odds;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

//    public Result getResult() {
//        return result;
//    }
//
//    public void setResult(Result result) {
//        this.result = result;
//    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setOdds(Map<Tip, Double> odds) {
        this.odds = odds;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }
}
