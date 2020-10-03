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
    public boolean validateJSONData() {

        String leagueName = (String) jsonData.get("leagueName");
        JSONArray conferences = (JSONArray) jsonData.get("conferences");
        JSONObject conference, division, team, player;
        JSONArray divisions, teams, players;

        if(leagueName.length() <= 0 || conferences.size() <= 0){
            return false;
        }else{
            for(int i = 0; i < conferences.size(); i++){

                conference = (JSONObject) conferences.get(i);
                String conferenceName = (String) conference.get("conferenceName");
                divisions = (JSONArray) conference.get("divisions");

                if(conferenceName.length() <= 0 || divisions.size() <= 0){
                    return false;
                }else{
                    for(int j = 0; j < divisions.size(); j++){
                        division = (JSONObject) divisions.get(j);
                        String divisionName = (String) division.get("divisionName");
                        teams = (JSONArray) division.get("teams");

                        if(divisionName.length() <= 0 || teams.size() <= 0) {
                            return false;
                        }else{
                            for(int k = 0; k < teams.size(); k++){
                                team = (JSONObject) teams.get(k);
                                String teamName = (String) team.get("teamName");
                                String generalManager = (String) team.get("generalManager");
                                String headCoach = (String) team.get("headCoach");
                                players = (JSONArray) team.get("players");

                                if(teamName.length() <= 0 || generalManager.length() <= 0 || headCoach.length() <= 0 ||
                                    players.size() == 0){
                                    return false;
                                }else{
                                    for(int l = 0; l < players.size(); l++){
                                        player = (JSONObject) players.get(l);
                                        String playerName = (String) player.get("playerName");
                                        String position = (String) player.get("position");

                                        if(playerName.length() <= 0 || position.length() <= 0) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void instantiateJSONData() {
        // Instantiate data in LOM
    }
}
