package com.groupten.createteam;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CreateTeamTest {

    @Test
    public void validateUserInputTest(){
        CreateTeam createTeam = new CreateTeam();
        createTeam.setConferenceName("XYZ");
        createTeam.setDivisionName("ABC");
        createTeam.setGeneralManager("PQR");
        createTeam.setHeadCoach("DEF");
        createTeam.setTeamName("LMO");
        assertTrue(createTeam.validateUserInput());
    }

}
