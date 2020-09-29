package com.groupten.json;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JSONTest {

    @Test
    public void importJSONData(){
        String path = "src/main/resources/configuration.json";

        JSON json = new JSON();
        assertTrue(json.importJSONData(path));
    }

}
