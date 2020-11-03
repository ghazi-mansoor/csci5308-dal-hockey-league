package com.groupten.leagueobjectmodel.player;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.persistence.dao.IPlayerDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
    private final double NUMBER_OF_DAYS_PER_YEAR = 365.0;
    private final double PROBABILITY_THRESHOLD_FOR_RETIRING_PLAYER = 70.0;

    private int playerID;
    private int teamID;
    private String playerName;
    private String position;
    private boolean captain;
    private double age;
    private double skating;
    private double shooting;
    private double checking;
    private double saving;
    private boolean injured;
    private int injuryPeriod;

    public Player() {}

    public Player(String playerName, String position, double age, double skating, double shooting, double checking, double saving) {
        this.playerName = playerName;
        this.position = position;
        this.age = age;
        this.skating = skating;
        this.shooting = shooting;
        this.checking = checking;
        this.saving = saving;
    }

    public Player(int playerID, String playerName, String position, double age, double skating, double shooting,
                  double checking, double saving) {
        this(playerName, position, age, skating, shooting, checking, saving);
        this.playerID = playerID;
    }

    public Player(String playerName, String position, boolean captain, double age, double skating, double shooting,
                  double checking, double saving) {
        this(playerName, position, age, skating, shooting, checking, saving);
        this.captain = captain;
    }

    public Player(int playerID, String playerName, String position, boolean captain, double age, double skating, double shooting,
                  double checking, double saving) {
        this(playerName, position, captain, age, skating, shooting, checking, saving);
        this.playerID = playerID;
    }

    public boolean increaseAgeAndCheckIfPlayerShouldBeRetired(int days) {
        age += (days / NUMBER_OF_DAYS_PER_YEAR);

        if (days > injuryPeriod) {
            removeInjury();
        }

        return shouldPlayerBeRetired();
    }

    private boolean shouldPlayerBeRetired() {
        double probabilityOfRetirement = calculateProbabilityOfRetirement();
        GameConfig.Aging agingConfig = getAgingConfig();
        return age > agingConfig.getMaximumAge() || probabilityOfRetirement > PROBABILITY_THRESHOLD_FOR_RETIRING_PLAYER;
    }

    private double calculateProbabilityOfRetirement() {
        double probability;
        GameConfig.Aging agingConfig = getAgingConfig();

        if (age <= agingConfig.getAverageRetirementAge()) {
            probability = 0.8571 * age;
        } else {
            probability = 4.6666 * age - 133.3;
        }

        return probability;
    }

    public boolean checkInjury() {
        if (injured) {
            return true;
        } else {
            GameConfig.Injuries injuriesConfig = getInjuriesConfig();

            if (Math.random() < injuriesConfig.getRandomInjuryChance()) {
                injured = true;
                setInjuryPeriod();
            } else {
                injured = false;
            }

            return injured;
        }
    }

    private void setInjuryPeriod() {
        GameConfig.Injuries injuriesConfig = getInjuriesConfig();
        Random ran = new Random();
        injuryPeriod = ran.nextInt(injuriesConfig.getInjuryDaysHigh()) + injuriesConfig.getInjuryDaysLows();
    }

    private void removeInjury() {
        injured = false;
        injuryPeriod = 0;
    }

    private GameConfig.Aging getAgingConfig() {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = leagueModel.getCurrentLeague();
        return league.getAgingConfig();
    }

    private GameConfig.Injuries getInjuriesConfig() {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = leagueModel.getCurrentLeague();
        return league.getInjuriesConfig();
    }

    public double calculateStrength() {
        double strength = 0.0;

        switch (position) {
            case "forward":
                strength = skating + shooting + (checking / 2);
                break;
            case "defense":
                strength = skating + shooting + (shooting / 2);
                break;
            case "goalie":
                strength = skating + saving;
                break;
        }

        return strength;
    }

    public static boolean arePlayerFieldsValid(String pN, String pos, double sk, double sh, double ch, double sa) {
        return isPlayerNameValid(pN) && isPositionValid(pos) && areStatsValid(sk, sh, ch, sa);
    }

    private static boolean isPlayerNameValid(String pN) {
        boolean isValid;
        if (pN.isEmpty() || pN.isBlank() || pN.toLowerCase().equals("null")) {
            isValid = false;
        } else {
            isValid = true;
        }

        return isValid;
    }

    private static boolean isPositionValid(String pos) {
        String positionLowerCased = pos.toLowerCase();
        return positionLowerCased.equals("goalie") || positionLowerCased.equals("forward") || positionLowerCased.equals("defense");
    }

    private static boolean areStatsValid(double ...args) {
        List<Boolean> validChecks = new ArrayList<>();

        for (double stat : args) {
            validChecks.add(stat >= 1 && stat <= 20);
        }

        return Collections.frequency(validChecks, false) == 0;
    }

    public boolean savePlayer() {
        IPlayerDAO playerDAO = Injector.instance().getPlayerDatabaseObject();
        playerID = playerDAO.createPlayer(playerName, position, age, skating, shooting, checking, saving);
        if (playerID != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getAge() {
        return age;
    }

    public void setSkating(double skating) {
        this.skating = skating;
    }

    public double getSkating() {
        return skating;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public double getShooting() {
        return shooting;
    }

    public void setChecking(double checking) {
        this.checking = checking;
    }

    public double getChecking() {
        return checking;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }

    public double getSaving() {
        return saving;
    }

    public void setInjuryPeriod(int injuryPeriod) {
        this.injuryPeriod = injuryPeriod;
    }

    public int getInjuryPeriod() {
        return injuryPeriod;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }


}

