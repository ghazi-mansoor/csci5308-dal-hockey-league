package com.groupten.statemachine.simulation.simulategame;

import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.statemachine.simulation.simulategame.strategy.AlgoStrategy;
import com.groupten.statemachine.simulation.simulategame.strategy.IStrategy;

public class ResolveGame implements IResolveGame {
    private IStrategy strategy;
    private final Season season;

    public ResolveGame(Season season){
        this.strategy = new AlgoStrategy();
        this.season = season;
    }

    @Override
    public IStrategy getStrategy() {
        return strategy;
    }

    @Override
    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Team getWinner(Team team1, Team team2){
        return strategy.getWinner(this.season, team1, team2);
    }
}
