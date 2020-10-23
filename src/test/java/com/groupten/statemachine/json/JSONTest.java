package com.groupten.statemachine.json;

import com.groupten.dao.ILeagueDAO;
import com.groupten.statemachine.mocks.LeagueDBMock;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JSONTest {

    @Test
    public void importJSONDataTest() {
        JSON jsonTestSuccess = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/JsonMock.json";
        assertTrue(jsonTestSuccess.importJSONData(path));
    }

    @Test
    public void isLeagueNameUniqueTest(){
        ILeagueDAO leagueDBObj = new LeagueDBMock();
        JSON json = new JSON(leagueDBObj);
        String path = "src/test/java/com/groupten/statemachine/mocks/JsonMock.json";
        json.importJSONData(path);
        assertTrue(json.isLeagueNameUnique());
    }

    @Test
    public void instantiateJSONDataTest(){
        JSON jsonTestSuccess = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/JsonMock.json";
        jsonTestSuccess.importJSONData(path);
        jsonTestSuccess.isLeagueNameUnique();
        assertTrue(jsonTestSuccess.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Player(){
        JSON jsonTestPlayerBlank = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/PlayerBlankMock.json";
        jsonTestPlayerBlank.importJSONData(path);
        jsonTestPlayerBlank.isLeagueNameUnique();
        assertFalse(jsonTestPlayerBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Division(){
        JSON jsonTestDivisionBlank = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/DivisionBlankMock.json";
        jsonTestDivisionBlank.importJSONData(path);
        jsonTestDivisionBlank.isLeagueNameUnique();
        assertFalse(jsonTestDivisionBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Conference(){
        JSON jsonTestConferenceBlank = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/ConferenceBlankMock.json";
        jsonTestConferenceBlank.importJSONData(path);
        jsonTestConferenceBlank.isLeagueNameUnique();
        assertFalse(jsonTestConferenceBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_League(){
        JSON jsonTestLeagueBlank = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/LeagueBlankMock.json";
        jsonTestLeagueBlank.importJSONData(path);
        jsonTestLeagueBlank.isLeagueNameUnique();
        assertFalse(jsonTestLeagueBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_FreeAgents(){
        JSON jsonTestFreeAgentBlank = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/FreeAgentBlankMock.json";
        jsonTestFreeAgentBlank.importJSONData(path);
        jsonTestFreeAgentBlank.isLeagueNameUnique();
        assertFalse(jsonTestFreeAgentBlank.instantiateJSONData());
    }

    @Test
    public void instantiateJSONDataTest_Team(){
        JSON jsonTestTeamBlank = new JSON();
        String path = "src/test/java/com/groupten/statemachine/mocks/TeamBlankMock.json";
        jsonTestTeamBlank.importJSONData(path);
        jsonTestTeamBlank.isLeagueNameUnique();
        assertFalse(jsonTestTeamBlank.instantiateJSONData());
    }

}
