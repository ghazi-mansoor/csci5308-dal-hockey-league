package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.List;

public interface IDraftContext {
    void setStrategy(IDraftStrategy draftStrategy);

    void executeStrategy(List<TeamStanding> teamStandings, List<Player> players);
}
