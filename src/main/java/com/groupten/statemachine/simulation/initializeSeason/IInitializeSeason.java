package com.groupten.statemachine.simulation.initializeSeason;

import com.groupten.leagueobjectmodel.season.Season;

public interface IInitializeSeason {
    Season getSeason();
    void generateRegularSchedule();
}
