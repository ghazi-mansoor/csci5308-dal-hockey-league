package com.groupten.statemachine.simulation.serializeData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;

import java.io.FileWriter;
import java.io.IOException;

public class SerializeData implements ISerializeData{

    League leagueLOM;

    public SerializeData(){
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        leagueLOM = leagueModel.getCurrentLeague();
    }

    public boolean exportData(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter fileWriter = new FileWriter("SerializedData.json");
            gson.toJson(null, fileWriter);
            fileWriter.close();
            return true;
        }catch (IOException e) {
            return false;
        }
    }
}
