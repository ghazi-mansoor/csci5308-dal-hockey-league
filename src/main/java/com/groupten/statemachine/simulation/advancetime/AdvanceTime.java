package com.groupten.statemachine.simulation.advancetime;

import com.groupten.leagueobjectmodel.season.Season;

public class AdvanceTime implements IAdvanceTime {
    private final Season season;

    public AdvanceTime(Season season){
        this.season = season;
    }
    @Override
    public void advanceTime() {
        season.advanceTime();
    }
}
