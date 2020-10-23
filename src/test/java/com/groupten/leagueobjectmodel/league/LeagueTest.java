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
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Player player = new Player("First Player", "goalie", 27, 5, 5, 5, 5);
        player.setPlayerID(1);
        assertTrue(league.addFreeAgent(player));
        player = new Player(2, "Second Player", "goalie", 27, 5, 5, 5, 5);
        assertTrue(league.addFreeAgent(player));
    }

    @Test
    public void addCoachTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Coach coach = new Coach(1, "First Coach", 0.5, 0.5, 0.5, 0.5);
        assertTrue(league.addCoach(coach));
    }

    @Test
    public void addGeneralManagerTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        GeneralManager generalManager = new GeneralManager(1, "First General Manager");
        assertTrue(league.addGeneralManager(generalManager));
    }

    @Test
    public void isNumberOFConferencesEvenTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Conference conferenceOne = new Conference(1, "First Conference");
        league.addConference(conferenceOne);
        assertFalse(league.isNumberOfConferencesEven());

        Conference conferenceTwo = new Conference(2, "Second Conference");
        league.addConference(conferenceTwo);
        assertTrue(league.isNumberOfConferencesEven());
    }

    @Test
    public void addConferenceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Conference conference = new Conference(1, "First Conference");
        assertTrue(league.addConference(conference));
    }

    @Test
    public void containsConference() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Conference conference = new Conference(1, "First Conference");
        league.addConference(conference);
        assertTrue(league.containsConference("First Conference"));
    }

    @Test
    public void getConferenceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Conference conference = new Conference(1, "First Conference");
        league.addConference(conference);
        assertEquals("First Conference", league.getConference("First Conference").getConferenceName());
    }

    @Test
    public void getConferencesTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Conference conference = new Conference(1, "First Conference");
        league.addConference(conference);
        conference = new Conference(2, "Second Conference");
        league.addConference(conference);
        assertEquals(2, league.getConferences().size());
    }

    @Test
    public void getCoachesTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Coach coach = new Coach(1, "First Coach", 0.5, 0.5, 0.5, 0.5);
        league.addCoach(coach);
        coach = new Coach(2, "Second Coach", 0.5, 0.5, 0.5, 0.5);
        league.addCoach(coach);
        assertEquals(2, league.getCoaches().size());
    }

    @Test
    public void getFreeAgentsTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        Player player = new Player(1,"First Player", "goalie", 27, 5, 5, 5, 5);
        league.addFreeAgent(player);
        player = new Player(2, "Second Player", "goalie", 27, 5, 5, 5, 5);
        league.addFreeAgent(player);
        assertEquals(2, league.getFreeAgents().size());
    }

    @Test
    public void getGeneralManagersTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        GeneralManager generalManager = new GeneralManager(1, "First General Manager");
        league.addGeneralManager(generalManager);
        generalManager = new GeneralManager(2, "Second General Manager");
        league.addGeneralManager(generalManager);
        assertEquals(2, league.getGeneralManagers().size());
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
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(1, league.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setLeagueID(1);
        assertEquals(1, league.getLeagueID());
    }

    @Test
    public void setLeagueNameTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setLeagueName("Updated First League");
        assertEquals("Updated First League", league.getLeagueName());
    }

    @Test
    public void getLeagueNameTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals("First League", league.getLeagueName());
    }

    @Test
    public void getAverageRetirementAgeTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(35.0, league.getAverageRetirementAge(), 0.0);
    }

    @Test
    public void setAverageRetirementAgeTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setAverageRetirementAge(40);
        assertEquals(40.0, league.getAverageRetirementAge(), 0.0);
    }

    @Test
    public void getMaximumAgeTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(50.0, league.getMaximumAge(), 0.0);
    }

    @Test
    public void setMaximumAgeTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setMaximumAge(40);
        assertEquals(40.0, league.getMaximumAge(), 0.0);
    }

    @Test
    public void getRandomWinChance() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(0.1, league.getRandomWinChance(), 0.0);
    }

    @Test
    public void setRandomWinChanceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setRandomWinChance(0.2);
        assertEquals(0.2, league.getRandomWinChance(), 0.0);
    }

    @Test
    public void getRandomInjuryChanceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(0.05, league.getRandomInjuryChance(), 0.0);
    }

    @Test
    public void setRandomInjuryChanceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setRandomInjuryChance(0.07);
        assertEquals(0.07, league.getRandomInjuryChance(), 0.0);
    }

    @Test
    public void getInjuryDaysLowTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(1, league.getInjuryDaysLow(), 0.0);
    }

    @Test
    public void setInjuryDaysLowTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setInjuryDaysLow(2);
        assertEquals(2, league.getInjuryDaysLow(), 0.0);
    }

    @Test
    public void getInjuryDaysHighTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(260, league.getInjuryDaysHigh(), 0.0);
    }

    @Test
    public void setInjuryDaysHighTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setInjuryDaysHigh(270);
        assertEquals(270, league.getInjuryDaysHigh(), 0.0);
    }

    @Test
    public void getDaysUntilStatIncreaseCheckTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(100, league.getDaysUntilStatIncreaseCheck(), 0.0);
    }

    @Test
    public void setDaysUntilStatIncreaseCheckTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setDaysUntilStatIncreaseCheck(120);
        assertEquals(120, league.getDaysUntilStatIncreaseCheck(), 0.0);
    }

    @Test
    public void getLossPointTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(8, league.getLossPoint(), 0.0);
    }

    @Test
    public void setLossPointTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setLossPoint(10);
        assertEquals(10, league.getLossPoint(), 0.0);
    }

    @Test
    public void getRandomTradeOfferChanceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(0.05, league.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void setRandomTradeOfferChanceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setRandomTradeOfferChance(0.07);
        assertEquals(0.07, league.getRandomTradeOfferChance(), 0.0);
    }

    @Test
    public void getRandomAcceptanceChanceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(0.05, league.getRandomAcceptanceChance(), 0.0);
    }

    @Test
    public void setRandomAcceptanceChanceTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setRandomAcceptanceChance(0.07);
        assertEquals(0.07, league.getRandomAcceptanceChance(), 0.0);
    }

    @Test
    public void getMaxPlayersPerTradeTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        assertEquals(2, league.getMaxPlayersPerTrade());
    }

    @Test
    public void setMaxPlayersPerTradeTest() {
        League league = new League(1, "First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);
        league.setMaxPlayersPerTrade(3);
        assertEquals(3, league.getMaxPlayersPerTrade());
    }
}
