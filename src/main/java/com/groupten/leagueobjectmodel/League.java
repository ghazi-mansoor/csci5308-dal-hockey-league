package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.league.LeagueInterface;

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
        System.out.println("League saved to DB");
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
        System.out.println("Saved free agents to DB");
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
