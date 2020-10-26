package com.groupten.leagueobjectmodel.schedule;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class ScheduleTest {

    @Test
    public void getGameDateTest() {
        Date date = new Date();
        Schedule schedule = new Schedule(date,"Team1", "Team2");
        assertEquals(date,schedule.getGameDate());
    }

    @Test
    public void setGameDateTest() {
        Date date = new Date();
        Schedule schedule = new Schedule(date,"Team1", "Team2");
        Date date2 = new Date();
        schedule.setGameDate(date2);
        assertEquals(date2,schedule.getGameDate());
    }

    @Test
    public void getTeamName1Test() {
        Date date = new Date();
        Schedule schedule = new Schedule(date,"Team1", "Team2");
        assertEquals("Team1",schedule.getTeamName1());
    }

    @Test
    public void setTeamName1Test() {
        Date date = new Date();
        Schedule schedule = new Schedule(date,"Team1", "Team2");
        schedule.setTeamName1("Team11");
        assertEquals("Team11",schedule.getTeamName1());
    }

    @Test
    public void getTeamName2Test() {
        Date date = new Date();
        Schedule schedule = new Schedule(date,"Team1", "Team2");
        assertEquals("Team2",schedule.getTeamName2());
    }

    @Test
    public void setTeamName2Test() {
        Date date = new Date();
        Schedule schedule = new Schedule(date,"Team1", "Team2");
        schedule.setTeamName2("Team22");
        assertEquals("Team22",schedule.getTeamName2());
    }
}
