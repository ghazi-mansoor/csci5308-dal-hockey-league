package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;

public class LeagueModel implements ILeagueModel {
    private League currentLeague;

    @Override
    public boolean loadLeague(int leagueID) {
        // TODO: Load league, conferences, divisions, teams, and players from DB via league's ID
        return true;
    }

    @Override
    public boolean loadLeague(String teamName) {
        // TODO: Load league, conferences, divisions, teams, and players from DB via team's name
        return true;
    }

    @Override
    public boolean saveLeagueModel() {

        return true;
    }

    @Override
    public League getCurrentLeague() {
        return currentLeague;
    }

    @Override
    public boolean setCurrentLeague(League currentLeague) {
        if (League.isLeagueNameValid(currentLeague.getLeagueName())) {
            this.currentLeague = currentLeague;
            return true;
        } else {
            return false;
        }
    }
}
