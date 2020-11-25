package com.groupten.statemachine.simulation.simulategame.strategy;

import com.groupten.leagueobjectmodel.team.Team;

public interface IStrategy {
    Team getWinner(Team team1, Team team2);
}
