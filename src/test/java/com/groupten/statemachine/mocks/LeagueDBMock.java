package com.groupten.statemachine.mocks;

import com.groupten.dao.ILeagueDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LeagueDBMock implements ILeagueDAO {

    @Override
    public int createLeague(String leagueName) {
        return 1;
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

    @Override
    public List<HashMap<String, Object>> getLeagueConferences(int leagueId) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getLeaguePlayers(int leagueId) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getLeagueFreeAgents(int leagueId) {
        return null;
    }
}
