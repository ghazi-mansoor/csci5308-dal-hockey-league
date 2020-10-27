package com.groupten.leagueobjectmodel.teamstanding;


public class TeamStanding implements Comparable<TeamStanding> {
    private String teamName;
    private int points;
    private int leagueRank;
    private int conferenceRank;
    private int divisionRank;

    public TeamStanding() {
        this.teamName = "";
        this.points = 0;
        this.leagueRank = 0;
        this.conferenceRank = 0;
        this.divisionRank = 0;
    }

    public TeamStanding(String teamName, int points, int leagueRank, int conferenceRank,int divisionRank){
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
        return (int) (this.points - teamStanding.getPoints());
    }
}
