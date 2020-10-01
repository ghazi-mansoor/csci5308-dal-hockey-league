package com.groupten.json;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JSONTest {

    @Test
    public void importJSONData(){
        String path = "E:/Project/Dalhousie/Advance_Software_Development/csci5308//src/main/resources/testData.json";
        JSON json = new JSON();
        assertTrue(json.importJSONData(path));
    }

}
