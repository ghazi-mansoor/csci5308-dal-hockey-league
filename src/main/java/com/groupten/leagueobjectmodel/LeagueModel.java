package com.groupten.leagueobjectmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueModel {
    private Map<String, League> leagues;

    public LeagueModel() {
        leagues = new HashMap<String, League>();
    }

    public boolean addLeagueToModel(League league) {
        int numberOfLeagues = leagues.size();
        leagues.put(league.getLeagueName(), league);
        int numberOfLeaguesPostAdditions = leagues.size();

        return numberOfLeaguesPostAdditions == numberOfLeagues + 1;
    }

    public void saveLeagueModelToDB() {
        for (League league : leagues.values()) {
            league.saveLeagueToDB();
        }
    }

}
