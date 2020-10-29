package com.groupten.statemachine.simulation.generateplayoffschedule;

import com.groupten.leagueobjectmodel.season.Season;

public class GeneratePlayoffSchedule implements IGeneratePlayoffSchedule {
    private Season season;

    @Override
    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public boolean generatePlayoffSchedule() {
        return season.generatePlayoffSchedule();
    }
}
