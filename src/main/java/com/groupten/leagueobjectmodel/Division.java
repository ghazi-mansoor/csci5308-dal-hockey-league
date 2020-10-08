package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.division.DivisionInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        teams.put(team.getTeamName(), team);
        return teams.containsKey(team.getTeamName());
    }

    public void saveDivisionToDB() {
        divisionID = divisionPersistenceAPI.createDivision(conferenceID, divisionName);
        setTeamForeignKeys();
        saveAllTeams();
        System.out.println("Division saved to DB");
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
