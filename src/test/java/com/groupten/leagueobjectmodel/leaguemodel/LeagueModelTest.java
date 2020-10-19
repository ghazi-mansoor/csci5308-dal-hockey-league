package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeagueModelTest {
    @Test
    public void addLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("First League");
        assertTrue(leagueModel.addLeague(league));
    }

    @Test
    public void containsLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League(1, "First League");
        leagueModel.addLeague(league);
        assertTrue(leagueModel.containsLeague("First League"));
    }

    @Test
    public void getLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("First League");
        leagueModel.addLeague(league);
        assertEquals("First League", leagueModel.getLeague("First League").getLeagueName());

    }

}
