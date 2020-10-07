package com.groupten.statemachine.json;

import com.groupten.injector.Injector;
import com.groupten.jdbc.league.LeagueInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class JSON implements JSONInterface {

    private JSONObject jsonData;

    public JSON(){ }

    public JSON(LeagueInterface leagueDBObj){
        Injector.injector().setLeagueDatabaseObject(leagueDBObj);
    }

    @Override
    public boolean importJSONData(String path) {
        JSONParser parser = new JSONParser();
        try{
            jsonData = (JSONObject) parser.parse(new FileReader(path));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean isLeagueNameUnique(){
        LeagueInterface leagueDB = Injector.injector().getLeagueDatabaseObject();
        String columnName = "leagueName";
        String leagueName = (String) jsonData.get("leagueName");
        List<HashMap<String,Object>> leagues = leagueDB.getLeagues(columnName, leagueName);
        return leagues.size() == 0;
    }

    @Override
    public boolean instantiateJSONData() {

        JSONObject conference, division, team, teamPlayer, freePlayer;
        JSONArray divisions, teams, players;

        String leagueName = (String) jsonData.get("leagueName");

        JSONArray conferences = (JSONArray) jsonData.get("conferences");

        for(int i = 0; i < conferences.size(); i++) {
            conference = (JSONObject) conferences.get(i);
            String conferenceName = (String) conference.get("conferenceName");
            divisions = (JSONArray) conference.get("divisions");

            // Invoke the method in LOM to add the conference
            // Return false if not able to add

            for (int j = 0; j < divisions.size(); j++) {
                division = (JSONObject) divisions.get(j);
                String divisionName = (String) division.get("divisionName");
                teams = (JSONArray) division.get("teams");

                // Invoke the method in LOM to add the division
                // Return false if not able to add

                for (int k = 0; k < teams.size(); k++) {
                    team = (JSONObject) teams.get(k);
                    String teamName = (String) team.get("teamName");
                    String generalManager = (String) team.get("generalManager");
                    String headCoach = (String) team.get("headCoach");
                    players = (JSONArray) team.get("players");

                    // Invoke the method in LOM to add the Team Name, General Manager, HeadCoach
                    // Return false if not able to add

                    for (int l = 0; l < players.size(); l++) {
                        teamPlayer = (JSONObject) players.get(l);
                        String playerName = (String) teamPlayer.get("playerName");
                        String position = (String) teamPlayer.get("position");
                        Boolean captain = (Boolean) teamPlayer.get("captain");

                        // Invoke the method in LOM to add the Player Name, Position, Captain
                        // Return false if not able to add

                    }
                }
            }
        }

        JSONArray freeAgents = (JSONArray) jsonData.get("freeAgents");

        for(int i = 0; i < freeAgents.size(); i++) {
            freePlayer = (JSONObject) freeAgents.get(i);
            String playerName = (String) freePlayer.get("playerName");
            String position = (String) freePlayer.get("position");
            Boolean captain = (Boolean) freePlayer.get("captain");

            // Invoke the method in LOM to add the Player Name, Position, Captain
            // Return false if not able to add

        }
        return true;
    }
}
