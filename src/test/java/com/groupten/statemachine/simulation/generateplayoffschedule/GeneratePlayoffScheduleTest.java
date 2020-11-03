package com.groupten.statemachine.simulation.generateplayoffschedule;

import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.season.Season;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GeneratePlayoffScheduleTest {
    League league = new League("League 1");
    Season season = new Season(league,2020);

    @Test
    public void getSeasonTest(){
        GeneratePlayoffSchedule generatePlayoffSchedule = new GeneratePlayoffSchedule();
        generatePlayoffSchedule.setSeason(season);
        assertEquals(season,generatePlayoffSchedule.getSeason());
    }

    @Test
    public void setSeasonTest(){
        GeneratePlayoffSchedule generatePlayoffSchedule = new GeneratePlayoffSchedule();
        generatePlayoffSchedule.setSeason(season);
        assertEquals(season,generatePlayoffSchedule.getSeason());
    }

    @Test
    public void generatePlayoffScheduleTest(){
        GeneratePlayoffSchedule generatePlayoffSchedule = new GeneratePlayoffSchedule();
        League league = null;
        Season season2 = new Season(league,2020);
        generatePlayoffSchedule.setSeason(season2);
        assertFalse(generatePlayoffSchedule.generatePlayoffSchedule());
    }
}
