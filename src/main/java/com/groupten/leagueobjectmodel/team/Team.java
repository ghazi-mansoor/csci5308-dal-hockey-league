package com.groupten.leagueobjectmodel.team;

import com.groupten.leagueobjectmodel.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Team {
    private int teamID;
    private String teamName;
    boolean AITeam;
    int lossPoint;
   

	private List<Player> players = new ArrayList<>();
	private HashMap<String, Integer> playerStrength = new HashMap<String, Integer>();
    private final int requiredNumberOfPlayers = 20;

    public Team(String tN) {
        teamName = tN;
    }

    public Team(int tID, String tN) {
        this(tN);
        teamID = tID;
    }

    public Team() {
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
    
    public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public HashMap<String, Integer> getPlayerStrength() {
		return playerStrength;
	}

	public void setPlayerStrength(HashMap<String, Integer> playerStrength) {
		this.playerStrength = playerStrength;
	}

	public boolean isAITeam() {
		return AITeam;
	}

	public void setAITeam(boolean aITeam) {
		AITeam = aITeam;
	}

	public int getLossPoint() {
		return lossPoint;
	}

	public void setLossPoint(int lossPoint) {
		this.lossPoint = lossPoint;
	}
	
	
}
