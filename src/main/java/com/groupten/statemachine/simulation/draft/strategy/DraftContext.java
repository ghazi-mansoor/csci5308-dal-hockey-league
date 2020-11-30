package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.List;
import java.util.Map;

public class DraftContext implements IDraftContext {
    private IDraftStrategy strategy;

    @Override
    public void setStrategy(IDraftStrategy draftStrategy) {
        this.strategy = draftStrategy;
    }

    @Override
    public void executeStrategy(List<TeamStanding> teamStandings, List<Player> players, Map<Map<Team, Team>, Integer> tradedTeamsMap, int currentRound) {
        strategy.execute(teamStandings, players, tradedTeamsMap, currentRound);
    }
}
