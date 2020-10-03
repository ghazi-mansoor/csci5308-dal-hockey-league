package com.groupten.jdbc.team;

import java.sql.ResultSet;

public interface TeamInterface {
    int createTeam(int divisionId, String teamName, String generalManager, String headCoach);
    ResultSet listTeams(int divisionId);
    ResultSet getTeams(int divisionId, String colName, String colValue);
    void updateTeam(int teamId, String teamName, String generalManager, String headCoach);
    void deleteTeam(int teamId);

    void addTeamPlayer(int teamId, int playerId);
    void removeTeamPlayer(int teamId, int playerId);
    ResultSet listTeamPlayers(int teamId);
}
