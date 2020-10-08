package com.groupten.statemachine.json;

import com.groupten.injector.Injector;
import com.groupten.jdbc.league.LeagueInterface;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
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

        boolean playerAddedToTeam = false, teamAddedToDivision = false, divisionAddedToConference = false, conferenceAddedToLeague = false, freeAgentAddedToLeague = false;
        LeagueModel leagueModel = Injector.injector().getLeagueModelObject();

        League leagueLOM;
        Conference conferenceLOM;
        Division divisionLOM;
        Player playerLOM, freeAgentLOM;
        Team teamLOM;
        JSONObject conference, division, team, teamPlayer, freeAgent;
        JSONArray divisions, teams, players;

        String leagueName = (String) jsonData.get("leagueName");
        leagueLOM = new League(leagueName, Injector.injector().getLeagueDatabaseObject());

        JSONArray conferences = (JSONArray) jsonData.get("conferences");

        for(int i = 0; i < conferences.size(); i++) {
            conference = (JSONObject) conferences.get(i);
            divisions = (JSONArray) conference.get("divisions");

            String conferenceName = (String) conference.get("conferenceName");
            conferenceLOM = new Conference(conferenceName, Injector.injector().getConferenceDatabaseObject());

            for (int j = 0; j < divisions.size(); j++) {
                division = (JSONObject) divisions.get(j);
                teams = (JSONArray) division.get("teams");

                String divisionName = (String) division.get("divisionName");
                divisionLOM = new Division(divisionName, Injector.injector().getDivisionDatabaseObject());

                for (int k = 0; k < teams.size(); k++) {
                    team = (JSONObject) teams.get(k);
                    String teamName = (String) team.get("teamName");
                    String generalManager = (String) team.get("generalManager");
                    String headCoach = (String) team.get("headCoach");
                    players = (JSONArray) team.get("players");

                    teamLOM = new Team(teamName, generalManager, headCoach, Injector.injector().getTeamDatabaseObject());

                    for (int l = 0; l < players.size(); l++) {
                        teamPlayer = (JSONObject) players.get(l);
                        String playerName = (String) teamPlayer.get("playerName");
                        String position = (String) teamPlayer.get("position");
                        Boolean captain = (Boolean) teamPlayer.get("captain");

                        playerLOM = new Player(playerName, position, captain, Injector.injector().getPlayerDatabaseObject(), Injector.injector().getTeamDatabaseObject());
                        if(teamLOM.addPlayerToTeam(playerLOM)){
                            playerAddedToTeam = true;
                        }else{
                            return false;
                        }
                    }

                    if(divisionLOM.addTeamToDivision(teamLOM)){
                        divisionAddedToConference = true;
                    }else{
                        return false;
                    }
                }
                if(conferenceLOM.addDivisionToConference(divisionLOM)){
                    teamAddedToDivision = true;
                }else{
                    return false;
                }
            }
            if( leagueLOM.addConferenceToLeague(conferenceLOM)){
                conferenceAddedToLeague = true;
            }else{
                return false;
            }
        }

        JSONArray freeAgents = (JSONArray) jsonData.get("freeAgents");

        for(int i = 0; i < freeAgents.size(); i++) {
            freeAgent = (JSONObject) freeAgents.get(i);
            String playerName = (String) freeAgent.get("playerName");
            String position = (String) freeAgent.get("position");
            Boolean captain = (Boolean) freeAgent.get("captain");

            freeAgentLOM = new Player(playerName, position, captain, Injector.injector().getPlayerDatabaseObject());
            if(leagueLOM.addFreeAgentToLeague(freeAgentLOM)){
                freeAgentAddedToLeague = true;
            }else{
                return false;
            }
        }

        leagueModel.addLeagueToModel(leagueLOM);

        return playerAddedToTeam && teamAddedToDivision && divisionAddedToConference && conferenceAddedToLeague && freeAgentAddedToLeague;
    }
}
