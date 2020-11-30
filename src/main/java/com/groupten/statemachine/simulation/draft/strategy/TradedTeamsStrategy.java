package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.List;
import java.util.Map;

public class TradedTeamsStrategy implements IDraftStrategy {

    @Override
    public void execute(List<TeamStanding> teamStandings, List<Player> players) {
        // Get all maps that traded in current round
        // if list of currentRoundTradePairs contains Key with TeamA:
            // find TeamA in teamStandings
            // Replace TeamA with TeamB
            // Do draft...
        // else
            // Do draft

    }
}
