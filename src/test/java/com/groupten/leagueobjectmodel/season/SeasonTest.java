package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SeasonTest {

    @Test
    public void getCurrentDateTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("30/09/2020", dateFormat.format(season.getCurrentDate()));
    }

    @Test
    public void getRegularSeasonStartsAtTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/10/2020", dateFormat.format(season.getRegularSeasonStartsAt()));
    }

    @Test
    public void getRegularSeasonEndsAtTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("03/04/2021", dateFormat.format(season.getRegularSeasonEndsAt()));
    }

    @Test
    public void getTradeEndsAtTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("22/02/2021", dateFormat.format(season.getTradeEndsAt()));
    }

    @Test
    public void getPlayoffStartsAtTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("14/04/2021", dateFormat.format(season.getPlayoffStartsAt()));
    }

    @Test
    public void getPlayoffEndsByTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/06/2021", dateFormat.format(season.getPlayoffEndsBy()));
    }

    @Test
    public void addTeamStandingTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        season.addTeamStanding(new TeamStanding(new Team("Team1"),"Division1","Conference1",1,2,3,4));
        assertEquals(1, season.getTeamStandings().size());
    }

    @Test
    public void getTeamStandingsTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        season.addTeamStanding(new TeamStanding(new Team("Team 1"),"Division1","Conference1",1,2,3,4));
        season.addTeamStanding(new TeamStanding(new Team("Team 2"),"Division1","Conference1",1,2,3,4));
        assertEquals(2, season.getTeamStandings().size());
    }

    @Test
    public void advanceTimeTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        season.advanceTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/10/2020", dateFormat.format(season.getCurrentDate()));
    }

    @Test
    public void recordWinTest(){
        Team team = new Team("Team1");
        League league = null;
        Season season = new Season(league,2020);
        TeamStanding teamStanding = new TeamStanding(team,"Division1","Conference1",1,2,3,4);;
        season.addTeamStanding(teamStanding);
        season.recordWin(team);

        assertEquals(3, teamStanding.getPoints());
    }

    @Test
    public void setCurrentDateTest() {
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            season.setCurrentDate(dateFormat.parse("10/09/2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals("10/09/2020", dateFormat.format(season.getCurrentDate().getTime()));
    }

    @Test
    public void isTodayRegularSeasonEndTest(){
        League league = null;
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = dateFormat.parse("03/04/2021");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        season.setCurrentDate(d);
        assertTrue(season.isTodayRegularSeasonEnd());
    }

    @Test
    public void schedulesTodayTest(){
        League league = null;
        Season season = new Season(league,2020);
        assertEquals(0,season.schedulesToday().size());
    }

    @Test
    public void isTradeEndedTest(){
        League league = null;
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = dateFormat.parse("20/02/2021");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        season.setCurrentDate(d);
        assertFalse(season.isTradeEnded());

        try {
            d = dateFormat.parse("23/02/2021");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        season.setCurrentDate(d);
        assertTrue(season.isTradeEnded());
    }

    @Test
    public void isWinnerDetermined(){
        League league = null;
        Season season = new Season(league,2020);
        assertTrue(season.isWinnerDetermined());
    }

    @Test
    public void generateRegularScheduleTest(){
        League league = null;
        Season season = new Season(league,2020);
        assertFalse(season.generateRegularSchedule());
    }

    @Test
    public void generatePlayoffScheduleTest(){
        League league = null;
        Season season = new Season(league,2020);
        assertFalse(season.generatePlayoffSchedule());
    }
}
