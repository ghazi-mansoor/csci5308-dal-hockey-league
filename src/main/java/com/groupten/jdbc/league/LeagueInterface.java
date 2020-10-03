package com.groupten.jdbc.league;

import java.sql.ResultSet;

public interface LeagueInterface {
    int createLeague(String leagueName);
    ResultSet listLeagues();
    ResultSet getLeagues(String colName, String colValue);
    void updateLeague(int leagueId, String leagueName);
    void deleteLeague(int leagueId);
}
