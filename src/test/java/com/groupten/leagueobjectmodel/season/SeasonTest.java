package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeasonTest {

    @Test
    public void getCurrentDateTest(){
        Season season = new Season();
        Calendar currentDate = season.getCurrentDate();

        Date today = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(today);

        Calendar expected = Calendar.getInstance();
        expected.set(now.get(Calendar.YEAR), Calendar.SEPTEMBER,30);

        assertEquals(expected.get(Calendar.YEAR), currentDate.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), currentDate.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DATE), currentDate.get(Calendar.DATE));
    }

    @Test
    public void addTeamStandingTest(){
        int teamId = 1;
        TeamStanding teamStanding= new TeamStanding();

        Season season = new Season();
        assertTrue(season.addTeamStanding(teamId,teamStanding));
    }

    @Test
    public void getTeamStandingsTest(){
        Season season = new Season();
        season.addTeamStanding(1,new TeamStanding());
        season.addTeamStanding(2,new TeamStanding());
        assertEquals(2, season.getTeamStandings().size());
    }

    @Test
    public void advanceTimeTest(){
        Date today = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(today);

        Calendar expected = Calendar.getInstance();
        expected.set(now.get(Calendar.YEAR), Calendar.OCTOBER,1);

        Season season = new Season();
        season.advanceTime();
        Calendar currentDate = season.getCurrentDate();

        assertEquals(expected.get(Calendar.YEAR), currentDate.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), currentDate.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DATE), currentDate.get(Calendar.DATE));
    }

    @Test
    public void recordWinTest(){
        Season season = new Season();
        season.addTeamStanding(1,new TeamStanding());
        season.addTeamStanding(2,new TeamStanding());
        season.recordWin(1);

        assertEquals(2, season.getTeamStandings().get((Integer) 1).getPoints());
    }
}
