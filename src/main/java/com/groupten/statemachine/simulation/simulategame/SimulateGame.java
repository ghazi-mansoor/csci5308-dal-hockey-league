package com.groupten.statemachine.simulation.simulategame;

import com.groupten.IO.console.IConsole;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
        Team team1 = teamList.get(0);
        Team team2 = teamList.get(1);

        ResolveGame resolveGame = new ResolveGame();
        Team winner = resolveGame.getWinner(teamList.get(0),teamList.get(1));
        if(winner == team1){
            recordWin(team1);
            recordLoss(team2);
        }else{
            recordWin(team2);
            recordLoss(team1);
        }
    }

    private void recordWin(Team team){
        season.recordWin(team);
        team.setWinPoint( team.getWinPoint() + 1);
        System.out.println("Team won : "+team.getTeamName());
    }

    private void recordLoss(Team team){
        team.setLossPoint( team.getLossPoint() + 1);
        System.out.println("Team lost : "+team.getTeamName());
    }
}
