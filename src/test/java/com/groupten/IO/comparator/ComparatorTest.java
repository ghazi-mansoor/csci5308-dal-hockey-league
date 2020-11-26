package com.groupten.IO.comparator;

import com.groupten.statemachine.jsonimport.JSONImport;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComparatorTest {

    @BeforeClass
    public static void setup() {
        JSONImport json = new JSONImport();
        json.importJSONData("src/test/java/com/groupten/mocks/JsonMock.json");
        json.instantiateJSONData();
    }

    @Test
    public void exportDataTest() {
//        IComparator comparator = Injector.instance().getComparatorObject();
//        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
//        League exportedLeague = leagueModel.getCurrentLeague();
//        leagueModel.getCurrentLeague().setUserTeam("Team Deep");
//
//        String path = "src/main/resources/";
//        SerializeData serializeData = new SerializeData();
//        assertTrue(serializeData.exportData(exportedLeague, path));
//
//        DeserializeData deserializeData = new DeserializeData();
//        League importedLeague = deserializeData.importData("src/test/java/com/groupten/mocks/Team_Deep.json");
//        assertTrue(comparator.compareLeagues(exportedLeague, importedLeague));
    }

}
