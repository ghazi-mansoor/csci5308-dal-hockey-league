package com.groupten.leagueobjectmodel;

import java.util.HashMap;
import java.util.Map;

public class LeagueModel {
    private Map<String, League> leagues;

    public LeagueModel() {
        leagues = new HashMap<String, League>();
    }

    public boolean addLeagueToModel(League league) {
        leagues.put(league.getLeagueName(), league);
        return leagues.containsKey(league.getLeagueName());
    }

    public void saveLeagueModelToDB() {
        for (League league : leagues.values()) {
            league.saveLeagueToDB();
        }
    }

}
