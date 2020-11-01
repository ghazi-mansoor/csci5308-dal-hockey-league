package com.groupten.leagueobjectmodel.league;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.season.Season;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {
    private int leagueID;
    private String leagueName;
    private Map<String, Conference> conferences = new HashMap<>();
    private List<Player> freeAgents = new ArrayList<>();
    private List<Coach> coaches = new ArrayList<>();
    private List<GeneralManager> generalManagers = new ArrayList<>();
    private List<Season> seasons = new ArrayList<>();
    private GameConfig.Aging agingConfig;
    private GameConfig.GameResolver gameResolverConfig;
    private GameConfig.Injuries injuriesConfig;
    private GameConfig.Training trainingConfig;
    private GameConfig.Trading tradingConfig;

    /* private int averageRetirementAge = 35;
    private int maximumAge = 50;
    private double randomWinChance = 0.1;
    private double randomInjuryChance = 0.05;
    private int injuryDaysLow = 1;
    private int injuryDaysHigh = 260;
    private int daysUntilStatIncreaseCheck = 100;
    private int lossPoint = 8;
    private double randomTradeOfferChance = 0.05;
    private int maxPlayersPerTrade = 2;
    private double randomAcceptanceChance = 0.05; */

    public League(String leagueName) {
        this.leagueName = leagueName;
    }

    public League(int leagueID, String leagueName) {
        this(leagueName);
        this.leagueID = leagueID;
    }

    public boolean addConference(Conference conference) {
        if (Conference.isConferenceNameValid(conference.getConferenceName())){
            String conferenceName = conference.getConferenceName();
            int initialSize = conferences.size();
            conferences.put(conferenceName, conference);
            return conferences.size() > initialSize;
        } else{
            return false;
        }
    }

    public boolean addFreeAgent(Player player) {
        if(Player.arePlayerFieldsValid(player.getPlayerName(), player.getPosition(),
                player.getSkating(), player.getShooting(), player.getChecking(), player.getSaving())){
            int initialSize = freeAgents.size();
            freeAgents.add(player);
            return freeAgents.size() > initialSize;
        }else{
            return false;
        }
    }

    public boolean addCoach(Coach coach) {
        int initialSize = coaches.size();
        coaches.add(coach);
        return coaches.size() > initialSize;
    }

    public boolean addGeneralManager(GeneralManager generalManager) {
        int initialSize = generalManagers.size();
        generalManagers.add(generalManager);
        return generalManagers.size() > initialSize;
    }

    public boolean addSeason(Season season) {
        int initialSize = seasons.size();
        this.seasons.add(season);
        return seasons.size() > initialSize;
    }

    public boolean isNumberOfConferencesEven() {
        return conferences.size() % 2 == 0;
    }

    public boolean containsConference(String conferenceName) {
        return conferences.containsKey(conferenceName);
    }

    public Conference getConference(String conferenceName) {
        return conferences.get(conferenceName);
    }

    public Map<String, Conference> getConferences() {
        return conferences;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Player> getFreeAgents() {
        return freeAgents;
    }

    public List<Player> getFreeAgentsGoalies() {
        List<Player> goalies = new ArrayList<>();
        for(Player freeAgent : freeAgents){
            if(freeAgent.getPosition().equals("goalie")){
                goalies.add(freeAgent);
            }
        }
        return goalies;
    }

    public List<Player> getFreeAgentsSkaters() {
        List<Player> skaters = new ArrayList<>();
        for(Player freeAgent : freeAgents){
            if(freeAgent.getPosition().equals("forward") || freeAgent.getPosition().equals("defense")){
                skaters.add(freeAgent);
            }
        }
        return skaters;
    }

    public List<GeneralManager> getGeneralManagers() {
        return generalManagers;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public static boolean isLeagueNameValid(String lN) {
        if (lN.isEmpty() || lN.isBlank() || lN.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String lN) {
        leagueName = lN;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int lID) {
        leagueID = lID;
    }

    public void removeGeneralManager(GeneralManager generalManager){
        generalManagers.remove(generalManager);
    }

    public void removeCoach(Coach coach){
        coaches.remove(coach);
    }

    public void removeFreeAgent(Player player){
        freeAgents.remove(player);
    }

    public boolean saveLeague() {
        System.out.println("League saved to DB. leagueID set to 1.");
        return true;
    }

    public GameConfig.Aging getAgingConfig() {
        return agingConfig;
    }

    public void setAgingConfig(GameConfig.Aging agingConfig) {
        this.agingConfig = agingConfig;
    }

    public GameConfig.GameResolver getGameResolverConfig() {
        return gameResolverConfig;
    }

    public void setGameResolverConfig(GameConfig.GameResolver gameResolverConfig) {
        this.gameResolverConfig = gameResolverConfig;
    }

    public GameConfig.Injuries getInjuriesConfig() {
        return injuriesConfig;
    }

    public void setInjuriesConfig(GameConfig.Injuries injuriesConfig) {
        this.injuriesConfig = injuriesConfig;
    }

    public GameConfig.Training getTrainingConfig() {
        return trainingConfig;
    }

    public void setTrainingConfig(GameConfig.Training trainingConfig) {
        this.trainingConfig = trainingConfig;
    }

    public GameConfig.Trading getTradingConfig() {
        return tradingConfig;
    }

    public void setTradingConfig(GameConfig.Trading tradingConfig) {
        this.tradingConfig = tradingConfig;
    }
}
