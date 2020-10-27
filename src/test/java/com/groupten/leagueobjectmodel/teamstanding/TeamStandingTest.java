package com.groupten.leagueobjectmodel.teamstanding;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamStandingTest{

    @Test
    public void getPointsTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        assertEquals(1,teamStanding.getPoints());
    }

    @Test
    public void setPointsTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        teamStanding.setPoints(11);
        assertEquals(11,teamStanding.getPoints());
    }

    @Test
    public void getLeagueRankTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        assertEquals(2,teamStanding.getLeagueRank());
    }

    @Test
    public void setLeagueRankTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        teamStanding.setLeagueRank(22);
        assertEquals(22,teamStanding.getLeagueRank());
    }

    @Test
    public void getConferenceRankTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        assertEquals(3,teamStanding.getConferenceRank());
    }

    @Test
    public void setConferenceRankTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        teamStanding.setConferenceRank(33);
        assertEquals(33,teamStanding.getConferenceRank());
    }

    @Test
    public void getDivisionRankTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        assertEquals(4,teamStanding.getDivisionRank());
    }

    @Test
    public void setDivisionRankTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        teamStanding.setDivisionRank(44);
        assertEquals(44,teamStanding.getDivisionRank());
    }

    @Test
    public void compareToTest() {
        TeamStanding teamStanding = new TeamStanding(1,2,3,4);
        TeamStanding teamStanding2 = new TeamStanding(5,6,7,8);
        assertEquals(-4,teamStanding.compareTo(teamStanding2));
    }
}
