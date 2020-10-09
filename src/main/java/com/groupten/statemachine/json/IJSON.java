package com.groupten.statemachine.json;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface IJSON {

    boolean importJSONData(String path) throws IOException, ParseException;
    boolean isLeagueNameUnique();
    boolean instantiateJSONData();

}
