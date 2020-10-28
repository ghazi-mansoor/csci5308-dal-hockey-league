package com.groupten.leagueobjectmodel.league;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.team.Team;
import com.sun.source.tree.Tree;

import java.util.*;

public class League {
    private int leagueID;
    private String leagueName;
    private Map<String, Conference> conferences = new HashMap<>();
    private List<Player> freeAgents = new ArrayList<>();
    private List<Coach> coaches = new ArrayList<>();
    private List<GeneralManager> generalManagers = new ArrayList<>();
    private List<Season> seasons = new ArrayList<>();

    private int averageRetirementAge;
    private int maximumAge;
    private double randomWinChance;
    private double randomInjuryChance;
    private int injuryDaysLow;
    private int injuryDaysHigh;
    private int daysUntilStatIncreaseCheck;
    private int lossPoint;
    private double randomTradeOfferChance;
    private int maxPlayersPerTrade;
    private double randomAcceptanceChance;

    public League(String leagueName) {
        this.leagueName = leagueName;
    }

    public League(String leagueName, int averageRetirementAge, int maximumAge, double randomWinChance, double randomInjuryChance,
                  int injuryDaysLow, int injuryDaysHigh, int daysUntilStatIncreaseCheck, int lossPoint, double randomTradeOfferChance,
                  int maxPlayersPerTrade, double randomAcceptanceChance) {
        this(leagueName);
        this.averageRetirementAge = averageRetirementAge;
        this.maximumAge = maximumAge;
        this.randomWinChance = randomWinChance;
        this.randomInjuryChance = randomInjuryChance;
        this.injuryDaysLow = injuryDaysLow;
        this.injuryDaysHigh = injuryDaysHigh;
        this.daysUntilStatIncreaseCheck = daysUntilStatIncreaseCheck;
        this.lossPoint = lossPoint;
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.maxPlayersPerTrade = maxPlayersPerTrade;
        this.randomAcceptanceChance = randomAcceptanceChance;
    }

    public League(int leagueID, String leagueName, int averageRetirementAge, int maximumAge, double randomWinChance, double randomInjuryChance,
                  int injuryDaysLow, int injuryDaysHigh, int daysUntilStatIncreaseCheck, int lossPoint, double randomTradeOfferChance,
                  int maxPlayersPerTrade, double randomAcceptanceChance) {
        this(leagueName, averageRetirementAge, maximumAge, randomWinChance, randomInjuryChance, injuryDaysLow, injuryDaysHigh,
                daysUntilStatIncreaseCheck, lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance);
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

    public double getAverageRetirementAge() {
        return averageRetirementAge;
    }

    public void setAverageRetirementAge(int avgRA) {
        averageRetirementAge = avgRA;
    }

    public double getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int mA) {
        maximumAge = mA;
    }

    public double getRandomWinChance() {
        return randomWinChance;
    }

    public void setRandomWinChance(double randWC) {
        randomWinChance = randWC;
    }

    public double getRandomInjuryChance() {
        return randomInjuryChance;
    }

    public void setRandomInjuryChance(double randomIC) {
        randomInjuryChance = randomIC;
    }

    public int getInjuryDaysLow() {
        return injuryDaysLow;
    }

    public void setInjuryDaysLow(int iDL) {
        injuryDaysLow = iDL;
    }

    public int getInjuryDaysHigh() {
        return injuryDaysHigh;
    }

    public void setInjuryDaysHigh(int iDH) {
        injuryDaysHigh = iDH;
    }

    public int getDaysUntilStatIncreaseCheck() {
        return daysUntilStatIncreaseCheck;
    }

    public void setDaysUntilStatIncreaseCheck(int daysSIC) {
        daysUntilStatIncreaseCheck = daysSIC;
    }

    public int getLossPoint() {
        return lossPoint;
    }

    public void setLossPoint(int lP) {
        lossPoint = lP;
    }

    public double getRandomTradeOfferChance() {
        return randomTradeOfferChance;
    }

    public void setRandomTradeOfferChance(double randomTOC) {
        randomTradeOfferChance = randomTOC;
    }

    public double getRandomAcceptanceChance() {
        return randomAcceptanceChance;
    }

    public void setRandomAcceptanceChance(double randomAC) {
       randomAcceptanceChance = randomAC;
    }

    public int getMaxPlayersPerTrade() {
        return maxPlayersPerTrade;
    }

    public void setMaxPlayersPerTrade(int maxPPT) {
        maxPlayersPerTrade = maxPPT;
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
}
