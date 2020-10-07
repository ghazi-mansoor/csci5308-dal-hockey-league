package com.groupten.leagueobjectmodel;

import java.util.ArrayList;

public class Player {
    private int leagueID;
    private int teamID;
    private int playerID;
    private String playerName;
    private String position;
    private boolean captain;
    private IPersistence persistenceAPI;

    public Player(String pn, String pos, boolean cap) {
        playerName = pn;
        position = pos;
        captain = cap;
    }

    public Player(String pn, String pos, boolean cap, IPersistence per) {
        playerName = pn;
        position = pos;
        captain = cap;
        persistenceAPI = per;
    }

    public void savePlayerToDB() {
        playerID = persistenceAPI.persistPlayer(this);
        System.out.println("Player saved to DB");
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

}
