package com.groupten.leagueobjectmodel.league;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeagueTest {
    @Test
    public void addFreeAgentTest() {
        League league = new League(1, "First League");
        Player player = new Player(1, "First Player", "goalie", false, 27, 5, 5, 5, 5);
        assertTrue(league.addFreeAgent(player));
    }

    @Test
    public void addCoachTest() {
        League league = new League(1, "First League");
        Coach coach = new Coach(1, "First Coach", 0.5, 0.5, 0.5, 0.5);
        assertTrue(league.addCoach(coach));
    }

    @Test
    public void addGeneralManagerTest() {
        League league = new League(1, "First League");
        GeneralManager generalManager = new GeneralManager(1, "First General Manager");
        assertTrue(league.addGeneralManager(generalManager));
    }

    @Test
    public void isNumberOFConferencesEvenTest() {
        League league = new League(1, "First League");
        Conference conferenceOne = new Conference(1, "First Conference");
        league.addConference(conferenceOne);
        assertFalse(league.isNumberOfConferencesEven());

        Conference conferenceTwo = new Conference(2, "Second Conference");
        league.addConference(conferenceTwo);
        assertTrue(league.isNumberOfConferencesEven());
    }

    @Test
    public void addConferenceTest() {
        League league = new League(1, "First League");
        Conference conference = new Conference(1, "First Conference");
        assertTrue(league.addConference(conference));
    }

    @Test
    public void containsConference() {
        League league = new League(1, "First League");
        Conference conference = new Conference(1, "First Conference");
        league.addConference(conference);
        assertTrue(league.containsConference("First Conference"));
    }

    @Test
    public void getConferenceTest() {
        League league = new League(1, "First League");
        Conference conference = new Conference(1, "First Conference");
        league.addConference(conference);
        assertEquals("First Conference", league.getConference("First Conference").getConferenceName());
    }

    @Test
    public void isLeagueNameValidTest() {
        String leagueName = "DHL";
        assertTrue(League.isLeagueNameValid(leagueName));
        leagueName = "";
        assertFalse(League.isLeagueNameValid(leagueName));
        leagueName = " ";
        assertFalse(League.isLeagueNameValid(leagueName));
        leagueName = "Null";
        assertFalse(League.isLeagueNameValid(leagueName));
    }

    @Test
    public void getLeagueIDTest() {
        League league = new League(1, "First League");
        assertEquals(1, league.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        League league = new League("First League");
        league.setLeagueID(1);
        assertEquals(1, league.getLeagueID());
    }

    @Test
    public void setLeagueNameTest() {
        League league = new League("First League");
        league.setLeagueName("Updated First League");
        assertEquals("Updated First League", league.getLeagueName());
    }

    @Test
    public void getLeagueNameTest() {
        League league = new League("First League");
        assertEquals("First League", league.getLeagueName());
    }

}
