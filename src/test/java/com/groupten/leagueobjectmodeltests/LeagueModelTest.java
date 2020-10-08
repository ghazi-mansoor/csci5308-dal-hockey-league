package com.groupten.leagueobjectmodeltests;

import com.groupten.jdbc.team.Team;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeagueModelTest {
    @Test
    public void addLeagueToModelTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("League 1");
        assertTrue(leagueModel.addLeagueToModel(league));
    }

    @Test
    public void addTeamToLeagueModelTest() {
        LeagueModel leagueModel = new LeagueModel();
        TeamInterface teamPersistenceAPI = new Team();

        League league = new League("League 1");

        Conference conference1 = new Conference("L1-Conference 1");
        Conference conference2 = new Conference("L1-Conference 2");

        Division division1 = new Division("L1-C1 Division 1");
        Division division2 = new Division("L1-C1 Division 2");

        conference1.addDivisionToConference(division1);
        conference1.addDivisionToConference(division2);

        division1 = new Division("L1-C2 Division 1");
        division2 = new Division("L1-C2 Division 2");

        conference2.addDivisionToConference(division1);
        conference2.addDivisionToConference(division2);

        league.addConferenceToLeague(conference1);
        league.addConferenceToLeague(conference2);

        leagueModel.addLeagueToModel(league);

        assertTrue(leagueModel.doEntitiesExistInMemory("League 1", "L1-Conference 1", "L1-C1 Division 1"));
        assertTrue(leagueModel.addTeamToLeagueModel("Team 1", "GM",
                "HC", teamPersistenceAPI));
    }

    @Test
    public void doEntitiesExistInMemoryTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("League 1");

        Conference conference1 = new Conference("L1-Conference 1");
        Conference conference2 = new Conference("L1-Conference 2");

        Division division1 = new Division("L1-C1 Division 1");
        Division division2 = new Division("L1-C1 Division 2");

        conference1.addDivisionToConference(division1);
        conference1.addDivisionToConference(division2);

        division1 = new Division("L1-C2 Division 1");
        division2 = new Division("L1-C2 Division 2");

        conference2.addDivisionToConference(division1);
        conference2.addDivisionToConference(division2);

        league.addConferenceToLeague(conference1);
        league.addConferenceToLeague(conference2);

        leagueModel.addLeagueToModel(league);

        assertTrue(leagueModel.doEntitiesExistInMemory("League 1", "L1-Conference 1", "L1-C1 Division 1"));
        assertTrue(leagueModel.doEntitiesExistInMemory("League 1", "L1-Conference 2", "L1-C2 Division 1"));
        assertFalse(leagueModel.doEntitiesExistInMemory("League 1", "L1-Conference 2", "L1-C1 Division 1"));
    }
}
