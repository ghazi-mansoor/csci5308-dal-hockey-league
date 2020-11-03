package com.groupten.statemachine.simulation.simulategame;

import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.season.Season;

public interface ISimulateGame {
    void setSeason(Season season);
    void simulateGame(Schedule schedule);
}
