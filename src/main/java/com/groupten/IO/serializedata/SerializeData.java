package com.groupten.IO.serializedata;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;

import java.io.FileWriter;
import java.io.IOException;

public class SerializeData implements ISerializeData{

    public SerializeData(){ }

    public boolean exportData(League leagueLOM){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/SerializedData.json");
            gson.toJson(leagueLOM, fileWriter);
            fileWriter.close();
            return true;
        }catch (IOException e) {
            return false;
        }
    }
}
