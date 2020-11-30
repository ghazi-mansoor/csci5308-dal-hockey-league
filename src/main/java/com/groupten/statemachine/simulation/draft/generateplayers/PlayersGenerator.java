package com.groupten.statemachine.simulation.draft.generateplayers;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.player.PlayerPosition;

import java.util.List;

public class PlayersGenerator implements IPlayersGenerator {
    private int numberOfForwardPlayers;
    private int numberOfDefensePlayers;
    private int numberOfGoaliePlayers;

    public PlayersGenerator() {
        this.numberOfForwardPlayers = calculateNumberOfPlayersToGenerate(PlayerPosition.FORWARD.name());
        this.numberOfDefensePlayers = calculateNumberOfPlayersToGenerate(PlayerPosition.DEFENSE.name());
        this.numberOfGoaliePlayers = calculateNumberOfPlayersToGenerate(PlayerPosition.GOALIE.name());
    }

    @Override
    public List<Player> generatePlayers(int numberOfPlayers) {
        return null;
    }

    private int calculateNumberOfPlayersToGenerate(String position) {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        int numberOfTeams = leagueModel.getTotalNumberOfTeams();

        return 0;
    }

}
