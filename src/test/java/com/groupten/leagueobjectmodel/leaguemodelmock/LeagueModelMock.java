package com.groupten.leagueobjectmodel.leaguemodelmock;

import com.groupten.jdbc.team.TeamDAO;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.jdbc.team.ITeamDAO;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;

public class LeagueModelMock {
    private LeagueModel leagueModel;

    public LeagueModelMock() {
        leagueModel = new LeagueModel();
        ITeamDAO teamPersistenceAPI = new TeamDAO();

        League league = new League("League 1");

        Conference conference1 = new Conference("L1-Conference 1");
        Conference conference2 = new Conference("L1-Conference 2");

        Division division1 = new Division("L1-C1 Division 1");
        Division division2 = new Division("L1-C1 Division 2");

        Team team1 = new Team("L1-C1-D1 Team 1", "Bob", "Marley");
        Team team2 = new Team("L1-C1-D1 Team 2", "Bob", "Marley");

        division1.addTeamToDivision(team1);
        division1.addTeamToDivision(team2);

        team1 = new Team("L1-C1-D2 Team 1", "Bob", "Marley");
        team2 = new Team("L1-C1-D2 Team 2", "Bob", "Marley");

        division2.addTeamToDivision(team1);
        division2.addTeamToDivision(team2);

        conference1.addDivisionToConference(division1);
        conference1.addDivisionToConference(division2);

        division1 = new Division("L1-C2 Division 1");
        division2 = new Division("L1-C2 Division 2");

        conference2.addDivisionToConference(division1);
        conference2.addDivisionToConference(division2);

        league.addConferenceToLeague(conference1);
        league.addConferenceToLeague(conference2);

        leagueModel.addLeagueToModel(league);
    }

    public LeagueModel getLeagueModel() {
        return leagueModel;
    }
}
