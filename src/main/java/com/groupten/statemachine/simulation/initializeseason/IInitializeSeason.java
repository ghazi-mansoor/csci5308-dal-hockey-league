package com.groupten.statemachine.simulation.initializeseason;

import com.groupten.leagueobjectmodel.season.Season;

public interface IInitializeSeason {
    void setSeason(Season season);

    boolean generateRegularSchedule();
}
