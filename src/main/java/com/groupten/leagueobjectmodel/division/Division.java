package com.groupten.leagueobjectmodel.division;

import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.persistence.dao.IDivisionDAO;

import java.util.HashMap;
import java.util.Map;

public class Division {
    private int divisionID;
    private int conferenceID;
    private String divisionName;
    private IDivisionDAO divisionDAO;
    private Map<String, Team> teams = new HashMap<>();

    public Division(String divisionName) {
        this.divisionName = divisionName;
    }

    public Division(String divisionName, IDivisionDAO divisionDAO) {
        this(divisionName);
        this.divisionDAO = divisionDAO;
    }

    public Division(int divisionID, String divisionName) {
        this(divisionName);
        this.divisionID = divisionID;
    }

    public boolean addTeam(Team team) {
        if(Team.isTeamNameValid(team.getTeamName())){
            String teamName = team.getTeamName();
            int initialSize = teams.size();
            teams.put(teamName, team);
            return teams.size() > initialSize;
        }else{
            return false;
        }
    }

    public boolean containsTeam(String teamName) {
        return teams.containsKey(teamName);
    }

    public Map<String, Team> getTeams() { return teams; }

    public Team getTeam(String teamName) {
        return teams.get(teamName);
    }

    public static boolean isDivisionNameValid(String divisionName) {
        if (divisionName.isEmpty() || divisionName.isBlank() || divisionName.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public void setConferenceID(int conferenceID) {
        this.conferenceID = conferenceID;
    }

    public int getConferenceID() {
        return conferenceID;
    }

    public boolean saveDivision() {
        divisionID = divisionDAO.createDivision(conferenceID, divisionName);
        if (divisionID != 0) {
            return true;
        } else {
            return false;
        }
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
