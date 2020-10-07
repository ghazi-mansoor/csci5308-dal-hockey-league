package com.groupten.statemachine.json;

import com.groupten.jdbc.league.LeagueInterface;
import com.groupten.statemachine.mocks.LeagueDBMock;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JSONTest {

    @Test
    public void importJSONDataTest() {
        JSON json = new JSON();
        String path = "src/main/resources/testData.json";
        assertTrue(json.importJSONData(path));
    }

    @Test
    public void isLeagueNameUniqueTest(){
        LeagueInterface leagueDBObj = new LeagueDBMock();
        JSON json = new JSON(leagueDBObj);
        String path = "src/main/resources/testData.json";
        json.importJSONData(path);
        assertTrue(json.isLeagueNameUnique());
    }
}
