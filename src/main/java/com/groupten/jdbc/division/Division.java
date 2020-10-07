package com.groupten.jdbc.division;

import com.groupten.jdbc.DatabaseConnection;
import com.groupten.jdbc.ResultSetOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public List<HashMap<String, Object>> listDivisions(int conferenceId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL listDivisions(?)}");
            cs.setInt(1, conferenceId);
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
    public List<HashMap<String, Object>> getDivisions(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try {
            CallableStatement cs = con.prepareCall("{CALL getDivisions(?,?)}");
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
