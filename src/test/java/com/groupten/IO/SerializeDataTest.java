package com.groupten.IO;

import com.groupten.statemachine.jsonimport.JSONImport;
import com.groupten.IO.serializedata.SerializeData;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SerializeDataTest {

    @BeforeClass
    public static void setup() {
        JSONImport json = new JSONImport();
        json.importJSONData("src/test/java/com/groupten/mocks/JsonMockCopy.json");
        json.instantiateJSONData();
    }

    @Test
    public void exportDataTest() {
        SerializeData serializeData = new SerializeData();
        assertTrue(serializeData.exportData());
    }

}
