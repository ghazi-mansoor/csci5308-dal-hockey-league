package com.groupten.leagueobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class LeagueModel {
    private IPersistence persistenceAPI;
    private List<League> leagues;

    public LeagueModel() {
        leagues = new ArrayList<League>();
    }

    public LeagueModel(IPersistence per) {
        leagues = new ArrayList<League>();
        persistenceAPI = per;
    }

    public boolean addLeagueToModel(League league) {
        int numberOfLeagues = leagues.size();
        leagues.add(league);
        int numberOfLeaguesPostAdditions = leagues.size();

        return numberOfLeaguesPostAdditions == numberOfLeagues + 1;
    }

}
