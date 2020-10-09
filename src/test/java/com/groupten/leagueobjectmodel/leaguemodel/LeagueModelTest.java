package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.jdbc.team.Team;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.leagueobjectmodel.leaguemodelmock.LeagueModelMock;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LeagueModelTest {

    @Test
    public void addLeagueToModelTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("League 1");
        assertTrue(leagueModel.addLeagueToModel(league));
    }

    @Test
    public void doesContainLeagueTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();

        assertTrue(leagueModel.doesContainLeague("League 1"));
        assertFalse(leagueModel.doesContainLeague("League XYZ"));
    }

    @Test
    public void getLeaguesTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();

        Map<String, League> leagues = leagueModel.getLeagues();
        assertTrue(leagueModel.getLeagues().containsKey("League 1"));
        assertFalse(leagueModel.getLeagues().containsKey("League XYZ"));
    }

    @Test
    public void getLeagueTest() {
        LeagueModelMock leagueModelMock = new LeagueModelMock();
        LeagueModel leagueModel = leagueModelMock.getLeagueModel();

        assertEquals("League 1", leagueModel.getLeague("League 1").getLeagueName());
    }

}
