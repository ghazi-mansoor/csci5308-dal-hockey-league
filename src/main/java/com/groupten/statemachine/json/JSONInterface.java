package com.groupten.statemachine.json;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface JSONInterface {

    boolean importJSONData(String path) throws IOException, ParseException;
    boolean validateJSONData();
    void instantiateJSONData();

}
