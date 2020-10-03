package com.groupten.leagueobjectmodel;

import java.util.ArrayList;

public class Division {
    private int leagueID;
    private int conferenceID;
    private int divisionID;
    private String divisionName;
    private ArrayList<Team> teams;

    public Division(int leagueID, int conferenceID, int divisionID, String divisionName) {
        this.leagueID = leagueID;
        this.conferenceID = conferenceID;
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.teams = new ArrayList<Team>();
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}
