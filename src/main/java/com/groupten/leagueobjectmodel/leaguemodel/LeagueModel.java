package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.jdbc.league.LeagueInterface;
import com.groupten.validator.Validator;
import com.groupten.leagueobjectmodel.league.League;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueModel {
    private Map<String, League> leagues;
    private League currentLeague;
    private LeagueInterface leaguePersistenceAPI;

    public LeagueModel() {
        leagues = new HashMap<String, League>();
    }

    public LeagueModel(LeagueInterface per) {
        leagues = new HashMap<String, League>();
        leaguePersistenceAPI = per;
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

    public boolean loadLeagueFromDB(int lID) {
        // TODO: Get the league info from DB
        String leagueId = String.valueOf(lID);
        List<HashMap<String, Object>> leaguesMap = leaguePersistenceAPI.getLeagues("leagueId", leagueId);
        Map<String, Object> leagueMap = leaguesMap.get(0);

        System.out.println(leagueMap.get("leagueId"));
        System.out.println(leagueMap.get("leagueName"));

        int leagueID = (int) leagueMap.get("leagueId");
        String leagueName = (String) leagueMap.get("leagueName");

        // TODO: Use league info to create new league object
        currentLeague = new League(leagueID, leagueName, leaguePersistenceAPI);
        // TODO: Call loadConferencesFromDB on the league object

        // TODO: Return true if the returned league has the same leagueID as the one passed via the argument
        return (currentLeague.getLeagueID() == lID);
    }

}
