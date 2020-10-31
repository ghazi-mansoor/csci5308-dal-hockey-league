package com.groupten.leagueobjectmodel.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
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
    private boolean retired;

    private final double gameConfigAverageRetirementAge = 35.0;
    private final double gameConfigMaxRetirementAge = 50.0;
    private final double randomInjuryChance = 0.05;
    private final int injuryDaysLow = 1;
    private final int injuryDaysHigh = 260;

    public Player() {
    }

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
        age += (days / 365.0);

        if (days > injuryPeriod) {
            removeInjury();
        }

        return shouldPlayerBeRetired();
    }

    private boolean shouldPlayerBeRetired() {
        double probabilityOfRetirement = calculateProbabilityOfRetirement();
        retired = age > gameConfigMaxRetirementAge || probabilityOfRetirement > 70;
        return retired;
    }

    private double calculateProbabilityOfRetirement() {
        double probability;
        if (age <= gameConfigAverageRetirementAge) {
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
            if (Math.random() < randomInjuryChance) {
                injured = true;
                setInjuryPeriod();
            } else {
                injured = false;
            }

            return injured;
        }
    }

    private void setInjuryPeriod() {
        Random ran = new Random();
        injuryPeriod = ran.nextInt(injuryDaysHigh) + injuryDaysLow;
    }

    private void removeInjury() {
        injured = false;
        injuryPeriod = 0;
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

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int pID) {
        playerID = pID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String pN) {
        playerName = pN;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String pos) {
        position = pos;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean cap) {
        captain = cap;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double a) {
        age = a;
    }

    public double getSkating() {
        return skating;
    }

    public void setSkating(double sk) {
        skating = sk;
    }

    public double getShooting() {
        return shooting;
    }

    public void setShooting(double sh) {
        shooting = sh;
    }

    public double getChecking() {
        return checking;
    }

    public void setChecking(double ch) {
        checking = ch;
    }

    public double getSaving() {
        return saving;
    }

    public void setSaving(double s) {
        saving = s;
    }

    public boolean isInjured() { return injured; }

    public void setInjured(boolean in) { injured = in; }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public boolean isRetired() { return retired; }

    public void setRetired(boolean retired) { this.retired = retired; };

    public boolean savePlayer() {
        System.out.println("Player saved to DB. playerID set to 1.");
        return true;
    }

}

