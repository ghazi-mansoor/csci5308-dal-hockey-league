package com.groupten.mocks;

import com.groupten.persistence.dao.ITeamDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamDBMock implements ITeamDAO {
    @Override
    public int createTeam(int divisionId, String teamName) {
        return 0;
    }

    @Override
    public List<HashMap<String, Object>> getTeams(String colName, String colValue) {
        List<HashMap<String, Object>> mockList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> mockMap = new HashMap<>();

        mockMap.put("teamID", 1);
        mockMap.put("teamName", "Dal Team");
        mockMap.put("leagueId", 1);

        mockList.add(mockMap);

        return mockList;
    }

    @Override
    public void updateTeam(int teamId, String teamName, String generalManager, String headCoach) {

    }

    @Override
    public void deleteTeam(int teamId) {

    }

    @Override
    public List<HashMap<String, Object>> getTeamPlayers(int teamId) {
        return null;
    }

    @Override
    public void attachTeamPlayer(int teamId, int playerId) {

    }

    @Override
    public void detachTeamPlayer(int teamId, int playerId) {

    }

}
