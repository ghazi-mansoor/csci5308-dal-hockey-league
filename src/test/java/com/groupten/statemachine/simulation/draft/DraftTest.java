package com.groupten.statemachine.simulation.draft;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.statemachine.jsonimport.JSONImport;
import org.junit.Test;

public class DraftTest {
    @Test
    public void executeTest() {
        JSONImport jsonTestSuccess = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/league.json";
        jsonTestSuccess.importJSONData(path);
        jsonTestSuccess.instantiateJSONData();
        IDraft draft = Injector.instance().getDraftInterface();
        draft.execute(new Season());
    }
}
