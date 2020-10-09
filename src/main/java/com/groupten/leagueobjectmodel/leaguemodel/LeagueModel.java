package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.validator.Validator;
import com.groupten.leagueobjectmodel.league.League;

import java.util.HashMap;
import java.util.Map;

public class LeagueModel {
    private Map<String, League> leagues;
    private League currentLeague;

    public LeagueModel() {
        leagues = new HashMap<String, League>();
    }

    public boolean addLeagueToModel(League league) {
        String leagueName = league.getLeagueName();

        if (Validator.areStringsValid(leagueName)) {
            leagues.put(league.getLeagueName(), league);
            return leagues.containsKey(league.getLeagueName());
        } else {
            return false;
        }
    }

    public void saveLeagueModelToDB() {
        for (League league : leagues.values()) {
            league.saveLeagueToDB();
        }
    }

    public boolean doesContainLeague(String leagueName) {
        return leagues.containsKey(leagueName);
    }

    public Map<String, League> getLeagues() {
        return leagues;
    }

    public League getLeague(String leagueName) {
        return leagues.get(leagueName);
    }

}
