package com.groupten.statemachine.simulation.simulategame;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.statemachine.simulation.simulategame.strategy.AlgoStrategy;
import com.groupten.statemachine.simulation.simulategame.strategy.IAlgoStrategyObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SimulateGame implements ISimulateGame {
    private static final Logger logger = LogManager.getLogger(SimulateGame.class.getName());
    private Season season;

    @Override
    public void simulateGame(Season season, Schedule schedule) {
        this.season = season;
        HashSet<Team> teams = schedule.getTeams();
        List<Team> teamList = new ArrayList<>(teams);
        Team team1 = teamList.get(0);
        Team team2 = teamList.get(1);

        IResolveGame resolveGame = new ResolveGame(season);
        AlgoStrategy algoStrategy = new AlgoStrategy();
        algoStrategy.attach(this.season.getSeasonStat());
        algoStrategy.attach((IAlgoStrategyObserver) Injector.instance().getTrophyObject());
        resolveGame.setStrategy(algoStrategy);
        Team winner = resolveGame.getWinner(teamList.get(0), teamList.get(1));

        if (winner == team1) {
            logger.info(team1.getTeamName() + " won against " + team2.getTeamName());
            recordWin(team1);
            recordLoss(team2);
        } else {
            logger.info(team2.getTeamName() + " won against " + team1.getTeamName());
            recordWin(team2);
            recordLoss(team1);
        }
    }

    private void recordWin(Team team) {
        season.recordWin(team);
        team.setWinPoint(team.getWinPoint() + 1);
    }

    private void recordLoss(Team team) {
        team.setLossPoint(team.getLossPoint() + 1);
    }
}
