package com.groupten.jdbc.team;

import com.groupten.jdbc.DatabaseConnection;
import com.groupten.jdbc.ResultSetOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Team implements TeamInterface {
    DatabaseConnection  dbConnectionObj = DatabaseConnection.getDatabaseConnectionObject();

    @Override
    public int createTeam(int divisionId, String teamName, String generalManager, String headCoach) {
        int teamId = 0;

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL createTeam(?,?,?,?,?)}");
            cs.setInt(1, divisionId);
            cs.setString(2, teamName);
            cs.setString(3, generalManager);
            cs.setString(4, headCoach);
            cs.registerOutParameter(5, java.sql.Types.INTEGER);
            cs.executeUpdate();
            teamId = cs.getInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teamId;
    }

    @Override
    public List<HashMap<String, Object>> listTeams(int divisionId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL listTeams(?)}");
            cs.setInt(1, divisionId);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<HashMap<String, Object>> getTeams(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getTeams(?,?)}");
            cs.setString(1, colName);
            cs.setString(2, colValue);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateTeam(int teamId, String teamName, String generalManager, String headCoach) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL updateTeam(?,?,?,?)}");
            cs.setInt(1, teamId);
            cs.setString(2, teamName);
            cs.setString(3, generalManager);
            cs.setString(4, headCoach);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeam(int teamId) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL deleteTeam(?)}");
            cs.setInt(1, teamId);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTeamPlayer(int teamId, int playerId) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL addTeamPlayer(?,?)}");
            cs.setInt(1, teamId);
            cs.setInt(2, playerId);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTeamPlayer(int teamId, int playerId) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL removeTeamPlayer(?,?)}");
            cs.setInt(1, teamId);
            cs.setInt(2, playerId);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HashMap<String, Object>> listTeamPlayers(int teamId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL listTeamPlayers(?)}");
            cs.setInt(1, teamId);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
