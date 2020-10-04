package com.groupten.leagueobjectmodel;

public interface IinstantiateLeague {
    void addLeague(int leagueID, String leagueName);

    void addConferenceToLeague(League league, int conferenceID, String conferenceName);
    void addConference(int leagueID, String leagueName, int conferenceID, String conferenceName);

    void addDivision(int divisionID, String divisionName);
    void addDivision(int leagueID, String leagueName, int conferenceID, String conferenceName, int divisionID, String divisionName);

    void addTeam(int teamID, String teamName, String generalManager, String headCoach);
    void addTeam(String leagueName, String conferenceName, String divisionName, String teamName, String generalManager, String headCoach);
    void addTeam(int leagueID, String leagueName, int conferenceID, String conferenceName, int divisionID, String divisionName, int teamID, String teamName, String generalManager, String headCoach);
}
