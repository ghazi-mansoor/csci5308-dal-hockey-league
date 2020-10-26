package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeasonTest {

    @Test
    public void getCurrentDateTest(){
        Season season = new Season(2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("30/09/2020", dateFormat.format(season.getCurrentDate()));
    }

    @Test
    public void getRegularSeasonStartsAtTest(){
        Season season = new Season(2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/10/2020", dateFormat.format(season.getRegularSeasonStartsAt()));
    }

    @Test
    public void getTradeEndsAtTest(){
        Season season = new Season(2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("22/02/2021", dateFormat.format(season.getTradeEndsAt()));
    }

    @Test
    public void getRegularSeasonEndsAtTest(){
        Season season = new Season(2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("03/04/2021", dateFormat.format(season.getRegularSeasonEndsAt()));
    }

    @Test
    public void getPlayoffStartsAtTest(){
        Season season = new Season(2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("14/04/2021", dateFormat.format(season.getPlayoffStartsAt()));
    }

    @Test
    public void getPlayoffEndsByTest(){
        Season season = new Season(2020);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/06/2021", dateFormat.format(season.getPlayoffEndsBy()));
    }

    @Test
    public void addTeamStandingTest(){
        String teamName = "A";
        TeamStanding teamStanding= new TeamStanding();

        Season season = new Season();
        assertTrue(season.addTeamStanding(teamName,teamStanding));
    }

    @Test
    public void getTeamStandingsTest(){
        Season season = new Season();
        season.addTeamStanding("A",new TeamStanding());
        season.addTeamStanding("B",new TeamStanding());
        assertEquals(2, season.getTeamStandings().size());
    }

    @Test
    public void advanceTimeTest(){
        Season season = new Season(2020);
        season.advanceTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/10/2020", dateFormat.format(season.getCurrentDate()));
    }

    @Test
    public void recordWinTest(){
        Season season = new Season();
        season.addTeamStanding("A",new TeamStanding());
        season.addTeamStanding("B",new TeamStanding());
        season.recordWin("A");

        assertEquals(2, season.getTeamStandings().get("A").getPoints());
    }
}
