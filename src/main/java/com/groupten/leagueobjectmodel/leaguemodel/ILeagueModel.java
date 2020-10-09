package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;

import java.util.Map;

public interface ILeagueModel {
    boolean addLeagueToModel(League league);
    void saveLeagueModelToDB();
    boolean doesContainLeague(String leagueName);
    Map<String, League> getLeagues();
    League getLeague(String leagueName);
    boolean loadLeagueFromDB(int lID);
}
