package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.List;

public interface IDraftStrategy {
    void execute(List<TeamStanding> teamStandings, List<Player> players);
}
