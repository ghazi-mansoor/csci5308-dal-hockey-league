package com.groupten.leagueobjectmodel.league;

import com.groupten.jdbc.team.Team;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.leagueobjectmodel.leaguemodelmock.LeagueModelMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeagueTest {

    @Test
    public void addTeamToLeagueModelTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();
        League league = leagueModel.getLeague("League 1");
        TeamInterface teamPersistenceAPI = new Team();

        assertTrue(league.doEntitiesExistInMemory("L1-Conference 1", "L1-C1 Division 1"));
        assertTrue(league.addTeamToLeagueModel("Team 1", "GM",
                "HC", teamPersistenceAPI));
    }

    @Test
    public void doEntitiesExistInMemoryTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();
        League league = leagueModel.getLeague("League 1");

        assertTrue(league.doEntitiesExistInMemory("L1-Conference 1", "L1-C1 Division 1"));
        assertTrue(league.doEntitiesExistInMemory("L1-Conference 2", "L1-C2 Division 1"));
        assertFalse(league.doEntitiesExistInMemory("L1-Conference 2", "L1-C1 Division 1"));
    }

    @Test
    public void addConferenceToLeagueTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();
        League league = leagueModel.getLeague("League 1");
        Conference conference = new Conference("L1-Conference 3");

        assertTrue(league.addConferenceToLeague(conference));
    }

    @Test
    public void doesContainConferenceTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();
        League league = leagueModel.getLeague("League 1");

        assertTrue(league.doesContainConference("L1-Conference 1"));
        assertFalse(league.doesContainConference("L1-Conference 3"));
    }

    @Test
    public void getLeagueNameTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();
        League league = leagueModel.getLeague("League 1");

        assertEquals("League 1", league.getLeagueName());
    }

    @Test
    public void getConferenceTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();
        League league = leagueModel.getLeague("League 1");

        assertEquals("L1-Conference 1", league.getConference("L1-Conference 1").getConferenceName());
    }

    @Test
    public void areNumberOfConferencesEvenTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();
        League league = leagueModel.getLeague("League 1");

        assertTrue(league.areNumberOfConferencesEven());
    }

}
