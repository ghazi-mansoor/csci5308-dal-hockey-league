package com.groupten.leagueobjectmodel;

public class LeagueModel {
    public League league;
    public Conference conference;
    public Division division;
    public Team team;

    public void addLeague(int leagueID, String leagueName) {
        this.league = new League(leagueID, leagueName);
    }

    public void addConferenceToLeague(int conferenceID, String conferenceName) {
        int leagueID = league.getLeagueID();
        this.conference = new Conference(leagueID, conferenceID, conferenceName);
        league.addConference(conference);
    }

    public void addDivisionToConference(int divisionID, String divisionName) {
        int leagueID = league.getLeagueID();
        int conferenceID = conference.getConferenceID();
        this.division = new Division(leagueID, conferenceID, divisionID, divisionName);
        conference.addDivision(division);
    }

    public void addTeam(String leagueName, String conferenceName, String divisionName, String teamName) {
        // TODO 1. Create new team > 2. Add team to the current league that is in-memory via the addTeamToDivision method
    }

    public void saveLeagueToDB() {
        // TODO 1. Save the league to DB, while making calls for the same to its conferences, divisions, teams, and players
    }

}
