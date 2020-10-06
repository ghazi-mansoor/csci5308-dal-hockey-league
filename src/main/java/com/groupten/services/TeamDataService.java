package com.groupten.services;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public interface TeamDataService {
    int createTeam(int divisionId, String teamName, String generalManager, String headCoach);
    List<HashMap<String,Object>> listTeams(int divisionId);
    List<HashMap<String,Object>> getTeams(int divisionId, String colName, String colValue);
    void updateTeam(int teamId, String teamName, String generalManager, String headCoach);
    void deleteTeam(int teamId);

    void addTeamPlayer(int teamId, int playerId);
    void removeTeamPlayer(int teamId, int playerId);
    List<HashMap<String,Object>> listTeamPlayers(int teamId);
}
