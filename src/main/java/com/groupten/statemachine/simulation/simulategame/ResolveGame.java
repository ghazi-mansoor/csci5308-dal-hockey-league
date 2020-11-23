package com.groupten.statemachine.simulation.simulategame;

import com.groupten.leagueobjectmodel.team.Team;

public class ResolveGame {
    private IStrategy strategy;

    public ResolveGame(){
        strategy = new RandomStrategy();
    }

    public IStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public Team getWinner(Team team1, Team team2){
        return strategy.getWinner(team1, team2);
    }
}
