package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.jdbc.conference.IConferenceDAO;
import com.groupten.jdbc.division.IDivisionDAO;
import com.groupten.jdbc.league.ILeagueDAO;
import com.groupten.jdbc.player.IPlayerDAO;
import com.groupten.jdbc.team.ITeamDAO;
import com.groupten.validator.Validator;
import com.groupten.leagueobjectmodel.league.League;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueModel implements ILeagueModel {
    private Map<String, League> leagues;
    private League currentLeague;
    private ILeagueDAO leaguePersistenceAPI;
    private IConferenceDAO conferencePersistenceAPI;
    private IDivisionDAO divisionPersistenceAPI;
    private ITeamDAO teamPersistenceAPI;
    private IPlayerDAO playerPersistenceAPI;

    public LeagueModel() {
        leagues = new HashMap<String, League>();
    }

    public LeagueModel(ILeagueDAO lPer, IConferenceDAO cPer, IDivisionDAO dPer, ITeamDAO tPer, IPlayerDAO pPer) {
        leagues = new HashMap<String, League>();
        leaguePersistenceAPI = lPer;
        conferencePersistenceAPI = cPer;
        divisionPersistenceAPI = dPer;
        teamPersistenceAPI = tPer;
        playerPersistenceAPI = pPer;
    }

    @Override
    public boolean addLeagueToModel(League league) {
        String leagueName = league.getLeagueName();

        if (Validator.areStringsValid(leagueName)) {
            leagues.put(league.getLeagueName(), league);
            return leagues.containsKey(league.getLeagueName());
        } else {
            return false;
        }
    }

    @Override
    public void saveLeagueModelToDB() {
        for (League league : leagues.values()) {
            league.saveLeagueToDB();
        }
    }

    @Override
    public boolean doesContainLeague(String leagueName) {
        return leagues.containsKey(leagueName);
    }

    @Override
    public Map<String, League> getLeagues() {
        return leagues;
    }

    @Override
    public League getLeague(String leagueName) {
        return leagues.get(leagueName);
    }

    @Override
    public boolean loadLeagueFromDB(int lID) {
        String leagueId = String.valueOf(lID);
        List<HashMap<String, Object>> leaguesMap = leaguePersistenceAPI.getLeagues("leagueId", leagueId);
        Map<String, Object> leagueMap = leaguesMap.get(0);

        int leagueID = (int) leagueMap.get("leagueId");
        String leagueName = (String) leagueMap.get("leagueName");

        currentLeague = new League(leagueID, leagueName, leaguePersistenceAPI, conferencePersistenceAPI, divisionPersistenceAPI, teamPersistenceAPI, playerPersistenceAPI);

        currentLeague.loadConferencesFromDB();

        return (currentLeague.getLeagueID() == lID);
    }

}
