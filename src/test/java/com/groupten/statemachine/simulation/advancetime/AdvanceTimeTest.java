package com.groupten.statemachine.simulation.advancetime;

import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.season.Season;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class AdvanceTimeTest {
    League league = new League("League 1");
    Season season = new Season(league,2020);

    @Test
    public void getSeasonTest(){
        AdvanceTime advanceTime = new AdvanceTime();
        advanceTime.setSeason(season);
        assertEquals(season,advanceTime.getSeason());
    }

    @Test
    public void setSeasonTest(){
        AdvanceTime advanceTime = new AdvanceTime();
        advanceTime.setSeason(season);
        assertEquals(season,advanceTime.getSeason());
    }

    @Test
    public void advanceTimeTest(){
        AdvanceTime advanceTime = new AdvanceTime();
        advanceTime.setSeason(season);
        advanceTime.advanceTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("01/10/2020", dateFormat.format(season.getCurrentDate()));
    }

}
