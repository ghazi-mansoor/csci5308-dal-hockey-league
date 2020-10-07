package com.groupten.jdbc.conference;

import com.groupten.jdbc.DatabaseConnection;
import com.groupten.jdbc.ResultSetOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Conference implements ConferenceInterface {
    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int createConference(int leagueId, String conferenceName) {
        int conferenceId = 0;
        try {
            CallableStatement cs = con.prepareCall("{CALL createConference(?,?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, conferenceName);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            conferenceId = cs.getInt(3);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return conferenceId;
    }

    @Override
    public List<HashMap<String, Object>> listConferences(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL listConferences(?)}");
            cs.setInt(1, leagueId);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public List<HashMap<String, Object>> getConferences(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL getConferences(?,?)}");
            cs.setString(1, colName);
            cs.setString(2, colValue);
            ResultSet rs = cs.executeQuery();
            list = ResultSetOperation.convertResultSetToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    @Override
    public void updateConference(int conferenceId, String conferenceName) {
        try {
            CallableStatement cs = con.prepareCall("{CALL updateConference(?,?)}");
            cs.setInt(1, conferenceId);
            cs.setString(2, conferenceName);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteConference(int conferenceId) {
        try {
            CallableStatement cs = con.prepareCall("{CALL deleteConference(?)}");
            cs.setInt(1, conferenceId);
            cs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
