package com.groupten.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSON implements JSONInterface {

    private JSONObject jsonData;
    private JSONParser parser;

    public JSON(){
       parser = new JSONParser();
    }

    @Override
    public boolean importJSONData(String path) {
        try{
            jsonData = (JSONObject) parser.parse(new FileReader(path));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void loadJSONData() {
        // TODO Instantiate the LOM
        System.out.println(jsonData);

        JSONArray conferences = (JSONArray) jsonData.get("conferences");
        JSONObject conference, division, team;
        JSONArray divisions, teams;

        for(int i = 0; i < conferences.size(); i++){
            conference = (JSONObject) conferences.get(i);
            System.out.println(conference.get("conferenceName"));
            divisions = (JSONArray) conference.get("divisions");

            for(int j = 0; j < divisions.size(); j++){
                division = (JSONObject) divisions.get(j);
                System.out.println(division.get("divisionName"));
                teams = (JSONArray) division.get("teams");

                for(int k = 0; k < teams.size(); k++){
                    team = (JSONObject) teams.get(k);
                    System.out.println(team.get("teamName"));

                }

            }

        }







    }
}
