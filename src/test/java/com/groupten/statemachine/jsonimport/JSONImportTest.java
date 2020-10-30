package com.groupten.statemachine.jsonimport;

import com.groupten.mocks.LeagueDBMock;
import com.groupten.persistence.dao.ILeagueDAO;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JSONImportTest {

    @Test
    public void importJSONDataTest() {
        JSONImport jsonTestSuccess = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/JsonMock.json";
        assertTrue(jsonTestSuccess.importJSONData(path));
    }

    @Test
    public void isLeagueNameUniqueTest() {
        ILeagueDAO leagueDBObj = new LeagueDBMock();
        JSONImport json = new JSONImport(leagueDBObj);
        String path = "src/test/java/com/groupten/mocks/JsonMock.json";
        json.importJSONData(path);
        assertTrue(json.isLeagueNameUnique());
    }

    @Test
    public void instantiateJSONDataTest() {
        JSONImport jsonTestSuccess = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/JsonMock.json";
        jsonTestSuccess.importJSONData(path);
        jsonTestSuccess.isLeagueNameUnique();
        assertTrue(jsonTestSuccess.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Player() {
        JSONImport jsonTestPlayerBlank = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/PlayerBlankMock.json";
        jsonTestPlayerBlank.importJSONData(path);
        jsonTestPlayerBlank.isLeagueNameUnique();
        assertFalse(jsonTestPlayerBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Division() {
        JSONImport jsonTestDivisionBlank = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/DivisionBlankMock.json";
        jsonTestDivisionBlank.importJSONData(path);
        jsonTestDivisionBlank.isLeagueNameUnique();
        assertFalse(jsonTestDivisionBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Conference() {
        JSONImport jsonTestConferenceBlank = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/ConferenceBlankMock.json";
        jsonTestConferenceBlank.importJSONData(path);
        jsonTestConferenceBlank.isLeagueNameUnique();
        assertFalse(jsonTestConferenceBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_League() {
        JSONImport jsonTestLeagueBlank = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/LeagueBlankMock.json";
        jsonTestLeagueBlank.importJSONData(path);
        jsonTestLeagueBlank.isLeagueNameUnique();
        assertFalse(jsonTestLeagueBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_FreeAgents() {
        JSONImport jsonTestFreeAgentBlank = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/FreeAgentBlankMock.json";
        jsonTestFreeAgentBlank.importJSONData(path);
        jsonTestFreeAgentBlank.isLeagueNameUnique();
        assertFalse(jsonTestFreeAgentBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Team() {
        JSONImport jsonTestTeamBlank = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/TeamBlankMock.json";
        jsonTestTeamBlank.importJSONData(path);
        jsonTestTeamBlank.isLeagueNameUnique();
        assertFalse(jsonTestTeamBlank.instantiateJSONData());
    }

}
