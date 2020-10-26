package com.groupten.leagueobjectmodel.schedule;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleTest {

    @Test
    public void getGameDateTest() {
        Date date = new Date();
        Schedule schedule = new Schedule(date);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        assertEquals(dateFormat.format(date), dateFormat.format(schedule.getGameDate()));
    }

    @Test
    public void setGameDateTest() {
        Schedule schedule = new Schedule();
        Date date = new Date();
        schedule.setGameDate(date);
        assertEquals(date, schedule.getGameDate());
    }

    @Test
    public void addTeamNameTest() {
        Schedule schedule = new Schedule();
        schedule.addTeamName("Team1");
        assertTrue(schedule.getTeamNames().contains("Team1"));
    }

    @Test
    public void getTeamNamesTest() {
        Schedule schedule = new Schedule();
        schedule.addTeamName("Team1");
        schedule.addTeamName("Team2");
        schedule.addTeamName("Team3");
        assertEquals(3, schedule.getTeamNames().size());
    }
}
