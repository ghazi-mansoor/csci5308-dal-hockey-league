package com.groupten.leagueobjectmodel.player;

import com.groupten.jdbc.player.PlayerInterface;
import com.groupten.jdbc.team.TeamInterface;

public class Player implements IPlayer {
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

    public Player(int lID, int pID, String pn, String pos, boolean cap, PlayerInterface pPer, TeamInterface tPer) {
        leagueID = lID;
        playerID = pID;
        playerName = pn;
        position = pos;
        captain = cap;
        playerPersistenceAPI = pPer;
        teamPersistenceAPI = tPer;
    }

    public Player(int lID, int tID, int pID, String pn, String pos, boolean cap, PlayerInterface pPer, TeamInterface tPer) {
        leagueID = lID;
        teamID = tID;
        playerID = pID;
        playerName = pn;
        position = pos;
        captain = cap;
        playerPersistenceAPI = pPer;
        teamPersistenceAPI = tPer;
    }

    public boolean savePlayerToDB() {
        playerID = playerPersistenceAPI.createPlayer(leagueID, playerName, position, captain);
        teamPersistenceAPI.attachTeamPlayer(teamID, playerID);
        return (playerID != 0);
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

    public String getPosition() {
        return position;
    }

    public boolean getCaptain() {
        return captain;
    }

}
