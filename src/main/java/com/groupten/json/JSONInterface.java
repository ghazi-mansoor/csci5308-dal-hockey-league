package com.groupten.json;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface JSONInterface {

    boolean importJSONData(String path) throws IOException, ParseException;
    void loadJSONData();

}
