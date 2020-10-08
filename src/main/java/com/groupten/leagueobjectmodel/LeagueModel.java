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

    public boolean areEntitiesInMemory(String leagueName, String conferenceName, String divisionName) {
        if (doesContainLeague(leagueName)) {
            for (League league : leagues.values()) {
                if (league.doesContainConference(conferenceName)) {
                    Map<String, Conference> conferences = league.getConferences();
                    for (Conference conference : conferences.values()) {
                        return conference.doesContainDivision(divisionName);
                    }
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean doesContainLeague(String leagueName) {
        return leagues.containsKey(leagueName);
    }

}
