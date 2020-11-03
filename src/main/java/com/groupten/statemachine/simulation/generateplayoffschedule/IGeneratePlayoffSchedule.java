package com.groupten.statemachine.simulation.generateplayoffschedule;

import com.groupten.leagueobjectmodel.season.Season;

public interface IGeneratePlayoffSchedule {
    void setSeason(Season season);
    boolean generatePlayoffSchedule();
}
