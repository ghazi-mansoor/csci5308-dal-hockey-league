package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.league.League;

import java.util.HashMap;
import java.util.Map;

public class LeagueModel implements ILeagueModel {
    private final Map<String, League> leagues;

    public LeagueModel() {
        leagues = new HashMap<>();
    }

    @Override
    public boolean addLeague(League league) {
        String leagueName = league.getLeagueName();
        int initialSize = leagues.size();
        leagues.put(leagueName, league);

        return leagues.size() > initialSize;
    }

    @Override
    public boolean containsLeague(String leagueName) {
        return leagues.containsKey(leagueName);
    }

    @Override
    public League getLeague(String leagueName) {
        return leagues.get(leagueName);
    }

}
