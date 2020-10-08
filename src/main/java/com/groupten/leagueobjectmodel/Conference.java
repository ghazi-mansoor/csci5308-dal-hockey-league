package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.conference.ConferenceInterface;

import java.util.HashMap;
import java.util.Map;

public class Conference {
    private int leagueID;
    private int conferenceID;
    private String conferenceName;
    private Map<String, Division> divisions;
    private ConferenceInterface conferencePersistenceAPI;

    public Conference(String cn) {
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
    }

    public Conference(String cn, ConferenceInterface per) {
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
        conferencePersistenceAPI = per;
    }

    public boolean addDivisionToConference(Division division) {
        String divisionName = division.getDivisionName();
        if (Validator.areStringsValid(divisionName)) {
            divisions.put(division.getDivisionName(), division);
            return divisions.containsKey(division.getDivisionName());
        } else {
            return false;
        }
    }

    public void saveConferenceToDB() {
        conferenceID = conferencePersistenceAPI.createConference(leagueID, conferenceName);
        setDivisionForeignKeys();
        saveAllDivisions();
        System.out.println("Conference saved to DB");
    }

    private void setDivisionForeignKeys() {
        for (Division division : divisions.values()) {
            division.setConferenceID(conferenceID);
            division.setLeagueID(leagueID);
        }
    }

    private void saveAllDivisions() {
        for (Division division : divisions.values()) {
            division.saveDivisionToDB();
        }
    }

    public boolean doesContainDivision(String divisionName) {
        return divisions.containsKey(divisionName);
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }
    public String getConferenceName() {
        return conferenceName;
    }

    public Division getDivision(String divisionName) {
        return divisions.get(divisionName);
    }
}
