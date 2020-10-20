package com.groupten.dao.database;

import com.groupten.dao.DatabaseConnection;
import com.groupten.dao.IConferenceDAO;
import com.groupten.dao.ResultSetOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConferenceDAO implements IConferenceDAO {
    DatabaseConnection dbConnectionObj = DatabaseConnection.getDatabaseConnectionObject();

    @Override
    public int createConference(int leagueId, String conferenceName) {
        int conferenceId = 0;

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL createConference(?,?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, conferenceName);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            conferenceId = cs.getInt(3);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conferenceId;
    }

    @Override
    public List<HashMap<String, Object>> getConferences(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getConferences(?,?)}");
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
    public void updateConference(int conferenceId, String conferenceName) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL updateConference(?,?)}");
            cs.setInt(1, conferenceId);
            cs.setString(2, conferenceName);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConference(int conferenceId) {
        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL deleteConference(?)}");
            cs.setInt(1, conferenceId);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HashMap<String, Object>> getConferenceDivisions(int conferenceId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        try(Connection con = dbConnectionObj.connect()) {
            CallableStatement cs = con.prepareCall("{CALL getConferenceDivisions(?)}");
            cs.setInt(1, conferenceId);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
