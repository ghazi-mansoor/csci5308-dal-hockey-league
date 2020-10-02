package com.groupten.json;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JSONTest {

    private static JSON json;

    @BeforeClass
    public static void setUpBeforeClass() {
        json = new JSON();
    }

    @Test
    public void importJSONDataTest(){
        String path = "src/main/resources/testData.json";
        assertTrue(json.importJSONData(path));
    }

    @Test
    public void validateJSONDataTest(){
        assertTrue(json.validateJSONData());
    }

}
