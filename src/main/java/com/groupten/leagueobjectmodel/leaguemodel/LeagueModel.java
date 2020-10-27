package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

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
    public void saveLeague() {
        // TODO: Save current league, conferences, divisions, teams, and players to DB
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
