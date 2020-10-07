package com.groupten.statemachine.loadteam;

import com.groupten.jdbc.league.LeagueInterface;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.jdbcmock.league.LeagueDBMock;
import com.groupten.jdbcmock.league.TeamDBMock;
import com.groupten.statemachine.json.JSON;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoadTeamTest {

    @Test
    public void validateUserInputTest(){
        LoadTeam loadTeam = new LoadTeam();
        loadTeam.setTeamName("Iron Man Team");
        assertTrue(loadTeam.validateUserInput());
    }

    @Test
    public void isLeagueNameUniqueTest(){
        TeamInterface teamDBObj = new TeamDBMock();
        LoadTeam loadTeam = new LoadTeam(teamDBObj);
        assertTrue( loadTeam.doesTeamExist());
    }

}
