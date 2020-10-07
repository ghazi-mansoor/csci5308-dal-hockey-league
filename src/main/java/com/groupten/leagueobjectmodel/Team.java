package com.groupten.leagueobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int divisionID;
    private int teamID;
    private String teamName;
    private String generalManager;
    private String headCoach;
    private List<Player> players;
    private IPersistence persistenceAPI;

    public Team(String tn, String gm, String hc) {
        teamName = tn;
        generalManager = gm;
        headCoach = hc;
        players = new ArrayList<Player>();
    }

    public Team(String tn, String gm, String hc, IPersistence per) {
        teamName = tn;
        generalManager = gm;
        headCoach = hc;
        players = new ArrayList<Player>();
        persistenceAPI = per;
    }

    public void addPlayerToTeam(Player player) {
        players.add(player);
    }

    public void saveTeamToDB() {
        teamID = persistenceAPI.persistTeam(this);
        setPlayerForeignKeys();
        saveAllPlayers();
        System.out.println("Team saved to DB");
    }

    private void setPlayerForeignKeys() {
        for (Player player : players) {
            player.setTeamID(teamID);
        }
    }

    private void saveAllPlayers() {
        for (Player player : players) {
            player.savePlayerToDB();
        }
    }


    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(String generalManager) {
        this.generalManager = generalManager;
    }

    public String getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }
}
