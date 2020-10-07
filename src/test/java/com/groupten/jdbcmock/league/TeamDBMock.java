package com.groupten.jdbcmock.league;

import com.groupten.jdbc.team.TeamInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamDBMock implements TeamInterface {
    @Override
    public int createTeam(int divisionId, String teamName, String generalManager, String headCoach) {
        return 0;
    }

    @Override
    public List<HashMap<String, Object>> listTeams(int divisionId) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getTeams(String colName, String colValue) {
        List<HashMap<String,Object>> mockList = new ArrayList<HashMap<String,Object>>();
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
    public void addTeamPlayer(int teamId, int playerId) {

    }

    @Override
    public void removeTeamPlayer(int teamId, int playerId) {

    }

    @Override
    public List<HashMap<String, Object>> listTeamPlayers(int teamId) {
        return null;
    }
}
