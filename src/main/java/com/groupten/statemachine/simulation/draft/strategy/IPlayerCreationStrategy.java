package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.leagueobjectmodel.player.Player;

import java.util.List;

public interface IPlayerCreationStrategy {
    List<Player> createPlayers();
}
