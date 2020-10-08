package com.groupten.leagueobjectmodel.league;

import com.groupten.jdbc.league.LeagueInterface;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.validators.Validator;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {
    private int leagueID;
    private String leagueName;
    private Map<String, Conference> conferences;
    private List<Player> freeAgents;
    private LeagueInterface leaguePersistenceAPI;

    private Conference currentConference;
    private Division currentDivision;

    public League(String ln) {
        leagueName = ln;
        conferences = new HashMap<String, Conference>();
        freeAgents = new ArrayList<Player>();
    }

    public League (String ln, LeagueInterface per) {
        leagueName = ln;
        leaguePersistenceAPI = per;
        conferences = new HashMap<String, Conference>();
        freeAgents = new ArrayList<Player>();
    }

    public boolean addConferenceToLeague(Conference conference) {
        String conferenceName = conference.getConferenceName();
        if (Validator.areStringsValid(conferenceName)) {
            conferences.put(conference.getConferenceName(), conference);
            return conferences.containsKey(conference.getConferenceName());
        } else {
            return false;
        }
    }

    public boolean addFreeAgentToLeague(Player player) {
        String playerName = player.getPlayerName();
        String playerPosition = player.getPosition();

        if (Validator.areStringsValid(playerName) && Validator.isPositionValid(playerPosition)) {
            int numberOfFreeAgents = freeAgents.size();
            freeAgents.add(player);
            int numberOfFreeAgentsPostAdditions = freeAgents.size();

            return numberOfFreeAgentsPostAdditions == numberOfFreeAgents + 1;
        } else {
            return false;
        }
    }

    public void saveLeagueToDB() {
        leagueID = leaguePersistenceAPI.createLeague(leagueName);
        setConferenceForeignKeys();
        saveAllConferences();
        setFreeAgentForeignKeys();
        saveAllFreeAgents();
    }

    private void setFreeAgentForeignKeys() {
        for (Player player : freeAgents) {
            player.setLeagueID(leagueID);
        }
    }

    private void setConferenceForeignKeys() {
        for (Conference conference : conferences.values()) {
            conference.setLeagueID(leagueID);
        }
    }

    private void saveAllConferences() {
        for (Conference conference : conferences.values()) {
            conference.saveConferenceToDB();
        }
    }

    private void saveAllFreeAgents() {
        for (Player player : freeAgents) {
            player.saveFreeAgentPlayerToDB();
        }
    }

    public boolean doEntitiesExistInMemory(String conferenceName, String divisionName) {
        if (doesContainConference(conferenceName)) {
            Conference conference = getConference(conferenceName);
            if (conference.doesContainDivision(divisionName)) {
                currentDivision = conference.getDivision(divisionName);
                currentConference = getConference(conferenceName);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean addTeamToLeagueModel(String teamName, String generalManager, String headCoach, TeamInterface persistenceAPI) {
        Team team = new Team(teamName, generalManager, headCoach, persistenceAPI);
        return currentDivision.addTeamToDivision(team);
    }

    public boolean doesContainConference(String conferenceName) {
        return conferences.containsKey(conferenceName);
    }

    public String getLeagueName() {
        return leagueName;
    }

    public Map<String, Conference> getConferences() {
        return conferences;
    }

    public Conference getConference(String conferenceName) {
        return conferences.get(conferenceName);
    }


}