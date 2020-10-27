package com.groupten.statemachine.simulate.serialize;

import com.groupten.statemachine.json.JSON;
import com.groupten.statemachine.simulation.serializedata.SerializeData;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SerializeDataTest {

    @BeforeClass
    public static void setup(){
        JSON json = new JSON();
        json.importJSONData("src/test/java/com/groupten/statemachine/mocks/JsonMockCopy.json");
        json.instantiateJSONData();
    }

    @Test
    public void exportDataTest(){
        SerializeData serializeData = new SerializeData();
        assertTrue(serializeData.exportData());
    }

}
