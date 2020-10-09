package com.groupten.leagueobjectmodel.conference;

import com.groupten.jdbc.conference.IConferenceDAO;
import com.groupten.jdbc.division.IDivisionDAO;
import com.groupten.jdbc.player.IPlayerDAO;
import com.groupten.jdbc.team.ITeamDAO;
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
    private IConferenceDAO conferencePersistenceAPI;
    private IDivisionDAO divisionPersistenceAPI;
    private ITeamDAO teamPersistenceAPI;
    private IPlayerDAO playerPersistenceAPI;

    public Conference(String cn) {
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
    }

    public Conference(String cn, IConferenceDAO per) {
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
        conferencePersistenceAPI = per;
    }

    public Conference(int lID, int cID, String cn, IConferenceDAO cPer, IDivisionDAO dPer, ITeamDAO tPer, IPlayerDAO pPer) {
        leagueID = lID;
        conferenceID = cID;
        conferenceName = cn;
        divisions = new HashMap<String, Division>();
        conferencePersistenceAPI = cPer;
        divisionPersistenceAPI = dPer;
        teamPersistenceAPI = tPer;
        playerPersistenceAPI = pPer;
    }

    @Override
    public boolean addDivisionToConference(Division division) {
        String divisionName = division.getDivisionName();
        if (Validator.areStringsValid(divisionName)) {
            divisions.put(division.getDivisionName(), division);
            return divisions.containsKey(division.getDivisionName());
        } else {
            return false;
        }
    }

    @Override
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

    @Override
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

    @Override
    public boolean areNumberOfDivisionsEven() {
        return (divisions.size() % 2 == 0);
    }

    @Override
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
