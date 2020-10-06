package com.groupten.services;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public interface PlayerDataService {
    int createPlayer(int leagueId, String playerName, String position, boolean isCaptain);
    List<HashMap<String,Object>> listPlayers(int leagueId);
    List<HashMap<String,Object>> getPlayers(int leagueId, String colName, String colValue);
    void updatePlayer(int playerId, String playerName, String position, boolean isCaptain);
    void deletePlayer(int playerId);

    List<HashMap<String,Object>> listFreeAgents(int leagueId);
    List<HashMap<String,Object>> getFreeAgents(int leagueId, String colName, String colValue);
}
