package com.groupten.statemachine.jsonimport;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupten.persistence.dao.ILeagueDAO;
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

public class JSONImport implements IJSONImport {

    private JsonObject jsonData;

    public JSONImport() {
    }

    public JSONImport(ILeagueDAO leagueDBMockObj) {
        Injector.instance().setLeagueDatabaseObject(leagueDBMockObj);
    }

    @Override
    public boolean importJSONData(String path) {
        JsonParser parser = new JsonParser();

        try {
            jsonData = (JsonObject) parser.parse(new FileReader(path));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isLeagueNameUnique() {
        ILeagueDAO leagueDB = Injector.instance().getLeagueDatabaseObject();
        String columnName = "leagueName";
        String leagueName = jsonData.get("leagueName").getAsString();
        List<HashMap<String, Object>> leagues = leagueDB.getLeagues(columnName, leagueName);
        return leagues.size() == 0;
    }

    @Override
    public boolean instantiateJSONData() {

        boolean leagueAdded = false, conferenceAdded = false, divisionAdded = false, teamAdded = false,
                managerAdded = false, coachAdded = false, playerAdded = false;

        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League leagueLOM;
        Conference conferenceLOM;
        Division divisionLOM;
        GeneralManager managerLOM;
        Coach coachLOM;
        Player playerLOM;
        Team teamLOM;

        JsonObject conference, division, team, headCoach, coach, teamPlayer, freeAgent;
        JsonObject gamePlayConfig, aging, gameResolver, injuries, training, trading;
        JsonArray conferences, divisions, teams, players;

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

        leagueLOM = new League(leagueName, averageRetirementAge, maximumAge, randomWinChance, randomInjuryChance, injuryDaysLow,
                injuryDaysHigh, daysUntilStatIncreaseCheck, lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance);

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
                        managerAdded = true;
                    } else {
                        return false;
                    }

                    if (teamLOM.setHeadCoach(coachLOM)) {
                        coachAdded = true;
                    } else {
                        return false;
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
                            playerAdded = true;
                        } else {
                            return false;
                        }
                    }

                    if (divisionLOM.addTeam(teamLOM)) {
                        teamAdded = true;
                    } else {
                        return false;
                    }
                }
                if (conferenceLOM.addDivision(divisionLOM)) {
                    divisionAdded = true;
                } else {
                    return false;
                }
            }
            if (leagueLOM.addConference(conferenceLOM)) {
                conferenceAdded = true;
            } else {
                return false;
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
                playerAdded = true;
            } else {
                return false;
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
                coachAdded = true;
            } else {
                return false;
            }
        }

        JsonArray generalManagers = (JsonArray) jsonData.get("generalManagers");

        for (int i = 0; i < generalManagers.size(); i++) {
            String generalManager = generalManagers.get(i).getAsString();

            managerLOM = new GeneralManager(generalManager);

            if (leagueLOM.addGeneralManager(managerLOM)) {
                managerAdded = true;
            } else {
                return false;
            }
        }

        if (leagueModel.setCurrentLeague(leagueLOM)) {
            leagueAdded = true;
        } else {
            return false;
        }

        return leagueAdded & conferenceAdded && divisionAdded && teamAdded && managerAdded && coachAdded && playerAdded;
    }
}
