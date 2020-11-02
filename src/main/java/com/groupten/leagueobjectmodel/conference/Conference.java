package com.groupten.leagueobjectmodel.conference;

import com.groupten.leagueobjectmodel.division.Division;

import java.util.HashMap;
import java.util.Map;

public class Conference {
    public int leagueID;
    private int conferenceID;
    private String conferenceName;
    private final Map<String, Division> divisions = new HashMap<>();

    public Conference(String conferenceName) {
        this.conferenceName = conferenceName;
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
        System.out.println("Conference saved to DB. conferenceID set to 1.");
        return true;
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
