package com.groupten.leagueobjectmodel;

public class Player {
    private int leagueID;
    private int teamID;
    private int playerID;
    private String playerName;
    private String position;
    private boolean captain;

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
