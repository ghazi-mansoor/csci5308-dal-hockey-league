package com.groupten.leagueobjectmodel.division;

import com.groupten.leagueobjectmodel.team.Team;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DivisionTest {

    @Test
    public void addTeamToDivisionTest() {
        Division division = new Division("Division 1");
        Team team1 = new Team("Team 1", "Bob", "Marley");

        assertTrue(division.addTeamToDivision(team1));
    }

    @Test
    public void doesTeamExistInMemoryTest() {
        Division division = new Division("Division 1");
        Team team1 = new Team("Team 1", "Bob", "Marley");
        division.addTeamToDivision(team1);

        assertTrue(division.doesTeamExistInMemory("Team 1"));
        assertFalse(division.doesTeamExistInMemory("Team 2"));
    }

}
