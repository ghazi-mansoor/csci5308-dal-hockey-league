package com.groupten.statemachine.simulation.draft;

import com.groupten.statemachine.jsonimport.JSONImport;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlayerGenerator {
    @Test
    public void generatePlayersTest() {
        JSONImport jsonTestSuccess = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/JsonMock.json";
        jsonTestSuccess.importJSONData(path);
        jsonTestSuccess.instantiateJSONData();
    }
}
