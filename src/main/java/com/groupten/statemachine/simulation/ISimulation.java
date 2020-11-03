package com.groupten.statemachine.simulation;

import com.groupten.leagueobjectmodel.league.League;

public interface ISimulation {
    void init(League leagueLOM, int numberOfSeasons);
}
