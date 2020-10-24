package com.groupten.leagueobjectmodel.coach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coach {
    private int coachID;
    private String coachName;
    private double skating;
    private double shooting;
    private double checking;
    private double saving;
    private boolean isPartOfTeam;

    public Coach(String n, double sk, double sh, double ch, double sa, boolean isPartOfTeam) {
        coachName = n;
        skating = sk;
        shooting = sh;
        checking = ch;
        saving = sa;
    }

    public Coach(int cID, String cN, double sk, double sh, double ch, double sa, boolean isPartOfTeam) {
        this(cN, sk, sh, ch, sa, isPartOfTeam);
        coachID = cID;
    }

    public static boolean areCoachFieldsValid(String cN, double sk, double sh, double ch, double sa) {
        return isCoachNameValid(cN) && areStatsValid(sk, sh, ch, sa);
    }

    private static boolean isCoachNameValid(String pN) {
        if (pN.isEmpty() || pN.isBlank() || pN.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean areStatsValid(double ...args) {
        List<Boolean> validChecks = new ArrayList<>();

        for (double stat : args) {
            validChecks.add(stat >= 0.0 && stat <= 1.0);
        }

        return Collections.frequency(validChecks, false) == 0;
    }

    public int getCoachID() {
        return coachID;
    }

    public void setCoachID(int cID) {
        coachID = cID;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String n) {
        coachName = n;
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

    public void setSaving(double sa) {
        saving = sa;
    }

    public boolean getCoachTeamStatus() {
        return isPartOfTeam;
    }

    public void setCoachTeamStatus(boolean partOfTeam) {
        isPartOfTeam = partOfTeam;
    }

}
