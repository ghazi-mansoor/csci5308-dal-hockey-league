package com.groupten.statemachine.simulation.simulategame;

import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SimulateGame implements ISimulateGame {
    private Season season;

    public Season getSeason() {
        return season;
    }

    @Override
    public void setSeason(Season season){
        this.season = season;
    }

    @Override
    public void simulateGame(Schedule schedule) {
        HashSet<Team> teams = schedule.getTeams();
        List<Team> teamList = new ArrayList<>(teams);
        Team team_1 = teamList.get(0);
        Team team_2 = teamList.get(1);
        if(team_1.getTeamStrength() > team_2.getTeamStrength()){
            recordWin(team_1);
            recordLoss(team_2);
        }else{
            recordWin(team_2);
            recordLoss(team_1);
        }

    }

    private void recordWin(Team team){
        season.recordWin(team);
    }

    private void recordLoss(Team team){
        team.setLossPoint( team.getLossPoint() + 1);
    }
}
