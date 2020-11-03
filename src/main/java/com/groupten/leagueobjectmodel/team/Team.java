package com.groupten.leagueobjectmodel.team;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.persistence.dao.ITeamDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private final int REQUIRED_NUMBER_OF_PLAYERS = 20;

    private int teamID;
    private int divisionID;
    private String teamName;
    private boolean aITeam;
    private List<Player> players = new ArrayList<>();
    private GeneralManager generalManager;
    private Coach headCoach;
    private double teamStrength;
    private int winPoint;
    private int lossPoint;

    public Team() {
        this.aITeam = true;
    }

    public Team(String teamName) {
        this.aITeam = true;
        this.teamName = teamName;
    }

    public Team(int teamID, String teamName) {
        this(teamName);
        this.aITeam = true;
        this.teamID = teamID;
    }

    public boolean addPlayer(Player player) {
        if (Player.arePlayerFieldsValid(player.getPlayerName(), player.getPosition(),
                player.getSkating(), player.getShooting(), player.getChecking(), player.getSaving())) {
            int initialSize = players.size();
            players.add(player);
            return players.size() > initialSize;
        } else{
            return false;
        }
    }

    public void persistPlayerWithTeam(Player player) {
        ITeamDAO teamDAO = Injector.instance().getTeamDatabaseObject();
        teamDAO.attachTeamPlayer(teamID, player.getPlayerID());
    }

    public boolean isPlayersCountValid() {
        return players.size() == REQUIRED_NUMBER_OF_PLAYERS;
    }

    public boolean doesTeamHaveOneCaptain() {
        List<Boolean> captains = new ArrayList<>();
        for (Player player : players) {
            captains.add(player.isCaptain());
        }
        int count = Collections.frequency(captains, true);

        return count == 1;
    }

    public static boolean isTeamNameValid(String teamName) {
        if (teamName.isEmpty() || teamName.isBlank() || teamName.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public double calculateTeamStrength() {
        for (Player player : players) {
            String pos = player.getPosition();
            double playerStrength = player.calculateStrength();
            if (player.isInjured()) {
                teamStrength += (playerStrength / 2);
            } else {
                teamStrength += playerStrength;
            }
        }

        return teamStrength;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int tID) {
        teamID = tID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String tN) {
        teamName = tN;
    }

    public GeneralManager getGeneralManager() {
        return generalManager;
    }

    public boolean setGeneralManager(GeneralManager generalManager) {
        if (GeneralManager.isManagerNameValid(generalManager.getManagerName())) {
            this.generalManager = generalManager;
            return true;
        }else{
            return false;
        }
    }

    public Coach getHeadCoach() {
        return headCoach;
    }

    public boolean setHeadCoach(Coach headCoach) {
        if (Coach.areCoachFieldsValid(headCoach.getCoachName(), headCoach.getSkating(), headCoach.getShooting(), headCoach.getChecking(), headCoach.getSaving())) {
            this.headCoach = headCoach;
            return true;
        }else{
            return false;
        }
    }

    public List<Player> getPlayers() { return players; }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public double getTeamStrength() {
        return teamStrength;
    }

    public void setTeamStrength(double teamStrength) {
        this.teamStrength = teamStrength;
    }

    public boolean isaITeam() {
        return aITeam;
    }

    public void setaITeam(boolean aITeam) {
        this.aITeam = aITeam;
    }

    public int getWinPoint() {
        return winPoint;
    }

    public void setWinPoint(int winPoint) {
        this.winPoint = winPoint;
    }

    public int getLossPoint() {
        return lossPoint;
    }

    public void setLossPoint(int lossPoint) {
        this.lossPoint = lossPoint;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public boolean saveTeam() {
        ITeamDAO teamDAO = Injector.instance().getTeamDatabaseObject();
        teamID = teamDAO.createTeam(divisionID, teamName);
        if (teamID != -0) {
            return true;
        } else {
            return false;
        }
    }
}
