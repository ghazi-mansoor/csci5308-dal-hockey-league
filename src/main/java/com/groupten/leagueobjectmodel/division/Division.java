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

    public Division() {
	}
    

	public Map<String, Team> getTeams() {
		return teams;
	}

	public void setTeams(Map<String, Team> teams) {
		this.teams = teams;
	}

	public boolean addTeam(Team team) {
        String teamName = team.getTeamName();
        int initialSize = teams.size();
        teams.put(teamName, team);

        return teams.size() > initialSize;
    }

    public boolean containsTeam(String teamName) {
        return teams.containsKey(teamName);
    }

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
