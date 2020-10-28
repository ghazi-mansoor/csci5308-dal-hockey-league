package com.groupten.leagueobjectmodel.teamstanding;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamStandingTest{
    @Test
    public void getTeamNameTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        assertEquals("Team1",teamStanding.getTeamName());
    }

    @Test
    public void setTeamNameTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        teamStanding.setTeamName("Team11");
        assertEquals("Team11",teamStanding.getTeamName());
    }

    @Test
    public void getPointsTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        assertEquals(1,teamStanding.getPoints());
    }

    @Test
    public void setPointsTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        teamStanding.setPoints(11);
        assertEquals(11,teamStanding.getPoints());
    }

    @Test
    public void getLeagueRankTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        assertEquals(2,teamStanding.getLeagueRank());
    }

    @Test
    public void setLeagueRankTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        teamStanding.setLeagueRank(22);
        assertEquals(22,teamStanding.getLeagueRank());
    }

    @Test
    public void getConferenceRankTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        assertEquals(3,teamStanding.getConferenceRank());
    }

    @Test
    public void setConferenceRankTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        teamStanding.setConferenceRank(33);
        assertEquals(33,teamStanding.getConferenceRank());
    }

    @Test
    public void getDivisionRankTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        assertEquals(4,teamStanding.getDivisionRank());
    }

    @Test
    public void setDivisionRankTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        teamStanding.setDivisionRank(44);
        assertEquals(44,teamStanding.getDivisionRank());
    }

    @Test
    public void addWinTest(){
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        teamStanding.addWin();
        assertEquals(3,teamStanding.getPoints());
        assertEquals(1,teamStanding.getWins());
    }

    @Test
    public void addLossTest(){
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        teamStanding.addLoss();
        assertEquals(1,teamStanding.getPoints());
        assertEquals(1,teamStanding.getLosses());
    }

    @Test
    public void compareToTest() {
        TeamStanding teamStanding = new TeamStanding("Team1",1,2,3,4,0,0);
        TeamStanding teamStanding2 = new TeamStanding("Team1",5,6,7,8,0,0);
        assertEquals(-4,teamStanding.compareTo(teamStanding2));
    }
}
