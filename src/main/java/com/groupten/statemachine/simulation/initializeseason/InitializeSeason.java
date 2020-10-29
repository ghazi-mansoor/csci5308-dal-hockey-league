package com.groupten.statemachine.simulation.initializeseason;

import com.groupten.leagueobjectmodel.season.Season;

public class InitializeSeason implements IInitializeSeason{
    private Season season;

    @Override
    public void setSeason(Season season){
        this.season = season;
    }

    @Override
    public boolean generateRegularSchedule() {
        return season.generateRegularSchedule();
    }
}
