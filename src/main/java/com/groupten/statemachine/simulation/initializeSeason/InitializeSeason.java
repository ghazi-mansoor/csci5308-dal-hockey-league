package com.groupten.statemachine.simulation.initializeSeason;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.season.Season;

public class InitializeSeason implements IInitializeSeason{
    private final Season season;

    public InitializeSeason(){
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League leagueLOM = leagueModel.getCurrentLeague();
        season = new Season(leagueLOM);
    }

    @Override
    public Season getSeason() {
       return season;
    }

    @Override
    public void generateRegularSchedule() {
        season.generateRegularSchedule();
    }
}
