package com.groupten.jdbc.player;

import java.sql.ResultSet;

public interface PlayerInterface {
    int createPlayer(int leagueId, String playerName, String position, boolean isCaptain);
    ResultSet listPlayers(int leagueId);
    ResultSet getPlayers(int leagueId, String colName, String colValue);
    void updatePlayer(int playerId, String playerName, String position, boolean isCaptain);
    void deletePlayer(int playerId);

    ResultSet listFreeAgents(int leagueId);
    ResultSet getFreeAgents(int leagueId, String colName, String colValue);
}
