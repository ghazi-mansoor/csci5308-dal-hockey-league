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
    public List<HashMap<String, Object>> listLeagues() {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL listLeagues()}");
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
    };

    @Override
    public List<HashMap<String, Object>> getLeagues(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL getLeagues(?,?)}");
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
