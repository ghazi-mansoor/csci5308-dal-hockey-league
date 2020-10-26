package com.groupten.leagueobjectmodel.schedule;

import java.util.Date;

public class Schedule {
    private Date gameDate;
    private String teamName1;
    private String  teamName2;

    public Schedule(Date gameDate, String teamName1, String  teamName2) {
        this.gameDate = gameDate;
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }
}
