package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;

public interface ILeagueModel {
    boolean loadLeague(int leagueID);
    boolean loadLeague(String teamName);
    boolean saveLeagueModel();
    League getCurrentLeague();
    boolean setCurrentLeague(League currentLeague);
}
