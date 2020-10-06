package com.groupten.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface LeagueDataService {
    int createLeague(String leagueName);
    List<HashMap<String,Object>> listLeagues();
    List<HashMap<String,Object>> getLeagues(String colName, String colValue);
    void updateLeague(int leagueId, String leagueName);
    void deleteLeague(int leagueId);
}
