package com.groupten.leagueobjectmodel;

import java.util.ArrayList;

public class League {
    private int leagueID;
    private String leagueName;
    private ArrayList<Conference> conferences;
    private ArrayList<Player> freeAgents;

    public League(int leagueID, String leagueName) {
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.conferences = new ArrayList<Conference>();
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

    public void addConference(Conference conference) {
        conferences.add(conference);
    }

    public ArrayList<Conference> getConferences() {
        return conferences;
    }
}
