package com.groupten.leagueobjectmodel.teamindex;

import com.groupten.leagueobjectmodel.team.Team;

public class TeamIndex {
    private String leagueName;
    private String conferenceName;
    private String divisionName;
    private String teamName;

    public TeamIndex(){

    }

    public TeamIndex(String leagueName, String conferenceName, String divisionName, String teamName){
        this.leagueName = leagueName;
        this.conferenceName = conferenceName;
        this.divisionName = divisionName;
        this.teamName = teamName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
