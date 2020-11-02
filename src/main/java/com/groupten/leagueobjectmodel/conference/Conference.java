package com.groupten.leagueobjectmodel.conference;

import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.persistence.dao.IConferenceDAO;

import java.util.HashMap;
import java.util.Map;

public class Conference {
    public int leagueID;
    private int conferenceID;
    private String conferenceName;
    private IConferenceDAO conferenceDAO;
    private final Map<String, Division> divisions = new HashMap<>();

    public Conference(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public Conference(String conferenceName, IConferenceDAO conferenceDAO) {
        this(conferenceName);
        this.conferenceDAO = conferenceDAO;
    }

    public Conference(int conferenceID, String conferenceName) {
        this(conferenceName);
        this.conferenceID = conferenceID;
    }

    public boolean addDivision(Division division) {
        if(Division.isDivisionNameValid(division.getDivisionName())){
            String divisionName = division.getDivisionName();
            int initialSize = divisions.size();
            divisions.put(divisionName, division);
            return divisions.size() > initialSize;
        }else{
            return false;
        }
    }

    public boolean isNumberOfDivisionsEven() {
        return divisions.size() % 2 == 0;
    }

    public boolean containsDivision(String divisionName) {
        return divisions.containsKey(divisionName);
    }

    public static boolean isConferenceNameValid(String conferenceName) {
        if (conferenceName.isEmpty() || conferenceName.isBlank() || conferenceName.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public Division getDivision(String divisionName) {
        return divisions.get(divisionName);
    }

    public Map<String, Division> getDivisions() { return divisions; }

    public boolean saveConference() {
        conferenceID = conferenceDAO.createConference(leagueID, conferenceName);
        if (conferenceID != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(int conferenceID) {
        this.conferenceID = conferenceID;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
}
