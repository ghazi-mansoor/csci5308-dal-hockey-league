package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.league.LeagueInterface;

import java.util.HashMap;
import java.util.Map;

public class League {
    private int leagueID;
    private String leagueName;
    private Map<String, Conference> conferences;
    private Map<String, Player> freeAgents;
    private LeagueInterface leaguePersistenceAPI;

    public League(String ln) {
        leagueName = ln;
        conferences = new HashMap<String, Conference>();
        freeAgents = new HashMap<String, Player>();
    }

    public League (String ln, LeagueInterface per) {
        leagueName = ln;
        leaguePersistenceAPI = per;
        conferences = new HashMap<String, Conference>();
        freeAgents = new HashMap<String, Player>();
    }

    public boolean addConferenceToLeague(Conference conference) {
        conferences.put(conference.getConferenceName(), conference);
        return conferences.containsKey(conference.getConferenceName());
    }

    public boolean addFreeAgentToLeague(Player player) {
        int numberOfFreeAgents = freeAgents.size();
        freeAgents.put(player.getPlayerName(), player);
        int numberOfFreeAgentsPostAdditions = freeAgents.size();

        return numberOfFreeAgentsPostAdditions == numberOfFreeAgents + 1;
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
        for (Player player : freeAgents.values()) {
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
        for (Player player : freeAgents.values()) {
            player.saveFreeAgentPlayerToDB();
        }
        System.out.println("Saved free agents to DB");
    }

    public String getLeagueName() {
        return leagueName;
    }


}
