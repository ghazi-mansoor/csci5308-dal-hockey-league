package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.division.DivisionInterface;

import java.util.ArrayList;
import java.util.List;

public class Division {
    private int leagueID;
    private int conferenceID;
    private int divisionID;
    private String divisionName;
    private List<Team> teams;
    private DivisionInterface divisionPersistenceAPI;

    public Division(String dn) {
        divisionName = dn;
        teams = new ArrayList<Team>();
    }

    public Division(String dn, DivisionInterface per) {
        divisionName = dn;
        teams = new ArrayList<Team>();
        divisionPersistenceAPI = per;
    }

    public void addTeamToDivision(Team team) {
        teams.add(team);
    }

    public void saveDivisionToDB() {
        divisionID = divisionPersistenceAPI.createDivision(conferenceID, divisionName);
        setTeamForeignKeys();
        saveAllTeams();
        System.out.println("Division saved to DB");
    }

    private void setTeamForeignKeys() {
        for (Team team : teams) {
            team.setDivisionID(divisionID);
            team.setLeagueID(leagueID);
        }
    }

    private void saveAllTeams() {
        for (Team team : teams) {
            team.saveTeamToDB();
        }
    }

    public int getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(int conferenceID) {
        this.conferenceID = conferenceID;
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

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }
}
