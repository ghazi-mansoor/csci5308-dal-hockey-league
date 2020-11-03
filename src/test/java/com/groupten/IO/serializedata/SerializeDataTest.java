package com.groupten.IO.serializedata;

import com.groupten.IO.comparator.IComparator;
import com.groupten.IO.deserializedata.DeserializeData;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.statemachine.jsonimport.JSONImport;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SerializeDataTest {

    @BeforeClass
    public static void setup() {
        JSONImport json = new JSONImport();
        json.importJSONData("src/test/java/com/groupten/mocks/JsonMock.json");
        json.instantiateJSONData();
    }

    @Test
    public void exportDataTestSuccess(){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League exportedLeague = leagueModel.getCurrentLeague();
        String path = "src/main/resources/SerializedData.json";
        SerializeData serializeData = new SerializeData(path);
        assertTrue(serializeData.exportData(exportedLeague));
    }

    @Test
    public void exportDataTestUnSuccess(){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League exportedLeague = leagueModel.getCurrentLeague();
        String path = "";
        SerializeData serializeData = new SerializeData(path);
        assertFalse(serializeData.exportData(exportedLeague));
    }

}
