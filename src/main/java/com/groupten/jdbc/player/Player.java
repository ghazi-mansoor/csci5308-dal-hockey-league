package com.groupten.jdbc.player;

import com.groupten.jdbc.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ResultSet listPlayers(int leagueId) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL listPlayers(?)}");
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
    public ResultSet getPlayers(int leagueId, String colName, String colValue) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL getPlayers(?,?,?)}");
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
    public ResultSet listFreeAgents(int leagueId) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL listFreeAgents(?)}");
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
    public ResultSet getFreeAgents(int leagueId, String colName, String colValue) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL getFreeAgents(?,?,?)}");
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
}
