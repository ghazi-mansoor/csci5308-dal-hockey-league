package com.groupten.leagueobjectmodel.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private int playerID;
    private String playerName;
    private String position;
    private boolean captain;
    private double age;
    private double skating;
    private double shooting;
    private double checking;
    private double saving;

    public Player(String pN, String pos, double a, double sk, double sh, double ch, double sa) {
        playerName = pN;
        position = pos;
        age = a;
        skating = sk;
        shooting = sh;
        checking = ch;
        saving = sa;
    }

    public Player(int pID, String pN, String pos, double a, double sk, double sh, double ch, double sa) {
        this(pN, pos, a, sk, sh, ch, sa);
        playerID = pID;
    }

    public Player(String pN, String pos, boolean cap, double a, double sk, double sh, double ch, double sa) {
        this(pN, pos, a, sk, sh, ch, sa);
        captain = cap;
    }

    public Player(int pID, String pN, String pos, boolean cap, double a, double sk, double sh, double ch, double sa) {
        this(pN, pos, cap, a, sk, sh, ch, sa);
        playerID = pID;
    }

    public void increaseAge() {
        age += (1.0 / 365.0);
    }

    public static boolean arePlayerFieldsValid(String pN, String pos, double sk, double sh, double ch, double sa) {
        return isPlayerNameValid(pN) && isPositionValid(pos) && areStatsValid(sk, sh, ch, sa);
    }

    private static boolean isPlayerNameValid(String pN) {
        if (pN.isEmpty() || pN.isBlank() || pN.toLowerCase().equals("null")) {
           return false;
        } else {
            return true;
        }
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
}
