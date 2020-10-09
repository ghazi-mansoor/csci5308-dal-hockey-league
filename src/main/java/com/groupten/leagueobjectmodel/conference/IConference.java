package com.groupten.leagueobjectmodel.conference;

import com.groupten.leagueobjectmodel.division.Division;

public interface IConference {
    boolean addDivisionToConference(Division division);
    boolean saveConferenceToDB();
    boolean doesContainDivision(String divisionName);
    boolean areNumberOfDivisionsEven();
    void loadDivisionFromDB();
}
