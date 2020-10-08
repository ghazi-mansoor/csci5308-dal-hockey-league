package com.groupten.jdbc.team;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public interface TeamInterface {
    //CRUD
    int createTeam(int divisionId, String teamName, String generalManager, String headCoach);
    List<HashMap<String,Object>> getTeams(String colName, String colValue);
    void updateTeam(int teamId, String teamName, String generalManager, String headCoach);
    void deleteTeam(int teamId);

    //Relations
    List<HashMap<String,Object>> getTeamPlayers(int teamId);
    void attachTeamPlayer(int teamId, int playerId);
    void detachTeamPlayer(int teamId, int playerId);
}
