package com.groupten.statemachine.simulation.advancetime;

import com.groupten.leagueobjectmodel.season.Season;

public class AdvanceTime implements IAdvanceTime {
    private Season season;

    public Season getSeason() {
        return season;
    }

    @Override
    public void setSeason(Season season){
        this.season = season;
    }

    @Override
    public void advanceTime() {
        season.advanceTime();
    }
}
