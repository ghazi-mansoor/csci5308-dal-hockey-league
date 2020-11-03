package com.groupten.leagueobjectmodel.generalmanager;

public class GeneralManager {
    private int managerID;
    private String managerName;
    private int leagueID;
    private int teamID;

    public GeneralManager(String mN) {
        managerName = mN;
    }

    public GeneralManager(int mID, String mN) {
        this(mN);
        managerID = mID;
    }

    public static boolean isManagerNameValid(String mN) {
        if (mN.isEmpty() || mN.isBlank() || mN.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int mID) {
        managerID = mID;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String mN) {
        managerName = mN;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
