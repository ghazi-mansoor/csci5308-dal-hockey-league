package com.groupten.leagueobjectmodel.division;

import com.groupten.jdbc.conference.ConferenceInterface;
import com.groupten.jdbc.division.DivisionInterface;
import com.groupten.jdbc.player.PlayerInterface;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.validator.Validator;

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
    private TeamInterface teamPersistenceAPI;
    private PlayerInterface playerPersistenceAPI;


    public Division(String dn) {
        divisionName = dn;
        teams = new HashMap<String, Team>();
    }

    public Division(String dn, DivisionInterface per) {
        divisionName = dn;
        teams = new HashMap<String, Team>();
        divisionPersistenceAPI = per;
    }

    public Division(int lID, int cID, int dID, String dn, DivisionInterface dPer, TeamInterface tPer, PlayerInterface pPer) {
        leagueID = lID;
        conferenceID = cID;
        divisionID = dID;
        divisionName = dn;
        teams = new HashMap<String, Team>();
        divisionPersistenceAPI = dPer;
        teamPersistenceAPI = tPer;
        playerPersistenceAPI = pPer;
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

    public void loadTeamsFromDB() {
        List<HashMap<String, Object>> teamMaps = divisionPersistenceAPI.getDivisionTeams(divisionID);
        for (Map<String, Object> teamMap : teamMaps) {
            int teamID = (int) teamMap.get("teamId");
            String teamName = (String) teamMap.get("teamName");
            String generalManager = (String) teamMap.get("generalManager");
            String headCoach = (String) teamMap.get("headCoach");
            Team team = new Team(leagueID, divisionID, teamID, teamName, generalManager, headCoach, teamPersistenceAPI, playerPersistenceAPI);
            System.out.println(teamName);
            addTeamToDivision(team);
            //team.loadPlayersFromDB();
        }
    }
}
