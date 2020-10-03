package com.groupten.leagueobjectmodel;

import java.util.ArrayList;

public class Conference {
    private int leagueID;
    private int conferenceID;
    private String conferenceName;
    private ArrayList<Division> divisions;

    public Conference(int leagueID, int conferenceID, String conferenceName) {
        this.leagueID = leagueID;
        this.conferenceID = conferenceID;
        this.conferenceName = conferenceName;
        this.divisions = new ArrayList<Division>();
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public int getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(int conferenceID) {
        this.conferenceID = conferenceID;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public void addDivision(Division division) {
        divisions.add(division);
    }

    public ArrayList<Division> getDivisions() {
        return divisions;
    }
}
