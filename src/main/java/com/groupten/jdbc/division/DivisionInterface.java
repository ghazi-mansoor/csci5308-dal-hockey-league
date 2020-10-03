package com.groupten.jdbc.division;

import java.sql.ResultSet;

public interface DivisionInterface {
    int createDivision(int conferenceId, String divisionName);
    ResultSet listDivisions(int conferenceId);
    ResultSet getDivisions(int conferenceId, String colName, String colValue);
    void updateDivision(int divisionId, String divisionName);
    void deleteDivision(int divisionId);
}
