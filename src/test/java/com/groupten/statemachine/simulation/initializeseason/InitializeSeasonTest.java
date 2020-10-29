package com.groupten.statemachine.simulation.initializeseason;

import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.statemachine.simulation.generateplayoffschedule.GeneratePlayoffSchedule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class InitializeSeasonTest {
    League league = new League("League 1");
    Season season = new Season(league,2020);

    @Test
    public void getSeasonTest(){
        InitializeSeason initializeSeason = new InitializeSeason();
        initializeSeason.setSeason(season);
        assertEquals(season,initializeSeason.getSeason());
    }

    @Test
    public void setSeasonTest(){
        InitializeSeason initializeSeason = new InitializeSeason();
        initializeSeason.setSeason(season);
        assertEquals(season,initializeSeason.getSeason());
    }

    @Test
    public void generatePlayoffScheduleTest(){
        InitializeSeason initializeSeason = new InitializeSeason();
        League league = null;
        Season season2 = new Season(league,2020);
        initializeSeason.setSeason(season2);
        assertFalse(initializeSeason.generateRegularSchedule());
    }
}
