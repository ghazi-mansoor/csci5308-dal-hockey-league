package com.groupten.statemachine.simulation.draft.generateplayers;

import com.groupten.leagueobjectmodel.player.Player;

import java.util.List;

public interface IPlayersGenerator {
    List<Player> generatePlayers(int numberOfPlayers);
}
