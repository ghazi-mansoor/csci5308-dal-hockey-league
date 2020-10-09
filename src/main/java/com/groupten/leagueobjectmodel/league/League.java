package com.groupten.leagueobjectmodel.league;

import com.groupten.jdbc.conference.IConferenceDAO;
import com.groupten.jdbc.division.IDivisionDAO;
import com.groupten.jdbc.league.ILeagueDAO;
import com.groupten.jdbc.player.IPlayerDAO;
import com.groupten.jdbc.team.ITeamDAO;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.validator.Validator;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League implements ILeague {
    private int leagueID;
    private String leagueName;
    private Map<String, Conference> conferences;
    private List<Player> freeAgents;
    private ILeagueDAO leaguePersistenceAPI;
    private IConferenceDAO conferencePersistenceAPI;
    private IDivisionDAO divisionPersistenceAPI;
    private ITeamDAO teamPersistenceAPI;
    private IPlayerDAO playerPersistenceAPI;

    private Conference currentConference;
    private Division currentDivision;

    public League(String ln) {
        leagueName = ln;
        conferences = new HashMap<String, Conference>();
        freeAgents = new ArrayList<Player>();
    }

    public League(String ln, ILeagueDAO per) {
        leagueName = ln;
        leaguePersistenceAPI = per;
        conferences = new HashMap<String, Conference>();
        freeAgents = new ArrayList<Player>();
    }

    public League(int lID, String ln, ILeagueDAO lPer, IConferenceDAO cPer, IDivisionDAO dPer, ITeamDAO tPer, IPlayerDAO pPer) {
        leagueID = lID;
        leagueName = ln;
        conferences = new HashMap<String, Conference>();
        freeAgents = new ArrayList<Player>();
        leaguePersistenceAPI = lPer;
        conferencePersistenceAPI = cPer;
        divisionPersistenceAPI = dPer;
        teamPersistenceAPI = tPer;
        playerPersistenceAPI = pPer;

    }

    @Override
    public boolean addConferenceToLeague(Conference conference) {
        String conferenceName = conference.getConferenceName();
        if (Validator.areStringsValid(conferenceName)) {
            conferences.put(conference.getConferenceName(), conference);
            return conferences.containsKey(conference.getConferenceName());
        } else {
            return false;
        }
    }

    @Override
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

    @Override
    public boolean saveLeagueToDB() {
        leagueID = leaguePersistenceAPI.createLeague(leagueName);
        setConferenceForeignKeys();
        saveAllConferences();
        setFreeAgentForeignKeys();
        saveAllFreeAgents();
        return (leagueID != 0);
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

    @Override
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

    @Override
    public boolean addTeamToLeagueModel(String teamName, String generalManager, String headCoach, ITeamDAO persistenceAPI) {
        Team team = new Team(teamName, generalManager, headCoach, persistenceAPI);
        return currentDivision.addTeamToDivision(team);
    }

    @Override
    public boolean doesContainConference(String conferenceName) {
        return conferences.containsKey(conferenceName);
    }

    @Override
    public String getLeagueName() {
        return leagueName;
    }

    public Map<String, Conference> getConferences() {
        return conferences;
    }

    @Override
    public Conference getConference(String conferenceName) {
        return conferences.get(conferenceName);
    }

    @Override
    public boolean areNumberOfConferencesEven() {
        return (conferences.size() % 2 == 0);
    }

    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public void loadConferencesFromDB() {
        List<HashMap<String, Object>> conferenceMaps = leaguePersistenceAPI.getLeagueConferences(leagueID);
        for (Map<String, Object> conferenceMap : conferenceMaps) {
            int conferenceID = (int) conferenceMap.get("conferenceId");
            String conferenceName = (String) conferenceMap.get("conferenceName");
            Conference conference = new Conference(leagueID, conferenceID, conferenceName, conferencePersistenceAPI, divisionPersistenceAPI, teamPersistenceAPI, playerPersistenceAPI);
            addConferenceToLeague(conference);
            conference.loadDivisionFromDB();
        }
    }
}
