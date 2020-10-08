package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.league.LeagueInterface;

import java.util.ArrayList;
import java.util.List;

public class League {
    private int leagueID;
    private String leagueName;
    private List<Conference> conferences;
    private List<Player> freeAgents;
    private LeagueInterface leaguePersistenceAPI;

    public League(String ln) {
        leagueName = ln;
        conferences = new ArrayList<Conference>();
        freeAgents = new ArrayList<Player>();
    }

    public League (String ln, LeagueInterface per) {
        leagueName = ln;
        leaguePersistenceAPI = per;
        conferences = new ArrayList<Conference>();
        freeAgents = new ArrayList<Player>();
    }

    public void addConferenceToLeague(Conference conference) {
        conferences.add(conference);
    }

    public void saveLeagueToDB() {
        leagueID = leaguePersistenceAPI.createLeague(leagueName);
        setConferenceForeignKeys();
        saveAllConferences();
        System.out.println("League saved to DB");
    }

    public void addFreeAgent(Player player) {
        freeAgents.add(player);
    }

    private void setConferenceForeignKeys() {
        for (Conference conference : conferences) {
            conference.setLeagueID(leagueID);
        }
    }

    private void saveAllConferences() {
        for (Conference conference : conferences) {
            conference.saveConferenceToDB();
        }
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public List<Conference> getDivisions() {
        return conferences;
    }

    public void setDivisions(List<Conference> conferences) {
        this.conferences = conferences;
    }

    public List<Player> getFreeAgents() {
        return freeAgents;
    }

    public void setFreeAgents(List<Player> freeAgents) {
        this.freeAgents = freeAgents;
    }

}
