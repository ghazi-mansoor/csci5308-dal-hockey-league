package com.groupten.leagueobjectmodel.team;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private int teamID;
    private String teamName;
    private List<Player> players = new ArrayList<>();
    private GeneralManager generalManager;
    private Coach headCoach;
    private final int requiredNumberOfPlayers = 20;

    public Team(String tN) {
        teamName = tN;
    }

    public Team(int tID, String tN) {
        this(tN);
        teamID = tID;
    }

    public boolean addPlayer(Player player) {
        int initialSize = players.size();
        players.add(player);
        return players.size() > initialSize;
    }

    public boolean isPlayersCountValid() {
        return players.size() == requiredNumberOfPlayers;
    }

    public boolean doesTeamHaveOneCaptain() {
        List<Boolean> captains = new ArrayList<>();
        for (Player player : players) {
            captains.add(player.isCaptain());
        }
        int count = Collections.frequency(captains, true);

        return count == 1;
    }

    public static boolean isTeamNameValid(String tN) {
        if (tN.isEmpty() || tN.isBlank() || tN.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
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

    public void setGeneralManager(GeneralManager generalManager) {
        this.generalManager = generalManager;
    }

    public Coach getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(Coach headCoach) {
        this.headCoach = headCoach;
    }

    public List<Player> getPlayers() { return players; }

}
