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
        Team team_1 = teamList.get(0);
        Team team_2 = teamList.get(1);
        GameConfig.GameResolver gameResolver = season.getLeague().getGameResolverConfig();

        double randomWinChance = gameResolver.getRandomWinChance();

        if(new Random().nextDouble() > randomWinChance){
            if(team_1.getTeamStrength() > team_2.getTeamStrength()){
                recordWin(team_1);
                recordLoss(team_2);
            }else{
                recordWin(team_2);
                recordLoss(team_1);
            }
        }else{
            if(team_1.getTeamStrength() < team_2.getTeamStrength()){
                recordWin(team_1);
                recordLoss(team_2);
            }else{
                recordWin(team_2);
                recordLoss(team_1);
            }
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
