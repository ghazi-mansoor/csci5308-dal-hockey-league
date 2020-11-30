package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.List;
import java.util.Map;

public class TradedTeamsStrategy implements IDraftStrategy {

    @Override
    public void execute(List<TeamStanding> teamStandings, List<Player> players, Map<Map<Team, Team>, Integer> tradedTeamsMap, int currentRound) {
        // if tradedTeamsMap has value currentRound
            // Traverse <Team, Team> key and find TeamA in teamStandings
            // Replace TeamA with TeamB
            // Do draft...
        // else
            // Do draft
    }
}
