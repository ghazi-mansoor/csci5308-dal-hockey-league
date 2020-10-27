package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeagueModelTest {
    @Test
    public void setCurrentLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("League 1");
        leagueModel.setCurrentLeague(league);
        assertEquals(league, leagueModel.getCurrentLeague());
        league = new League(" ");
        assertFalse(leagueModel.setCurrentLeague(league));
    }

    @Test
    public void loadLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        assertTrue(leagueModel.loadLeague("Team 1"));
        assertTrue(leagueModel.loadLeague(1));
    }
}
