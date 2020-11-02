package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;

public interface ILeagueModel {
    boolean saveLeagueModel();
    League getCurrentLeague();
    boolean setCurrentLeague(League currentLeague);
}
