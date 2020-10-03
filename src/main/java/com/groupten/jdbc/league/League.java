package com.groupten.jdbc.league;

import com.groupten.jdbc.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class League implements LeagueInterface {
    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int createLeague(String leagueName) {
        int leagueId = 0;
        try {
            CallableStatement cs = con.prepareCall("{CALL createLeague(?,?)}");
            cs.setString(1, leagueName);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            leagueId = cs.getInt(2);

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
        return leagueId;
    };

    @Override
    public ResultSet listLeagues(){
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL listLeagues()}");
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
    };

    @Override
    public ResultSet getLeagues(String colName, String colValue){
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL getLeagues(?,?)}");
            cs.setString(1, colName);
            cs.setString(2, colValue);
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
    public void updateLeague(int leagueId, String leagueName){
        try {
            CallableStatement cs = con.prepareCall("{CALL updateLeague(?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, leagueName);
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
    };

    @Override
    public void deleteLeague(int leagueId){
        try {
            CallableStatement cs = con.prepareCall("{CALL deleteLeague(?)}");
            cs.setInt(1, leagueId);
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
    };
}
