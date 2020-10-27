package com.groupten.dao;

import java.util.HashMap;
import java.util.List;

public interface IPlayerDAO {
    int createPlayer(int leagueId, String playerName, String position, boolean isCaptain);
    List<HashMap<String,Object>> getPlayers(String colName, String colValue);
    void updatePlayer(int playerId, String playerName, String position, boolean isCaptain);
    void deletePlayer(int playerId);
    List<HashMap<String,Object>> getCaptains(String colName, String colValue);
    List<HashMap<String,Object>> getFreeAgents(String colName, String colValue);
}
