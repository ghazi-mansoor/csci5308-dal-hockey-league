package com.groupten.leagueobjectmodel;

public class LeagueModel implements IinstantiateLeague {
    public League league;
    public Conference conference;
    public Division division;

    @Override
    public void addLeague(int leagueID, String leagueName) {
        this.league = new League(leagueID, leagueName);
    }

    @Override
    public void addConferenceToLeague(int conferenceID, String conferenceName) {
        int leagueID = league.getLeagueID();
        this.conference = new Conference(leagueID, conferenceID, conferenceName);
        league.addConference(conference);
    }

    @Override
    public void addDivisionToConference(int divisionID, String divisionName) {
        int leagueID = league.getLeagueID();
        int conferenceID = conference.getConferenceID();
        this.division = new Division(leagueID, conferenceID, divisionID, divisionName);
        conference.addDivision(division);
    }

    @Override
    public void addTeam(String leagueName, String conferenceName, String divisionName, String teamName) {
        // TODO 1. Create new team > 2. Add team to the current league that is in-memory via the addTeamToDivision method
    }


}
