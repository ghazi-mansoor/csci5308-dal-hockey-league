package com.groupten.jdbc.league;

import com.groupten.jdbc.DatabaseConnection;
import com.groupten.jdbc.ResultSetOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class League implements LeagueInterface {
    DatabaseConnection  dbConnectionObj = DatabaseConnection.getDatabaseConnectionObject();

    //CRUD

    @Override
    public int createLeague(String leagueName) {
        int leagueId = 0;

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL createLeague(?,?)}");
            cs.setString(1, leagueName);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            leagueId = cs.getInt(2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return leagueId;
    };

    @Override
    public List<HashMap<String, Object>> getLeagues(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getLeagues(?,?)}");
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
    public void updateLeague(int leagueId, String leagueName){
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL updateLeague(?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, leagueName);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    @Override
    public void deleteLeague(int leagueId){
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL deleteLeague(?)}");
            cs.setInt(1, leagueId);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    //Relations

    @Override
    public List<HashMap<String, Object>> getLeagueConferences(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getLeagueConferences(?)}");
            cs.setInt(1, leagueId);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<HashMap<String, Object>> getLeaguePlayers(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getLeaguePlayers(?)}");
            cs.setInt(1, leagueId);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<HashMap<String, Object>> getLeagueFreeAgents(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getLeagueFreeAgents(?)}");
            cs.setInt(1, leagueId);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
