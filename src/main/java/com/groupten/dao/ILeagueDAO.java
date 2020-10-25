package com.groupten.dao;

import java.util.HashMap;
import java.util.List;

public interface ILeagueDAO {
    int createLeague(String leagueName);
    List<HashMap<String,Object>> getLeagues(String colName, String colValue);
    void updateLeague(int leagueId, String leagueName);
    void deleteLeague(int leagueId);
    List<HashMap<String,Object>> getLeagueConferences(int leagueId);
    List<HashMap<String,Object>> getLeaguePlayers(int leagueId);
    List<HashMap<String,Object>> getLeagueFreeAgents(int leagueId);
}
