package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeagueModelTest {
    @Test
    public void addLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertTrue(leagueModel.addLeague(league));
    }

    @Test
    public void containsLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        leagueModel.addLeague(league);
        assertTrue(leagueModel.containsLeague("First League"));
    }

    @Test
    public void getLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        leagueModel.addLeague(league);
        assertEquals("First League", leagueModel.getLeague("First League").getLeagueName());
    }

    @Test
    public void getCurrentLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        leagueModel.addLeague(league);
        assertEquals("First League", leagueModel.getCurrentLeague().getLeagueName());
    }

    @Test
    public void getLeaguesTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        leagueModel.addLeague(league);
        league = new League("Second League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        leagueModel.addLeague(league);
        assertEquals(2, leagueModel.getLeagues().size());
    }

    @Test
    public void loadLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        assertTrue(leagueModel.loadLeague(1));
    }

}
