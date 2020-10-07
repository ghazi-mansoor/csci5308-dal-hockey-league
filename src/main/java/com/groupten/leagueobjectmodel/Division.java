package com.groupten.leagueobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Division {
    private int conferenceID;
    private int divisionID;
    private String divisionName;
    private List<Team> teams;
    private IPersistence persistenceAPI;

    public Division(String dn) {
        divisionName = dn;
        teams = new ArrayList<Team>();
    }

    public Division(String dn, IPersistence per) {
        divisionName = dn;
        teams = new ArrayList<Team>();
        persistenceAPI = per;
    }

    public void addTeamToDivision(Team team) {
        teams.add(team);
    }

    public void saveDivisionToDB() {
        divisionID = persistenceAPI.persistDivision(this);
        setTeamForeignKeys();
        saveAllTeams();
        System.out.println("Division saved to DB");
    }

    private void setTeamForeignKeys() {
        for (Team team : teams) {
            team.setDivisionID(divisionID);
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
}
