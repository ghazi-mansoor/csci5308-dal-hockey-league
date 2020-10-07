package com.groupten.leagueobjectmodel;

import java.util.List;

public class League {
    private int leagueID;
    private String leagueName;
    private List<Conference> divisions;
    private List<Player> freeAgents;
    private IPersistence persistenceAPI;

    public League(String ln) {
        leagueName = ln;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public List<Conference> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Conference> divisions) {
        this.divisions = divisions;
    }

    public List<Player> getFreeAgents() {
        return freeAgents;
    }

    public void setFreeAgents(List<Player> freeAgents) {
        this.freeAgents = freeAgents;
    }

}
