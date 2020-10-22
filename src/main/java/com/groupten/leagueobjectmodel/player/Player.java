package com.groupten.leagueobjectmodel.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private int playerID;
    private String playerName;
    private String position;
    private boolean captain;
    private int age;
    private int skating;
    private int shooting;
    private int checking;
    private int saving;
    private int playerStrength;

    public Player(String pN, String pos, boolean cap, int a, int sk, int sh, int ch, int sa) {
        playerName = pN;
        position = pos;
        captain = cap;
        age = a;
        skating = sk;
        shooting = sh;
        checking = ch;
        saving = sa;
    }

    public Player(int pID, String pN, String pos, boolean cap, int a, int sk, int sh, int ch, int sa) {
        this(pN, pos, cap, a, sk, sh, ch, sa);
        playerID = pID;
    }

    public static boolean arePlayerFieldsValid(String pN, String pos, boolean cap, int a, int sk, int sh, int ch, int sa) {
        return isPlayerNameValid(pN) && isPositionValid(pos) && areStatsValid(a, sk, sh, ch, sa);
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

    private static boolean areStatsValid(int ...args) {
        List<Boolean> validChecks = new ArrayList<>();

         for (int stat : args) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int a) {
        age = a;
    }

    public int getSkating() {
        return skating;
    }

    public void setSkating(int sk) {
        skating = sk;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int sh) {
        shooting = sh;
    }

    public int getChecking() {
        return checking;
    }

    public void setChecking(int ch) {
        checking = ch;
    }

    public int getSaving() {
        return saving;
    }

    public void setSaving(int s) {
        saving = s;
    }

	public int getPlayerStrength() {
		return playerStrength;
	}

	public void setPlayerStrength(int playerStrength) {
		this.playerStrength = playerStrength;
	}
    
    
}
