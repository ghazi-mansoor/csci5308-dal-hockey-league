package com.groupten.leagueobjectmodel.schedule;

import java.util.Date;
import java.util.HashSet;

public class Schedule {
    private Date gameDate;
    private HashSet<String> teamNames = new HashSet<>();

    public Schedule(){
        gameDate = null;
    }

    public Schedule(Date date){
        gameDate = new Date();
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public boolean addTeamName(String teamName){
        if(teamNames.size() <=2){
            teamNames.add(teamName);
            return true;
        }else{
            return false;
        }
    }

    public HashSet<String> getTeamNames() {
        return teamNames;
    }
}
