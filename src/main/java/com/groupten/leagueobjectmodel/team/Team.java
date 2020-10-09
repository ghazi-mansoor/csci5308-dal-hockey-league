package com.groupten.leagueobjectmodel.team;

import com.groupten.jdbc.player.PlayerInterface;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.validator.Validator;
import com.groupten.leagueobjectmodel.player.Player;

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
    private PlayerInterface playerPersistenceAPI;

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

    public Team(int lID, int dID, int tID, String tn, String gm, String hc, TeamInterface tPer, PlayerInterface pPer) {
        leagueID = lID;
        divisionID = dID;
        teamID = tID;
        teamName = tn;
        generalManager = gm;
        headCoach = hc;
        players = new ArrayList<Player>();
        teamPersistenceAPI = tPer;
    }

    public boolean addPlayerToTeam(Player player) {
        String playerName = player.getPlayerName();
        String playerPosition = player.getPosition();

        if (Validator.areStringsValid(playerName) && Validator.isPositionValid(playerPosition)) {
            int numberOfFreeAgents = players.size();
            players.add(player);
            int numberOfFreeAgentsPostAdditions = players.size();

            return numberOfFreeAgentsPostAdditions == numberOfFreeAgents + 1;
        } else {
            return false;
        }
    }

    public boolean saveTeamToDB() {
        teamID = teamPersistenceAPI.createTeam(divisionID, teamName, generalManager, headCoach);
        setPlayerForeignKeys();
        saveAllPlayers();
        return (teamID != 0);
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

    public boolean isOnlyOnePlayerCaptain() {
        List<Boolean> captains = new ArrayList<Boolean>();
        int count = 0;

        for (Player player : players) {
            captains.add((player.getCaptain()));
        }

        for (Boolean captain : captains) {
            if (captain) {
                count++;
            }
        }

        return count == 1;

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

    public void loadPlayersFromDB() {
        List<HashMap<String, Object>> playerMaps = teamPersistenceAPI.getTeamPlayers(teamID);
        for (Map<String, Object> playerMap : playerMaps) {
            int playerID = (int) playerMap.get("playerId");
            String playerName = (String) playerMap.get("playerName");
            String position = (String) playerMap.get("position");
            Boolean captain = (Boolean) playerMap.get("captain");
            Player player = new Player(leagueID, teamID, playerID, playerName, position, captain, playerPersistenceAPI, teamPersistenceAPI);
            System.out.println(playerName);
            addPlayerToTeam(player);
        }
    }

}
