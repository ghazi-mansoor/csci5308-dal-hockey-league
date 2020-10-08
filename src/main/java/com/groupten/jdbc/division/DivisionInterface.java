package com.groupten.jdbc.division;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public interface DivisionInterface {
    //CRUD
    int createDivision(int conferenceId, String divisionName);
    List<HashMap<String,Object>> getDivisions(String colName, String colValue);
    void updateDivision(int divisionId, String divisionName);
    void deleteDivision(int divisionId);

    //Relations
    List<HashMap<String,Object>> getDivisionTeams(int divisionId);
}
