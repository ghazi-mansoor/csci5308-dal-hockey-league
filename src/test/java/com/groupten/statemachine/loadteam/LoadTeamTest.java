package com.groupten.statemachine.loadteam;

import com.groupten.jdbc.team.ITeamDAO;
import com.groupten.statemachine.mocks.TeamDBMock;
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
        ITeamDAO teamDBObj = new TeamDBMock();
        LoadTeam loadTeam = new LoadTeam(teamDBObj);
        assertTrue( loadTeam.doesTeamExist());
    }
}
