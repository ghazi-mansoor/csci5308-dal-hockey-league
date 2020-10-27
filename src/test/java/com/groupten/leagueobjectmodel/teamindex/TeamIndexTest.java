package com.groupten.leagueobjectmodel.teamindex;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamIndexTest {

    @Test
    public void getLeagueNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        assertEquals("League1",teamIndex.getLeagueName());
    }

    @Test
    public void setLeagueNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        teamIndex.setLeagueName("League2");
        assertEquals("League2",teamIndex.getLeagueName());
    }

    @Test
    public void getConferenceNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        assertEquals("Conference1",teamIndex.getConferenceName());
    }

    @Test
    public void setConferenceNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        teamIndex.setConferenceName("Conference2");
        assertEquals("Conference2",teamIndex.getConferenceName());
    }

    @Test
    public void getDivisionNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        assertEquals("Division1",teamIndex.getDivisionName());
    }

    @Test
    public void setDivisionNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        teamIndex.setDivisionName("Division2");
        assertEquals("Division2",teamIndex.getDivisionName());
    }

    public void getTeamNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        assertEquals("Team1",teamIndex.getTeamName());
    }

    @Test
    public void setTeamNameTest() {
        TeamIndex teamIndex =
                new TeamIndex("League1","Conference1","Division1","Team1");
        teamIndex.setTeamName("Team1");
        assertEquals("Team1",teamIndex.getTeamName());
    }
}
