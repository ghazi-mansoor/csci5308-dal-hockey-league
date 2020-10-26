package com.groupten.leagueobjectmodel.division;

import com.groupten.leagueobjectmodel.team.Team;

import java.util.HashMap;
import java.util.Map;

public class Division {
    private int divisionID;
    private String divisionName;
    private Map<String, Team> teams = new HashMap<>();

    public Division(String dN) {
        divisionName = dN;
    }

    public Division(int dID, String dN) {
        this(dN);
        divisionID = dID;
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

    public static boolean isDivisionNameValid(String dN) {
        if (dN.isEmpty() || dN.isBlank() || dN.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int dID) {
        divisionID = dID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String dN) {
        divisionName = dN;
    }
}
