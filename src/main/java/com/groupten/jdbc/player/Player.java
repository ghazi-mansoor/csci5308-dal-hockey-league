package com.groupten.jdbc.player;

import com.groupten.jdbc.DatabaseConnection;
import com.groupten.jdbc.ResultSetOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements PlayerInterface {
    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int createPlayer(int leagueId, String playerName, String position, boolean isCaptain) {
        int playerId = 0;
        try {
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
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return playerId;
    }

    @Override
    public List<HashMap<String, Object>> listPlayers(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL listPlayers(?)}");
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
    public List<HashMap<String, Object>> getPlayers(int leagueId, String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL getPlayers(?,?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, colName);
            cs.setString(3, colValue);
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
    public void updatePlayer(int playerId, String playerName, String position, boolean isCaptain) {
        try {
            CallableStatement cs = con.prepareCall("{CALL updatePlayer(?,?,?,?)}");
            cs.setInt(1, playerId);
            cs.setString(2, playerName);
            cs.setString(3, position);
            cs.setBoolean(4, isCaptain);
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
    public void deletePlayer(int playerId) {
        try {
            CallableStatement cs = con.prepareCall("{CALL deletePlayer(?)}");
            cs.setInt(1, playerId);
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
    public List<HashMap<String, Object>> listFreeAgents(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL listFreeAgents(?)}");
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
    public List<HashMap<String, Object>> getFreeAgents(int leagueId, String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL getFreeAgents(?,?,?)}");
            cs.setInt(1, leagueId);
            cs.setString(2, colName);
            cs.setString(3, colValue);
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
}
