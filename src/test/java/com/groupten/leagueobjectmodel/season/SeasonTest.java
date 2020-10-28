package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public void getTradeEndsAtTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("22/02/2021", dateFormat.format(season.getTradeEndsAt()));
    }

    @Test
    public void getRegularSeasonEndsAtTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("03/04/2021", dateFormat.format(season.getRegularSeasonEndsAt()));
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
        season.addTeamStanding(new TeamStanding("Team1","Division1","Conference1",1,2,3,4,0,0));
        assertEquals(1, season.getTeamStandings().size());
    }

    @Test
    public void getTeamStandingsTest(){
        League league = new League("League 1");
        Season season = new Season(league,2020);
        season.addTeamStanding(new TeamStanding("Team1","Division1","Conference1",1,2,3,4,0,0));
        season.addTeamStanding(new TeamStanding("Team2","Division1","Conference1",1,2,3,4,0,0));
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
        League league = null;
        Season season = new Season(league,2020);
        TeamStanding Team1 = new TeamStanding("Team1","Division1","Conference1",1,2,3,4,0,0);
        TeamStanding Team2 = new TeamStanding("Team2","Division1","Conference1",1,2,3,4,0,0);
        season.addTeamStanding(Team1);
        season.addTeamStanding(Team2);
        season.recordWin("Team1");

        assertEquals(3, Team1.getPoints());
    }

    @Test
    public void generateRegularScheduleTest(){
        League league = null;
        Season season = new Season(league,2020);
        assertFalse(season.generateRegularSchedule());
    }
}
