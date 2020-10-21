package com.groupten.leagueobjectmodel.league;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;

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

    private double averageRetirementAge;
    private double maximumAge;
    private double randomWinChance;
    private double randomInjuryChance;
    private int injuryDaysLow;
    private int injuryDaysHigh;
    private int daysUntilStatIncreaseCheck;
    private int lossPoint;
    private double randomTradeOfferChance;
    private int maxPlayersPerTrade;
    private double randomAcceptanceChance;

    public League(String lN, double avgRA, double mA, double randWC, double randIC ,int iDL, int iDH, int daysUSIC, int lP
    , double randTOC, int maxPPT, double randAC) {
        leagueName = lN;
        averageRetirementAge = avgRA;
        maximumAge = mA;
        randomWinChance = randWC;
        randomInjuryChance = randIC;
        injuryDaysLow = iDL;
        injuryDaysHigh = iDH;
        daysUntilStatIncreaseCheck = daysUSIC;
        lossPoint = lP;
        randomTradeOfferChance = randTOC;
        maxPlayersPerTrade = maxPPT;
        randomAcceptanceChance = randAC;
    }

    public League(int lID, String lN, double avgRA, double mA, double randWC, double randIC ,int iDL, int iDH, int daysUSIC, int lP
            ,double randTOC, int maxPPT, double randAC) {
        this(lN, avgRA, mA, randWC, randIC, iDL, iDH, daysUSIC, lP, randTOC, maxPPT, randAC);
        leagueID = lID;
    }

    public boolean addConference(Conference conference) {
        String conferenceName = conference.getConferenceName();
        int initialSize = conferences.size();
        conferences.put(conferenceName, conference);

        return conferences.size() > initialSize;
    }

    public boolean addFreeAgent(Player player) {
        int initialSize = freeAgents.size();
        freeAgents.add(player);
        return freeAgents.size() > initialSize;
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

    public List<GeneralManager> getGeneralManagers() {
        return generalManagers;
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

    public void setAverageRetirementAge(double avgRA) {
        averageRetirementAge = avgRA;
    }

    public double getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(double mA) {
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
}
