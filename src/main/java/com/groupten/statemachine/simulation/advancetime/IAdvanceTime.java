package com.groupten.statemachine.simulation.advancetime;

import com.groupten.leagueobjectmodel.season.Season;

public interface IAdvanceTime {
    void setSeason(Season season);
    void advanceTime();
}
