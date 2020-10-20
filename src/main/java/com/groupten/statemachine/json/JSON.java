package com.groupten.statemachine.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupten.injector.Injector;
import com.groupten.dao.ILeagueDAO;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class JSON implements IJSON {

    private JsonObject jsonData;

    public JSON(){ }

    public JSON(ILeagueDAO leagueDBMockObj){
        Injector.injector().setLeagueDatabaseObject(leagueDBMockObj);
    }

    @Override
    public boolean importJSONData(String path) {
        JsonParser parser = new JsonParser();

        try{
            jsonData = (JsonObject) parser.parse(new FileReader(path));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean isLeagueNameUnique(){
        ILeagueDAO leagueDB = Injector.injector().getLeagueDatabaseObject();
        String columnName = "leagueName";
        String leagueName = jsonData.get("leagueName").getAsString();
        List<HashMap<String,Object>> leagues = leagueDB.getLeagues(columnName, leagueName);
        return leagues.size() == 0;
    }

    @Override
    public boolean instantiateJSONData() {

        boolean playerAddedToTeam = false, teamAddedToDivision = false, divisionAddedToConference = false, conferenceAddedToLeague = false, freeAgentAddedToLeague = false, leagueAddedToLeagueModel = false;
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();

        League leagueLOM;
        Conference conferenceLOM = null;
        Division divisionLOM;
        Player playerLOM, freeAgentLOM;
        Team teamLOM = null;
        JsonObject conference, division, team, teamPlayer, freeAgent;
        JsonArray divisions, teams, players;

        String leagueName = jsonData.get("leagueName").getAsString();
        leagueLOM = new League(leagueName, Injector.injector().getLeagueDatabaseObject());

        JsonArray conferences = (JsonArray) jsonData.get("conferences");

        for(int i = 0; i < conferences.size(); i++) {
            conference = (JsonObject) conferences.get(i);
            divisions = (JsonArray) conference.get("divisions");

            String conferenceName = conference.get("conferenceName").getAsString();
            conferenceLOM = new Conference(conferenceName, Injector.injector().getConferenceDatabaseObject());

            for (int j = 0; j < divisions.size(); j++) {
                division = (JsonObject) divisions.get(j);
                teams = (JsonArray) division.get("teams");

                String divisionName = division.get("divisionName").getAsString();
                divisionLOM = new Division(divisionName, Injector.injector().getDivisionDatabaseObject());

                for (int k = 0; k < teams.size(); k++) {
                    team = (JsonObject) teams.get(k);
                    String teamName = team.get("teamName").getAsString();
                    String generalManager = team.get("generalManager").getAsString();
                    String headCoach = team.get("headCoach").getAsString();
                    players = (JsonArray) team.get("players");

                    teamLOM = new Team(teamName, generalManager, headCoach, Injector.injector().getTeamDatabaseObject());

                    for (int l = 0; l < players.size(); l++) {
                        teamPlayer = (JsonObject) players.get(l);
                        String playerName = teamPlayer.get("playerName").getAsString();
                        String position = teamPlayer.get("position").getAsString();
                        Boolean captain = teamPlayer.get("captain").getAsBoolean();

                        playerLOM = new Player(playerName, position, captain, Injector.injector().getPlayerDatabaseObject(), Injector.injector().getTeamDatabaseObject());
                        if(teamLOM.addPlayerToTeam(playerLOM)){
                            playerAddedToTeam = true;
                        }else{
                            return false;
                        }
                    }

                    if (divisionLOM.addTeamToDivision(teamLOM) && teamLOM.isOnlyOnePlayerCaptain()){
                        divisionAddedToConference = true;
                    } else{
                        return false;
                    }
                }
                if(conferenceLOM.addDivisionToConference(divisionLOM)){
                    teamAddedToDivision = true;
                }else{
                    return false;
                }
            }
            if(leagueLOM.addConferenceToLeague(conferenceLOM)){
                conferenceAddedToLeague = true;
            } else{
                return false;
            }
        }

        JsonArray freeAgents = (JsonArray) jsonData.get("freeAgents");

        for(int i = 0; i < freeAgents.size(); i++) {
            freeAgent = (JsonObject) freeAgents.get(i);
            String playerName = freeAgent.get("playerName").getAsString();
            String position = freeAgent.get("position").getAsString();
            Boolean captain = freeAgent.get("captain").getAsBoolean();

            freeAgentLOM = new Player(playerName, position, captain, Injector.injector().getPlayerDatabaseObject());
            if(leagueLOM.addFreeAgentToLeague(freeAgentLOM)){
                freeAgentAddedToLeague = true;
            }else{
                return false;
            }
        }

        if(leagueModel.addLeagueToModel(leagueLOM)){
            leagueAddedToLeagueModel = true;
        }else{
            return false;
        }

        return teamLOM.isOnlyOnePlayerCaptain() && leagueAddedToLeagueModel && playerAddedToTeam && teamAddedToDivision && divisionAddedToConference && conferenceAddedToLeague && freeAgentAddedToLeague && leagueLOM.areNumberOfConferencesEven() && conferenceLOM.areNumberOfDivisionsEven();
    }
}
