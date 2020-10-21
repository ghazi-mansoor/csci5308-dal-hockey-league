package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;

public interface ILeagueModel {
    boolean addLeague(League league);
    boolean containsLeague(String leagueName);
    League getLeague(String leagueName);
}
