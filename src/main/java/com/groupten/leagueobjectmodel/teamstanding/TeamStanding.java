package com.groupten.leagueobjectmodel.teamstanding;


public class TeamStanding implements Comparable<TeamStanding> {
    private String teamName;
    private int points;
    private int leagueRank;
    private int conferenceRank;
    private int divisionRank;
    private int wins;
    private int losses;

    public TeamStanding() {
        this.teamName = "";
        this.points = 0;
        this.leagueRank = 0;
        this.conferenceRank = 0;
        this.divisionRank = 0;
        this.wins = 0;
        this.losses = 0;
    }

    public TeamStanding(String teamName, int points, int leagueRank, int conferenceRank,int divisionRank, int wins, int losses){
        this.teamName = teamName;
        this.points = points;
        this.leagueRank = leagueRank;
        this.conferenceRank = conferenceRank;
        this.divisionRank = divisionRank;
        this.wins = wins;
        this.losses = losses;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLeagueRank() {
        return leagueRank;
    }

    public void setLeagueRank(int leagueRank) {
        this.leagueRank = leagueRank;
    }

    public int getConferenceRank() {
        return conferenceRank;
    }

    public void setConferenceRank(int conferenceRank) {
        this.conferenceRank = conferenceRank;
    }

    public int getDivisionRank() {
        return divisionRank;
    }

    public void setDivisionRank(int divisionRank) {
        this.divisionRank = divisionRank;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void addWin(){
        this.wins = this.wins + 1;
        this.points = this.points + 2;
    }

    public void addLoss(){
        this.losses = this.losses + 1;
    }

    @Override
    public int compareTo(TeamStanding teamStanding) {
        return (int) (this.points - teamStanding.getPoints());
    }
}
