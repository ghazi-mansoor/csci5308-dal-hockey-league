package com.groupten.statemachine.simulation.serializeData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SerializeData implements ISerializeData{

    private JsonObject league;

    public SerializeData(){
        league = new JsonObject();
    }

    public boolean exportData(){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        prepareData();

        try {
            FileWriter fileWriter = new FileWriter("SerializedData.json");
            gson.toJson(league, fileWriter);
            fileWriter.close();
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    private void prepareData() {

        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League leagueLOM = leagueModel.getCurrentLeague();

        league.addProperty("leagueName", leagueLOM.getLeagueName());

        JsonObject aging = new JsonObject();
        aging.addProperty("averageRetirementAge", leagueLOM.getAverageRetirementAge());
        aging.addProperty("maximumAge", leagueLOM.getMaximumAge());

        JsonObject gameResolver = new JsonObject();
        gameResolver.addProperty("randomWinChance", leagueLOM.getRandomWinChance());

        JsonObject injuries = new JsonObject();
        injuries.addProperty("randomInjuryChance", leagueLOM.getRandomInjuryChance());
        injuries.addProperty("injuryDaysLow", leagueLOM.getInjuryDaysLow());
        injuries.addProperty("injuryDaysHigh", leagueLOM.getInjuryDaysLow());

        JsonObject training = new JsonObject();
        training.addProperty("daysUntilStatIncreaseCheck", leagueLOM.getDaysUntilStatIncreaseCheck());

        JsonObject trading = new JsonObject();
        trading.addProperty("lossPoint", leagueLOM.getLossPoint());
        trading.addProperty("randomTradeOfferChance", leagueLOM.getRandomTradeOfferChance());
        trading.addProperty("maxPlayersPerTrade", leagueLOM.getMaxPlayersPerTrade());
        trading.addProperty("randomAcceptanceChance", leagueLOM.getRandomAcceptanceChance());

        JsonObject gameplayConfig = new JsonObject();
        gameplayConfig.add("aging", aging);
        gameplayConfig.add("gameResolver", gameResolver);
        gameplayConfig.add("injuries", injuries);
        gameplayConfig.add("training", training);
        gameplayConfig.add("trading", trading);

        league.add("gameplayConfig", gameplayConfig);

        JsonArray leagueConferences = new JsonArray();
        league.add("conferences", leagueConferences);
        Map<String, Conference> conferences = leagueLOM.getConferences();
        for (Conference conference: conferences.values()) {
            JsonObject leagueConference = new JsonObject();
            leagueConference.addProperty("conferenceName", conference.getConferenceName());

            JsonArray leagueDivisions = new JsonArray();
            leagueConference.add("divisions", leagueDivisions);
            Map<String, Division> divisions = conference.getDivisions();
            for(Division division: divisions.values()){
                JsonObject leagueDivision = new JsonObject();
                leagueDivision.addProperty("divisionName", division.getDivisionName());

                JsonArray leagueTeams = new JsonArray();
                leagueDivision.add("teams", leagueTeams);
                Map<String, Team> teams = division.getTeams();
                for(Team team: teams.values()){
                    JsonObject leagueTeam = new JsonObject();
                    leagueTeam.addProperty("teamName", team.getTeamName());

                    leagueTeam.addProperty("generalManager", team.getGeneralManager().getManagerName());

                    JsonObject teamCoach = new JsonObject();
                    teamCoach.addProperty("name", team.getHeadCoach().getCoachName());
                    teamCoach.addProperty("skating", team.getHeadCoach().getSkating());
                    teamCoach.addProperty("shooting", team.getHeadCoach().getShooting());
                    teamCoach.addProperty("checking", team.getHeadCoach().getChecking());
                    teamCoach.addProperty("saving", team.getHeadCoach().getSaving());

                    leagueTeam.add("headCoach", teamCoach);

                    JsonArray teamPlayers = new JsonArray();
                    leagueTeam.add("players", teamPlayers);
                    List<Player> players = new ArrayList<>(team.getPlayers());
                    for(Player player: players){
                        JsonObject teamPlayer = new JsonObject();
                        teamPlayer.addProperty("playerName", player.getPlayerName());
                        teamPlayer.addProperty("position", player.getPosition());
                        teamPlayer.addProperty("captain", player.isCaptain());
                        teamPlayer.addProperty("age", player.getAge());
                        teamPlayer.addProperty("skating", player.getSkating());
                        teamPlayer.addProperty("shooting", player.getShooting());
                        teamPlayer.addProperty("checking", player.getChecking());
                        teamPlayer.addProperty("saving", player.getSaving());
                        teamPlayers.add(teamPlayer);
                    }
                    leagueTeams.add(leagueTeam);
                }
                leagueDivisions.add(leagueDivision);
            }
            leagueConferences.add(leagueConference);
        }

        JsonArray freeAgents = new JsonArray();
        league.add("freeAgents", freeAgents);
        List<Player> freeAgentsList = new ArrayList<>(leagueLOM.getFreeAgents());
        for(Player player: freeAgentsList){
            JsonObject agents = new JsonObject();
            agents.addProperty("playerName", player.getPlayerName());
            agents.addProperty("position", player.getPosition());
            agents.addProperty("age", player.getAge());
            agents.addProperty("skating", player.getSkating());
            agents.addProperty("shooting", player.getShooting());
            agents.addProperty("checking", player.getChecking());
            agents.addProperty("saving", player.getSaving());
            freeAgents.add(agents);
        }

        JsonArray freeCoaches = new JsonArray();
        league.add("coaches", freeCoaches);
        List<Coach> freeCoachesList = new ArrayList<>(leagueLOM.getCoaches());
        for(Coach coach: freeCoachesList){
            JsonObject coaches = new JsonObject();
            coaches.addProperty("name", coach.getCoachName());
            coaches.addProperty("skating", coach.getSkating());
            coaches.addProperty("shooting", coach.getShooting());
            coaches.addProperty("checking", coach.getChecking());
            coaches.addProperty("saving", coach.getSaving());
            freeCoaches.add(coaches);
        }

        JsonArray freeGeneralManager = new JsonArray();
        league.add("generalManagers", freeGeneralManager);
        List<GeneralManager> freeGeneralManagerList = new ArrayList<>(leagueLOM.getGeneralManagers());
        for(GeneralManager generalManager: freeGeneralManagerList){
            freeGeneralManager.add(generalManager.getManagerName());
        }
    }
}
