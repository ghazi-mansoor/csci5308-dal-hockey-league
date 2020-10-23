package com.groupten.statemachine.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupten.dao.ILeagueDAO;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
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
    public boolean instantiateJSONData(){

        boolean playerAdded = false,
                teamAdded = false,
                divisionAdded = false,
                conferenceAdded = false,
                leagueAdded = false,
                managerAdded = false,
                coachAdded = false;

        League leagueLOM;
        Conference conferenceLOM = null;
        Division divisionLOM;
        GeneralManager managerLOM;
        Coach coachLOM;
        Player playerLOM, freeAgentLOM;
        Team teamLOM = null;

        JsonObject conference, division, team, teamPlayer, headCoach, coach, freeAgent;
        JsonObject gamePlayConfig, aging, gameResolver, injuries, training, trading;
        JsonArray conferences, divisions, teams, players;

        String leagueName = jsonData.get("leagueName").getAsString();

        System.out.println(leagueName);

        gamePlayConfig = (JsonObject) jsonData.get("gameplayConfig");
        aging = (JsonObject) gamePlayConfig.get("aging");
        gameResolver = (JsonObject) gamePlayConfig.get("gameResolver");
        injuries = (JsonObject) gamePlayConfig.get("injuries");
        training = (JsonObject) gamePlayConfig.get("training");
        trading = (JsonObject) gamePlayConfig.get("trading");

        double averageRetirementAge = aging.get("averageRetirementAge").getAsDouble();
        double maximumAge = aging.get("maximumAge").getAsDouble();

        System.out.println(averageRetirementAge + " " + maximumAge);

        double randomWinChance = gameResolver.get("randomWinChance").getAsDouble();

        System.out.println(randomWinChance);

        double randomInjuryChance = injuries.get("randomInjuryChance").getAsDouble();
        double injuryDaysLow = injuries.get("injuryDaysLow").getAsDouble();
        double injuryDaysHigh = injuries.get("injuryDaysHigh").getAsDouble();

        System.out.println(randomInjuryChance + " " + injuryDaysLow + " " + injuryDaysHigh);

        double daysUntilStatIncreaseCheck = training.get("daysUntilStatIncreaseCheck").getAsDouble();

        System.out.println(daysUntilStatIncreaseCheck);

        double lossPoint = trading.get("lossPoint").getAsDouble();
        double randomTradeOfferChance = trading.get("randomTradeOfferChance").getAsDouble();
        double maxPlayersPerTrade = trading.get("maxPlayersPerTrade").getAsDouble();
        double randomAcceptanceChance = trading.get("randomAcceptanceChance").getAsDouble();

        System.out.println(lossPoint + " " + randomTradeOfferChance + " " + maxPlayersPerTrade + " " + randomAcceptanceChance);

        conferences = (JsonArray) jsonData.get("conferences");

        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();

        leagueLOM = new League(leagueName);

        for(int i = 0; i < conferences.size(); i++) {
            conference = (JsonObject) conferences.get(i);
            divisions = (JsonArray) conference.get("divisions");

            String conferenceName = conference.get("conferenceName").getAsString();
            System.out.println(conferenceName);

            conferenceLOM = new Conference(conferenceName);

            for (int j = 0; j < divisions.size(); j++) {
                division = (JsonObject) divisions.get(j);
                teams = (JsonArray) division.get("teams");

                String divisionName = division.get("divisionName").getAsString();
                System.out.println(divisionName);

                divisionLOM = new Division(divisionName);

                for (int k = 0; k < teams.size(); k++) {
                    team = (JsonObject) teams.get(k);
                    String teamName = team.get("teamName").getAsString();
                    String generalManager = team.get("generalManager").getAsString();
                    headCoach = (JsonObject) team.get("headCoach");
                    String coachName = headCoach.get("name").getAsString();
                    double coachSkating = headCoach.get("skating").getAsDouble();
                    double coachShooting = headCoach.get("shooting").getAsDouble();
                    double coachChecking = headCoach.get("checking").getAsDouble();
                    double coachSaving = headCoach.get("saving").getAsDouble();
                    players = (JsonArray) team.get("players");

                    System.out.println(teamName + " " + generalManager);
                    System.out.println(coachName + " " + coachSkating + " " + coachShooting + " " + coachChecking + " " + coachSaving);

                    teamLOM = new Team(teamName);
                    managerLOM = new GeneralManager(generalManager);
                    coachLOM = new Coach(coachName, coachSkating, coachShooting, coachChecking, coachSaving);

                    // Add method in LOM for adding manager and coach to LOM
                    // Use leagueLOM.addGeneralManager(managerLOM) -> returns boolean
                    // Use leagueLOM.addCoach(coachLOM) -> returns boolean

                    for (int l = 0; l < players.size(); l++) {
                        teamPlayer = (JsonObject) players.get(l);
                        String playerName = teamPlayer.get("playerName").getAsString();
                        String position = teamPlayer.get("position").getAsString();
                        Boolean captain = teamPlayer.get("captain").getAsBoolean();
                        // Change them to double
                        double playerAge = teamPlayer.get("age").getAsInt();
                        double playerSkating = teamPlayer.get("skating").getAsInt();
                        double playerShooting = teamPlayer.get("shooting").getAsInt();
                        double playerChecking = teamPlayer.get("checking").getAsInt();
                        double playerSaving = teamPlayer.get("saving").getAsInt();

                        playerLOM = new Player(playerName, position, captain, playerAge, playerSkating, playerShooting, playerChecking, playerSaving);

                        if(teamLOM.addPlayer(playerLOM)){
                            playerAdded = true;
                        }else{
                            return false;
                        }
                    }

                    if(divisionLOM.addTeam(teamLOM)){
                        teamAdded = true;
                    }else{
                        return false;
                    }
                }
                if(conferenceLOM.addDivision(divisionLOM)){
                    divisionAdded = true;
                }else{
                    return false;
                }
            }
            if(leagueLOM.addConference(conferenceLOM)){
                conferenceAdded = true;
            } else{
                return false;
            }

        }

        JsonArray freeAgents = (JsonArray) jsonData.get("freeAgents");
        for(int i = 0; i < freeAgents.size(); i++) {
            freeAgent = (JsonObject) freeAgents.get(i);
            String playerName = freeAgent.get("playerName").getAsString();
            String position = freeAgent.get("position").getAsString();
            int playerAge = freeAgent.get("age").getAsInt();
            int playerSkating = freeAgent.get("skating").getAsInt();
            int playerShooting = freeAgent.get("shooting").getAsInt();
            // remove captain field from free agents
            // Boolean captain = false;
            int playerChecking = freeAgent.get("checking").getAsInt();
            int playerSaving = freeAgent.get("saving").getAsInt();

            playerLOM = new Player(playerName, position, playerAge, playerSkating, playerShooting, playerChecking, playerSaving);
            if(leagueLOM.addFreeAgent(playerLOM)){
                playerAdded = true;
            }else{
                return false;
            }
        }

        JsonArray coaches = (JsonArray) jsonData.get("coaches");
        for(int i = 0; i < coaches.size(); i++) {
            coach = (JsonObject) coaches.get(i);
            String coachName = coach.get("name").getAsString();
            double coachSkating = coach.get("skating").getAsDouble();
            double coachShooting = coach.get("shooting").getAsDouble();
            double coachChecking = coach.get("checking").getAsDouble();
            double coachSaving = coach.get("saving").getAsDouble();

            coachLOM = new Coach(coachName, coachSkating, coachShooting, coachChecking, coachSaving);
            if(leagueLOM.addCoach(coachLOM)){
                coachAdded = true;
            }else{
                return false;
            }
        }

        JsonArray generalManagers = (JsonArray) jsonData.get("generalManagers");
        for(int i = 0; i < generalManagers.size(); i++) {
            String generalManager = generalManagers.get(i).getAsString();

            managerLOM = new GeneralManager(generalManager);
            if(leagueLOM.addGeneralManager(managerLOM)){
                managerAdded = true;
            }else{
                return false;
            }
        }

        if(leagueModel.addLeague(leagueLOM)){
            leagueAdded = true;
        }else{
            return false;
        }

        return managerAdded && coachAdded && playerAdded && teamAdded && leagueAdded && divisionAdded && conferenceAdded;
    }
}
