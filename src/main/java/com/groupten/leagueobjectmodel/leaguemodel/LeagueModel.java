package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.validators.Validator;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;

import java.util.HashMap;
import java.util.Map;

public class LeagueModel {
    private Map<String, League> leagues;
    private League currentLeague;
    private Conference currentConference;
    private Division currentDivision;

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

    public boolean doEntitiesExistInMemory(String leagueName, String conferenceName, String divisionName) {
        if (doesContainLeague(leagueName)) {
            League league = getLeague(leagueName);
            if (league.doesContainConference(conferenceName)) {
                Conference conference = league.getConference(conferenceName);
                if (conference.doesContainDivision(divisionName)) {
                    currentDivision = conference.getDivision(divisionName);
                    currentConference = league.getConference(conferenceName);
                    currentLeague = getLeague(leagueName);

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public boolean addTeamToLeagueModel(String teamName, String generalManager, String headCoach, TeamInterface persistenceAPI) {
        Team team = new Team(teamName, generalManager, headCoach, persistenceAPI);
        return currentDivision.addTeamToDivision(team);
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