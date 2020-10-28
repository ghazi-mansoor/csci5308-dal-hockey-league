package com.groupten.statemachine.simulation;

import com.groupten.leagueobjectmodel.league.League;

public interface IAging {
    void advanceEveryPlayersAge(League league, int days);
}
