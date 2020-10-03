package com.groupten.leagueobjectmodel;

public interface IinstantiateLeague {
    void addLeague(int leagueID, String leagueName);
    void addConferenceToLeague(int conferenceID, String conferenceName);
    void addDivisionToConference(int divisionID, String divisionName);
    void addTeam(String leagueName, String conferenceName, String divisionName, String teamName);
}
