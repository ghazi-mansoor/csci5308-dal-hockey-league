package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.player.PlayerInterface;
import com.groupten.jdbc.team.TeamInterface;

public class Player {
    private int leagueID;
    private int teamID;
    private int playerID;
    private String playerName;
    private String position;
    private boolean captain;
    private PlayerInterface playerPersistenceAPI;
    private TeamInterface teamPersistenceAPI;

    public Player(String pn, String pos, boolean cap) {
        playerName = pn;
        position = pos;
        captain = cap;
    }

    public Player(String pn, String pos, boolean cap, PlayerInterface per) {
        playerName = pn;
        position = pos;
        captain = cap;
        playerPersistenceAPI = per;
    }

    public Player(String pn, String pos, boolean cap, PlayerInterface per, TeamInterface perTeam) {
        playerName = pn;
        position = pos;
        captain = cap;
        playerPersistenceAPI = per;
        teamPersistenceAPI = perTeam;
    }

    public void savePlayerToDB() {
        playerID = playerPersistenceAPI.createPlayer(leagueID, playerName, position, captain);
        teamPersistenceAPI.addTeamPlayer(teamID, playerID);
        System.out.println("Player saved to DB");
    }

    public void saveFreeAgentPlayerToDB() {
        playerID = playerPersistenceAPI.createPlayer(leagueID, playerName, position, captain);
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getPlayerName() {
        return playerName;
    }

}
