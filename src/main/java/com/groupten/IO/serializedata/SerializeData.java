package com.groupten.IO.serializedata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupten.leagueobjectmodel.league.League;

import java.io.FileWriter;
import java.io.IOException;

public class SerializeData implements ISerializeData{

    private String path;

    public SerializeData(){
        this.path = "src/main/resources/SerializedData.json";
    }

    public SerializeData(String path){
        this.path = path;
    }

    public boolean exportData(League leagueLOM){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter fileWriter = new FileWriter(path);
            gson.toJson(leagueLOM, fileWriter);
            fileWriter.close();
            return true;
        }catch (IOException e) {
            return false;
        }
    }
}
