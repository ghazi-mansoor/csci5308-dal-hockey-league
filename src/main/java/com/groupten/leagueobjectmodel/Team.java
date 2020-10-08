package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.team.TeamInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    private int leagueID;
    private int divisionID;
    private int teamID;
    private String teamName;
    private String generalManager;
    private String headCoach;
    private Map<String, Player> players;
    private TeamInterface teamPersistenceAPI;

    public Team(String tn, String gm, String hc) {
        teamName = tn;
        generalManager = gm;
        headCoach = hc;
        players = new HashMap<String, Player>();
    }

    public Team(String tn, String gm, String hc, TeamInterface per) {
        teamName = tn;
        generalManager = gm;
        headCoach = hc;
        players = new HashMap<String, Player>();
        teamPersistenceAPI = per;
    }

    public boolean addPlayerToTeam(Player player) {
        players.put(player.getPlayerName(), player);
        return players.containsKey(player.getPlayerName());
    }

    public void saveTeamToDB() {
        teamID = teamPersistenceAPI.createTeam(divisionID, teamName, generalManager, headCoach);
        setPlayerForeignKeys();
        saveAllPlayers();
        System.out.println("Team saved to DB");
    }

    private void setPlayerForeignKeys() {
        for (Player player : players.values()) {
            player.setTeamID(teamID);
            player.setLeagueID(leagueID);
        }
    }

    private void saveAllPlayers() {
        for (Player player : players.values()) {
            player.savePlayerToDB();
        }
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }
}
