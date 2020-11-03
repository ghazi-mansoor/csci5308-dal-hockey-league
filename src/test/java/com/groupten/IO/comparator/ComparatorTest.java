package com.groupten.IO.comparator;

import com.groupten.IO.deserializedata.DeserializeData;
import com.groupten.IO.serializedata.SerializeData;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.statemachine.jsonimport.JSONImport;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ComparatorTest {

    @BeforeClass
    public static void setup() {
        JSONImport json = new JSONImport();
        json.importJSONData("src/test/java/com/groupten/mocks/JsonMock.json");
        json.instantiateJSONData();
    }

    @Test
    public void exportDataTest() {

        IComparator comparator = Injector.instance().getComparatorObject();
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League exportedLeague = leagueModel.getCurrentLeague();

        SerializeData serializeData = new SerializeData();
        assertTrue(serializeData.exportData(exportedLeague));

        DeserializeData deserializeData = new DeserializeData();
        League importedLeague = deserializeData.importData();
        assertTrue(comparator.compareLeagues(exportedLeague, importedLeague));
    }

}
