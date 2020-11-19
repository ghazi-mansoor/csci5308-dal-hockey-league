package com.groupten.statemachinenew.states;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.statemachinenew.StateMachine;

import java.io.FileReader;

public class ImportState implements IState {
    StateMachine stateMachine;
    private JsonObject jsonData;

    public ImportState(StateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    @Override
    public void doAction() {
        IConsole console = Injector.instance().getConsoleObject();

        console.printLine("Please enter the file path:");
        String path = console.readLine();

        console.printLine("Reading and Validating JSON file");
        if(validateJson(path)){
            console.printLine("SUCCESS: Reading JSON file");
        }else{
            console.printLine("ERROR: Invalid input file or path");
            stateMachine.retry();
        }

        console.printLine("Loading JSON file");
        if(instantiateLOM()){
            console.printLine("SUCCESS: JSON loaded");
            stateMachine.setState(new CreateTeamState(stateMachine));
            stateMachine.doAction();
        }else{
            console.printLine("FAILURE: Some error occurred");
            stateMachine.retry();
        }
    }

    private boolean validateJson(String path){
        JsonParser parser = new JsonParser();
        try {
            jsonData = (JsonObject) parser.parse(new FileReader(path));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean instantiateLOM(){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League leagueLOM;
        Conference conferenceLOM;
        Division divisionLOM;
        GeneralManager managerLOM;
        Coach coachLOM;
        Player playerLOM;
        Team teamLOM;
        GameConfig.Aging agingLOM;
        GameConfig.GameResolver gameResolverLOM;
        GameConfig.Injuries injuriesLOM;
        GameConfig.Training trainingLOM;
        GameConfig.Trading tradingLOM;

        JsonObject conference, division, team, headCoach, coach, teamPlayer, freeAgent;
        JsonObject gamePlayConfig, aging, gameResolver, injuries, training, trading;
        JsonArray conferences, divisions, teams, players;

        boolean success = false;

        gamePlayConfig = (JsonObject) jsonData.get("gameplayConfig");
        aging = (JsonObject) gamePlayConfig.get("aging");
        gameResolver = (JsonObject) gamePlayConfig.get("gameResolver");
        injuries = (JsonObject) gamePlayConfig.get("injuries");
        training = (JsonObject) gamePlayConfig.get("training");
        trading = (JsonObject) gamePlayConfig.get("trading");

        int averageRetirementAge = aging.get("averageRetirementAge").getAsInt();
        int maximumAge = aging.get("maximumAge").getAsInt();

        double randomWinChance = gameResolver.get("randomWinChance").getAsDouble();

        double randomInjuryChance = injuries.get("randomInjuryChance").getAsDouble();
        int injuryDaysLow = injuries.get("injuryDaysLow").getAsInt();
        int injuryDaysHigh = injuries.get("injuryDaysHigh").getAsInt();

        int daysUntilStatIncreaseCheck = training.get("daysUntilStatIncreaseCheck").getAsInt();

        int lossPoint = trading.get("lossPoint").getAsInt();
        double randomTradeOfferChance = trading.get("randomTradeOfferChance").getAsDouble();
        int maxPlayersPerTrade = trading.get("maxPlayersPerTrade").getAsInt();
        double randomAcceptanceChance = trading.get("randomAcceptanceChance").getAsDouble();

        String leagueName = jsonData.get("leagueName").getAsString();
        conferences = (JsonArray) jsonData.get("conferences");

        leagueLOM = new League(leagueName);

        agingLOM = new GameConfig.Aging(averageRetirementAge, maximumAge);
        gameResolverLOM = new GameConfig.GameResolver(randomWinChance);
        injuriesLOM = new GameConfig.Injuries(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
        trainingLOM = new GameConfig.Training(daysUntilStatIncreaseCheck);
        tradingLOM = new GameConfig.Trading(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance);

        leagueLOM.setAgingConfig(agingLOM);
        leagueLOM.setGameResolverConfig(gameResolverLOM);
        leagueLOM.setInjuriesConfig(injuriesLOM);
        leagueLOM.setTrainingConfig(trainingLOM);
        leagueLOM.setTradingConfig(tradingLOM);

        try {
            for (int i = 0; i < conferences.size(); i++) {
                conference = (JsonObject) conferences.get(i);
                divisions = (JsonArray) conference.get("divisions");
                String conferenceName = conference.get("conferenceName").getAsString();

                conferenceLOM = new Conference(conferenceName);

                for (int j = 0; j < divisions.size(); j++) {
                    division = (JsonObject) divisions.get(j);
                    teams = (JsonArray) division.get("teams");
                    String divisionName = division.get("divisionName").getAsString();

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

                        teamLOM = new Team(teamName);
                        managerLOM = new GeneralManager(generalManager);
                        coachLOM = new Coach(coachName, coachSkating, coachShooting, coachChecking, coachSaving);

                        if (teamLOM.setGeneralManager(managerLOM)) {
                            success = true;
                        }else{
                            throw new Exception("Issue with JSON Data");
                        }

                        if (teamLOM.setHeadCoach(coachLOM)) {
                            success = true;
                        }else{
                            throw new Exception("Issue with JSON Data");
                        }

                        for (int l = 0; l < players.size(); l++) {
                            teamPlayer = (JsonObject) players.get(l);
                            String playerName = teamPlayer.get("playerName").getAsString();
                            String position = teamPlayer.get("position").getAsString();
                            boolean captain = teamPlayer.get("captain").getAsBoolean();
                            double playerAge = teamPlayer.get("age").getAsDouble();
                            double playerSkating = teamPlayer.get("skating").getAsDouble();
                            double playerShooting = teamPlayer.get("shooting").getAsDouble();
                            double playerChecking = teamPlayer.get("checking").getAsDouble();
                            double playerSaving = teamPlayer.get("saving").getAsDouble();

                            playerLOM = new Player(playerName, position, captain, playerAge, playerSkating, playerShooting, playerChecking, playerSaving);

                            if (teamLOM.addPlayer(playerLOM)) {
                                success = true;
                            }else{
                                throw new Exception("Issue with JSON Data");
                            }
                        }

                        if (divisionLOM.addTeam(teamLOM)) {
                            success = true;
                        }else{
                            throw new Exception("Issue with JSON Data");
                        }
                    }
                    if (conferenceLOM.addDivision(divisionLOM)) {
                        success = true;
                    }else{
                        throw new Exception("Issue with JSON Data");
                    }
                }
                if (leagueLOM.addConference(conferenceLOM)) {
                    success = true;
                }else{
                    throw new Exception("Issue with JSON Data");
                }

            }

            JsonArray freeAgents = (JsonArray) jsonData.get("freeAgents");
            for (int i = 0; i < freeAgents.size(); i++) {
                freeAgent = (JsonObject) freeAgents.get(i);
                String playerName = freeAgent.get("playerName").getAsString();
                String position = freeAgent.get("position").getAsString();
                double playerAge = freeAgent.get("age").getAsDouble();
                double playerSkating = freeAgent.get("skating").getAsDouble();
                double playerShooting = freeAgent.get("shooting").getAsDouble();
                double playerChecking = freeAgent.get("checking").getAsDouble();
                double playerSaving = freeAgent.get("saving").getAsDouble();

                playerLOM = new Player(playerName, position, playerAge, playerSkating, playerShooting, playerChecking, playerSaving);

                if (leagueLOM.addFreeAgent(playerLOM)) {
                    success = true;
                }else{
                    throw new Exception("Issue with JSON Data");
                }
            }

            JsonArray coaches = (JsonArray) jsonData.get("coaches");

            for (int i = 0; i < coaches.size(); i++) {
                coach = (JsonObject) coaches.get(i);
                String coachName = coach.get("name").getAsString();
                double coachSkating = coach.get("skating").getAsDouble();
                double coachShooting = coach.get("shooting").getAsDouble();
                double coachChecking = coach.get("checking").getAsDouble();
                double coachSaving = coach.get("saving").getAsDouble();

                coachLOM = new Coach(coachName, coachSkating, coachShooting, coachChecking, coachSaving);

                if (leagueLOM.addCoach(coachLOM)) {
                    success = true;
                }else{
                    throw new Exception("Issue with JSON Data");
                }
            }

            JsonArray generalManagers = (JsonArray) jsonData.get("generalManagers");

            for (int i = 0; i < generalManagers.size(); i++) {
                String generalManager = generalManagers.get(i).getAsString();

                managerLOM = new GeneralManager(generalManager);

                if (leagueLOM.addGeneralManager(managerLOM)) {
                    success = true;
                } else {
                    throw new Exception("Issue with JSON Data");
                }
            }

            if (leagueModel.setCurrentLeague(leagueLOM)) {
                success = true;
            } else {
                throw new Exception("Issue with JSON Data");
            }
        } catch (Exception e){
            success = false;
        }

        return success;
    }
}
