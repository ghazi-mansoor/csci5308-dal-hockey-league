package com.groupten.leagueobjectmodel.division;

import com.groupten.jdbc.division.DivisionInterface;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class Division {
    private int leagueID;
    private int conferenceID;
    private int divisionID;
    private String divisionName;
    private Map<String, Team> teams;
    private DivisionInterface divisionPersistenceAPI;

    public Division(String dn) {
        divisionName = dn;
        teams = new HashMap<String, Team>();
    }

    public Division(String dn, DivisionInterface per) {
        divisionName = dn;
        teams = new HashMap<String, Team>();
        divisionPersistenceAPI = per;
    }

    public boolean addTeamToDivision(Team team) {
        String teamName = team.getTeamName();
        if (Validator.areStringsValid(teamName)) {
            teams.put(team.getTeamName(), team);
            return teams.containsKey(team.getTeamName());
        } else {
            return false;
        }
    }

    public boolean saveDivisionToDB() {
        divisionID = divisionPersistenceAPI.createDivision(conferenceID, divisionName);
        setTeamForeignKeys();
        saveAllTeams();
        return (divisionID != 0);
    }

    private void setTeamForeignKeys() {
        for (Team team : teams.values()) {
            team.setDivisionID(divisionID);
            team.setLeagueID(leagueID);
        }
    }

    private void saveAllTeams() {
        for (Team team : teams.values()) {
            team.saveTeamToDB();
        }
    }

    public boolean doesTeamExistInMemory(String teamName) {
        return teams.containsKey(teamName);
    }

    public void setConferenceID(int conferenceID) {
        this.conferenceID = conferenceID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }
}
