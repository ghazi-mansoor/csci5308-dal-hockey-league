package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.List;

public class DraftContext implements IDraftContext {
    private IDraftStrategy strategy;

    @Override
    public void setStrategy(IDraftStrategy draftStrategy) {
        this.strategy = draftStrategy;
    }

    @Override
    public void executeStrategy(List<TeamStanding> teamStandings, List<Player> players) {
        strategy.execute(teamStandings, players);
    }
}
