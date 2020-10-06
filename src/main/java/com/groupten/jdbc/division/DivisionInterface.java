package com.groupten.jdbc.division;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public interface DivisionInterface {
    int createDivision(int conferenceId, String divisionName);
    List<HashMap<String,Object>> listDivisions(int conferenceId);
    List<HashMap<String,Object>> getDivisions(int conferenceId, String colName, String colValue);
    void updateDivision(int divisionId, String divisionName);
    void deleteDivision(int divisionId);
}
