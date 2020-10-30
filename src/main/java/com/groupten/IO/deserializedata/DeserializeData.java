package com.groupten.IO.deserializedata;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.groupten.leagueobjectmodel.league.League;

import java.io.FileReader;

public class DeserializeData implements IDeserializeData{

    private String path;

    public DeserializeData(){
        this.path = "src/main/resources/SerializedData.json";
    }

    public DeserializeData(String path){
        this.path = path;
    }

    public League importData() {
        FileReader fileReader;
        JsonParser jsonParser = new JsonParser();
        Gson gson = new Gson();
        League importedLeague;

        try {
            fileReader = new FileReader(path);
            importedLeague = gson.fromJson(jsonParser.parse(fileReader).toString(), League.class);
            fileReader.close();
        } catch (Exception e) {
            return null;
        }

        return importedLeague;

    }
}
