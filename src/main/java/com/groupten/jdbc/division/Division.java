package com.groupten.jdbc.division;

import com.groupten.jdbc.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Division implements DivisionInterface {
    static Connection con = DatabaseConnection.getConnection();

    @Override
    public int createDivision(int conferenceId, String divisionName) {
        int divisionId = 0;
        try {
            CallableStatement cs = con.prepareCall("{CALL createDivision(?,?,?)}");
            cs.setInt(1, conferenceId);
            cs.setString(2, divisionName);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            divisionId = cs.getInt(3);

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
        return divisionId;
    }

    @Override
    public ResultSet listDivisions(int conferenceId) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL listDivisions(?)}");
            cs.setInt(1, conferenceId);
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
    public ResultSet getDivisions(int conferenceId, String colName, String colValue) {
        ResultSet rs = null;
        try {
            CallableStatement cs = con.prepareCall("{CALL getDivisions(?,?,?)}");
            cs.setInt(1, conferenceId);
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
    public void updateDivision(int divisionId, String divisionName) {
        try {
            CallableStatement cs = con.prepareCall("{CALL updateDivision(?,?)}");
            cs.setInt(1, divisionId);
            cs.setString(2, divisionName);
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
    public void deleteDivision(int divisionId) {
        try {
            CallableStatement cs = con.prepareCall("{CALL deleteDivision(?)}");
            cs.setInt(1, divisionId);
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
