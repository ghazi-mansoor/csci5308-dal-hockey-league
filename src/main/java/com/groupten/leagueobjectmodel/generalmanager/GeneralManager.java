package com.groupten.leagueobjectmodel.generalmanager;

public class GeneralManager {
    private int managerID;
    private String managerName;
    private boolean isPartOfTeam;

    public GeneralManager(String mN, boolean isPartOfTeam) {
        managerName = mN;
        this.isPartOfTeam = isPartOfTeam;
    }

    public GeneralManager(int mID, String mN, boolean isPartOfTeam) {
        this(mN, isPartOfTeam);
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

    public boolean getManagerTeamStatus() {
        return isPartOfTeam;
    }

    public void setManagerTeamStatus(boolean partOfTeam) {
        isPartOfTeam = partOfTeam;
    }

}
