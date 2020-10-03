package com.groupten.jdbc.conference;

import com.groupten.jdbc.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ResultSet listConferences(int leagueId) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL listConferences(?)}");
            cs.setInt(1, leagueId);
            rs = cs.executeQuery();
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
        return rs;
    }

    @Override
    public ResultSet getConferences(int leagueId, String colName, String colValue) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL getConferences(?,?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, colName);
            cs.setString(3, colValue);
            rs = cs.executeQuery();
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

        return rs;
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
