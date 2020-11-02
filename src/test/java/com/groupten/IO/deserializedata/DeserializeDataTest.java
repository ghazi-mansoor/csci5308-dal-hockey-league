package com.groupten.IO.deserializedata;

import com.groupten.IO.serializedata.SerializeData;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.statemachine.jsonimport.JSONImport;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeserializeDataTest {

    @BeforeClass
    public static void setup() {
        JSONImport json = new JSONImport();
        json.importJSONData("src/test/java/com/groupten/mocks/JsonMockCopy.json");
        json.instantiateJSONData();
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League exportedLeague = leagueModel.getCurrentLeague();
        String path = "src/main/resources/SerializedDataTest.json";
        SerializeData serializeData = new SerializeData(path);
        serializeData.exportData(exportedLeague);
    }

    @Test
    public void importDataTestSuccess(){
        String path = "src/main/resources/SerializedDataTest.json";
        DeserializeData deserializeData = new DeserializeData(path);
        League league = deserializeData.importData();
        assertEquals("Deep Hockey League", league.getLeagueName());
    }

    @Test
    public void importDataTestUnSuccess(){
        String path = "";
        DeserializeData deserializeData = new DeserializeData(path);
        assertNull(deserializeData.importData());
    }

}
