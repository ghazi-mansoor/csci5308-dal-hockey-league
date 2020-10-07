package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.conference.ConferenceInterface;

import java.util.ArrayList;
import java.util.List;

public class Conference {
    private int leagueID;
    private int conferenceID;
    private String conferenceName;
    private List<Division> divisions;
    private ConferenceInterface conferencePersistenceAPI;

    public Conference(String cn) {
        conferenceName = cn;
        divisions = new ArrayList<Division>();
    }

    public Conference(String cn, ConferenceInterface per) {
        conferenceName = cn;
        divisions = new ArrayList<Division>();
        conferencePersistenceAPI = per;
    }

    public void addDivisionToConference(Division division) {
        divisions.add(division);
    }

    public void saveConferenceToDB() {
        conferenceID = conferencePersistenceAPI.createConference(leagueID, conferenceName);
        setDivisionForeignKeys();
        saveAllDivisions();
        System.out.println("Conference saved to DB");
    }

    private void setDivisionForeignKeys() {
        for (Division division : divisions) {
            division.setConferenceID(conferenceID);
        }
    }

    private void saveAllDivisions() {
        for (Division division : divisions) {
            division.saveDivisionToDB();
        }
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

}
