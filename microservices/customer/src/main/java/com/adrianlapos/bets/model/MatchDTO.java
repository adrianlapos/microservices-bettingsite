package com.adrianlapos.bets.model;

import com.adrianlapos.bets.entity.Match;

import java.sql.Timestamp;

public class MatchDTO {
    private Long team1Id;
    private Long team2Id;
   // private EnumMap<Tip,Integer> odds = new EnumMap<>(Tip.class);
    private double home_odd;
    private double draw_odd;
    private double away_odd;
    private Timestamp time;
    private Sport sport;
    private int homeGoals;
    private int awayGoals;
    public MatchDTO(){};
    public MatchDTO(Long team1Id, Long team2Id, double home_odd, double draw_odd, double away_odd, Timestamp time, Sport sport, int homeGoals, int awayGoals) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.home_odd = home_odd;
        this.draw_odd = draw_odd;
        this.away_odd = away_odd;
        this.time = time;
        this.sport = sport;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }
    public MatchDTO(Long team1Id, Long team2Id, double home_odd, double draw_odd, double away_odd, Timestamp time, Sport sport) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.home_odd = home_odd;
        this.draw_odd = draw_odd;
        this.away_odd = away_odd;
        this.time = time;
        this.sport = sport;
        this.homeGoals = 0;
        this.awayGoals = 0;
    }

    public Long getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Long team1Id) {
        this.team1Id = team1Id;
    }

    public Long getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Long team2Id) {
        this.team2Id = team2Id;
    }

    public double getHome_odd() {
        return home_odd;
    }

    public void setHome_odd(double home_odd) {
        this.home_odd = home_odd;
    }

    public double getDraw_odd() {
        return draw_odd;
    }

    public void setDraw_odd(double draw_odd) {
        this.draw_odd = draw_odd;
    }

    public double getAway_odd() {
        return away_odd;
    }

    public void setAway_odd(double away_odd) {
        this.away_odd = away_odd;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
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

    public static class MatchMapper{
        public static MatchDTO mapToDto(Match match){
            return new MatchDTO(match.getTeam1(),match.getTeam2(),match.getOdds().get(Tip.HOME),
                    match.getOdds().get(Tip.DRAW),match.getOdds().get(Tip.AWAY),
            match.getTime(),match.getSport(),match.getHomeGoals(),match.getAwayGoals());
        }
    }
}
