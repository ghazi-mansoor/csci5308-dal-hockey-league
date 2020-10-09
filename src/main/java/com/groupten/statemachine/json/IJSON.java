package com.groupten.statemachine.json;
import java.io.IOException;

public interface IJSON {

    boolean importJSONData(String path) throws IOException;
    boolean isLeagueNameUnique();
    boolean instantiateJSONData();

}
