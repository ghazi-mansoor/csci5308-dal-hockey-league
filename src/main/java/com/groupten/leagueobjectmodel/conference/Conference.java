package com.groupten.leagueobjectmodel.conference;

import com.groupten.jdbc.conference.ConferenceInterface;
import com.groupten.jdbc.division.DivisionInterface;
import com.groupten.jdbc.player.PlayerInterface;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.validator.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conference implements IConference {
    private int leagueID;
    private int conferenceID;
    private String conferenceName;
    private Map<String, Division> divisions;
    private ConferenceInterface conferencePersistenceAPI;
    private DivisionInterface divisionPersistenceAPI;
    private TeamInterface teamPersistenceAPI;
    private PlayerInterface playerPersistenceAPI;

    public Conference(String cn) {
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
    }

    public Conference(String cn, ConferenceInterface per) {
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
        conferencePersistenceAPI = per;
    }

    public Conference(int lID, int cID, String cn, ConferenceInterface cPer, DivisionInterface dPer, TeamInterface tPer, PlayerInterface pPer) {
        leagueID = lID;
        conferenceID = cID;
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
        conferencePersistenceAPI = cPer;
        divisionPersistenceAPI = dPer;
        teamPersistenceAPI = tPer;
        playerPersistenceAPI = pPer;
    }

    public boolean addDivisionToConference(Division division) {
        String divisionName = division.getDivisionName();
        if (Validator.areStringsValid(divisionName)) {
            divisions.put(division.getDivisionName(), division);
            return divisions.containsKey(division.getDivisionName());
        } else {
            return false;
        }
    }

    public boolean saveConferenceToDB() {
        conferenceID = conferencePersistenceAPI.createConference(leagueID, conferenceName);
        setDivisionForeignKeys();
        saveAllDivisions();
        return (conferenceID != 0);
    }

    private void setDivisionForeignKeys() {
        for (Division division : divisions.values()) {
            division.setConferenceID(conferenceID);
            division.setLeagueID(leagueID);
        }
    }

    private void saveAllDivisions() {
        for (Division division : divisions.values()) {
            division.saveDivisionToDB();
        }
    }

    public boolean doesContainDivision(String divisionName) {
        return divisions.containsKey(divisionName);
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public Division getDivision(String divisionName) {
        return divisions.get(divisionName);
    }

    public boolean areNumberOfDivisionsEven() {
        return (divisions.size() % 2 == 0);
    }

    public void loadDivisionFromDB() {
        List<HashMap<String, Object>> divisionMaps = conferencePersistenceAPI.getConferenceDivisions(conferenceID);
        for (Map<String, Object> divisionMap : divisionMaps) {
            int divisionID = (int) divisionMap.get("divisionId");
            String divisionName = (String) divisionMap.get("divisionName");
            Division division = new Division(leagueID, conferenceID, divisionID, divisionName, divisionPersistenceAPI, teamPersistenceAPI, playerPersistenceAPI);
            addDivisionToConference(division);
            division.loadTeamsFromDB();
        }
    }
}
