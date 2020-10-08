package com.groupten.jdbc.player;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public interface PlayerInterface {
    //CRUD
    int createPlayer(int leagueId, String playerName, String position, boolean isCaptain);
    List<HashMap<String,Object>> getPlayers(String colName, String colValue);
    void updatePlayer(int playerId, String playerName, String position, boolean isCaptain);
    void deletePlayer(int playerId);

    //Filters
    List<HashMap<String,Object>> getCaptains(String colName, String colValue);
    List<HashMap<String,Object>> getFreeAgents(String colName, String colValue);
}
