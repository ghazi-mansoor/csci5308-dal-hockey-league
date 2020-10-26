package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueModel implements ILeagueModel {
    private final Map<String, League> leagues;

    public LeagueModel() {
        leagues = new HashMap<>();
    }

    @Override
    public boolean addLeague(League league) {
        if (League.isLeagueNameValid(league.getLeagueName())) {
            String leagueName = league.getLeagueName();
            int initialSize = leagues.size();
            leagues.put(leagueName, league);

            return leagues.size() > initialSize;
        }else{
            return false;
        }
    }

    @Override
    public boolean containsLeague(String leagueName) {
        return leagues.containsKey(leagueName);
    }

    @Override
    public boolean loadLeague(int leagueID) { return true; }

    @Override
    public void saveLeagueModel() {
        // Save league model via persistence API(s)
    }

    @Override
    public League getLeague(String leagueName) {
        return leagues.get(leagueName);
    }

    @Override
    public League getCurrentLeague() {
        return (League) leagues.values().toArray()[0];
    }

    public Map<String, League> getLeagues() {
        return leagues;
    }

}
