package com.groupten.leagueobjectmodel.teamstanding;


public class TeamStanding implements Comparable<TeamStanding>{
    private String teamName;
    private String divisionName;
    private String conferenceName;
    private int wins;
    private int losses;
    private int points;
    private int leagueRank;
    private int conferenceRank;
    private int divisionRank;

    public TeamStanding() {
        this.teamName = "";
        this.divisionName = "";
        this.conferenceName = "";
        this.wins = 0;
        this.losses = 0;
        this.points = 0;
        this.leagueRank = 0;
        this.conferenceRank = 0;
        this.divisionRank = 0;
    }

    public TeamStanding(String teamName,String divisionName,String conferenceName, int points, int leagueRank, int conferenceRank,int divisionRank, int wins, int losses){
        this.teamName = teamName;
        this.divisionName = divisionName;
        this.conferenceName = conferenceName;
        this.wins = wins;
        this.losses = losses;
        this.points = points;
        this.leagueRank = leagueRank;
        this.conferenceRank = conferenceRank;
        this.divisionRank = divisionRank;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
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

    @Override
    public int compareTo(TeamStanding teamStanding) {
        return this.points - teamStanding.getPoints();
    }
}
