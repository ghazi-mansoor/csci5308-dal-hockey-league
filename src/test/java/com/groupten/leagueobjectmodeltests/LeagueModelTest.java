package com.groupten.leagueobjectmodeltests;

import com.groupten.leagueobjectmodel.League;
import com.groupten.leagueobjectmodel.LeagueModel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LeagueModelTest {
    @Test
    public void addLeagueToModelTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("League 1");
        assertTrue(leagueModel.addLeagueToModel(league));
    }
}
