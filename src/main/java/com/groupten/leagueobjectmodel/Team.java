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
    private List <Player> players;
    private TeamInterface teamPersistenceAPI;

    public Team(String tn, String gm, String hc) {
        teamName = tn;
        generalManager = gm;
        headCoach = hc;
        players = new ArrayList<Player>();
    }

    public Team(String tn, String gm, String hc, TeamInterface per) {
        teamName = tn;
        generalManager = gm;
        headCoach = hc;
        players = new ArrayList<Player>();
        teamPersistenceAPI = per;
    }

    public boolean addPlayerToTeam(Player player) {
        int sizeBeforeAddition = players.size();
        players.add(player);
        int sizeAfterAddition = players.size();
        return sizeAfterAddition == sizeBeforeAddition + 1;
    }

    public void saveTeamToDB() {
        teamID = teamPersistenceAPI.createTeam(divisionID, teamName, generalManager, headCoach);
        setPlayerForeignKeys();
        saveAllPlayers();
        System.out.println("Team saved to DB");
    }

    private void setPlayerForeignKeys() {
        for (Player player : players) {
            player.setTeamID(teamID);
            player.setLeagueID(leagueID);
        }
    }

    private void saveAllPlayers() {
        for (Player player : players) {
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
