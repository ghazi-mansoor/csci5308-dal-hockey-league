package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;

public interface ILeagueModel {
    boolean loadLeagueModel(int leagueID);
    boolean saveLeagueModel();
    League getCurrentLeague();
    boolean setCurrentLeague(League currentLeague);
    int getTotalNumberOfTeams();
}
