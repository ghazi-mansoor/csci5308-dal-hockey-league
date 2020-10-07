package com.groupten.leagueobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class LeagueModel {
    private List<League> leagues;

    public LeagueModel() {
        leagues = new ArrayList<League>();
    }

    public boolean addLeagueToModel(League league) {
        int numberOfLeagues = leagues.size();
        leagues.add(league);
        int numberOfLeaguesPostAdditions = leagues.size();

        return numberOfLeaguesPostAdditions == numberOfLeagues + 1;
    }

    public void saveLeagueModelToDB() {
        for (League league : leagues) {
            league.saveLeagueToDB();
        }
    }

}
