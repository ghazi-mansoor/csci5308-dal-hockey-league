package com.groupten.jdbc.player;

import com.groupten.jdbc.DatabaseConnection;
import com.groupten.jdbc.ResultSetOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerDAO implements IPlayerDAO {
    DatabaseConnection dbConnectionObj = DatabaseConnection.getDatabaseConnectionObject();

    @Override
    public int createPlayer(int leagueId, String playerName, String position, boolean isCaptain) {
        int playerId = 0;

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL createPlayer(?,?,?,?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, playerName);
            cs.setString(3, position);
            cs.setBoolean(4, isCaptain);
            cs.registerOutParameter(5, java.sql.Types.INTEGER);
            cs.executeUpdate();
            playerId = cs.getInt(5);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return playerId;
    }

    @Override
    public List<HashMap<String, Object>> getPlayers(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getPlayers(?,?)}");
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
    public void updatePlayer(int playerId, String playerName, String position, boolean isCaptain) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL updatePlayer(?,?,?,?)}");
            cs.setInt(1, playerId);
            cs.setString(2, playerName);
            cs.setString(3, position);
            cs.setBoolean(4, isCaptain);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlayer(int playerId) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL deletePlayer(?)}");
            cs.setInt(1, playerId);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HashMap<String, Object>> getCaptains(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getCaptains(?,?)}");
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
    public List<HashMap<String, Object>> getFreeAgents(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getFreeAgents(?,?)}");
            cs.setString(1, colName);
            cs.setString(2, colValue);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
