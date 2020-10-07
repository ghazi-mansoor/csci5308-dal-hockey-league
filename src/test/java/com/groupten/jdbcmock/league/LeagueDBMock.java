package com.groupten.jdbcmock.league;

import com.groupten.jdbc.DatabaseConnection;
import com.groupten.jdbc.league.LeagueInterface;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LeagueDBMock implements LeagueInterface {
    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int createLeague(String leagueName) {
        return 1;
    };

    @Override
    public List<HashMap<String, Object>> listLeagues() {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        return list;
    };

    @Override
    public List<HashMap<String, Object>> getLeagues(String colName, String colValue) {
        HashMap<String, Object> mMap = new HashMap<>();
        List<HashMap<String, Object>> list = new ArrayList<>();
        return list;
    }

    @Override
    public void updateLeague(int leagueId, String leagueName){
    }

    @Override
    public void deleteLeague(int leagueId) {
    }
}
