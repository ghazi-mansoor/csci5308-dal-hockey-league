package com.groupten.statemachine.simulation.draft.generateplayers;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.IPlayerBuilder;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.player.PlayerBuilder;
import com.groupten.leagueobjectmodel.player.PlayerPosition;
import com.groupten.statemachine.simulation.draft.DraftConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayersGenerator implements IPlayersGenerator {
    private Map<String, List<Player>> playersGenerated = new HashMap<>();
    private int numberOfForwardPlayers;
    private int numberOfDefensePlayers;
    private int numberOfGoaliePlayers;

    public PlayersGenerator() {
        this.numberOfForwardPlayers = calculateNumberOfPlayersToGenerate(PlayerPosition.FORWARD.name());
        this.numberOfDefensePlayers = calculateNumberOfPlayersToGenerate(PlayerPosition.DEFENSE.name());
        this.numberOfGoaliePlayers = calculateNumberOfPlayersToGenerate(PlayerPosition.GOALIE.name());
    }

    @Override
    public Map<String, List<Player>> generatePlayers() {
        List<Player> forwardPlayers = buildPlayersForPosition(PlayerPosition.FORWARD.name());
        playersGenerated.put("forward players", forwardPlayers);
        return playersGenerated;
    }

    private List<Player> buildPlayersForPosition(String position) {
        List<Player> players = new ArrayList<>();
        IPlayerBuilder playerBuilder = Injector.instance().getPlayerBuilder();

        if (position.equals(PlayerPosition.FORWARD.name())) {
            double minimumAge = ForwardPlayerStatsRanges.MINIMUM_AGE.getDoubleValue();
            double maximumAge = ForwardPlayerStatsRanges.MAXIMUM_AGE.getDoubleValue();
            double minimumSkatingStat = ForwardPlayerStatsRanges.MINIMUM_SKATING_STAT.getDoubleValue();
            double maximumSkatingStat = ForwardPlayerStatsRanges.MAXIMUM_SKATING_STAT.getDoubleValue();
            double minimumShootingStat = ForwardPlayerStatsRanges.MINIMUM_SHOOTING_STAT.getDoubleValue();
            double maximumShootingStat = ForwardPlayerStatsRanges.MAXIMUM_SHOOTING_STAT.getDoubleValue();
            double minimumCheckingStat = ForwardPlayerStatsRanges.MINIMUM_CHECKING_STAT.getDoubleValue();
            double maximumCheckingStat = ForwardPlayerStatsRanges.MAXIMUM_CHECKING_STAT.getDoubleValue();
            double minimumSavingStat = ForwardPlayerStatsRanges.MINIMUM_SAVING_STAT.getDoubleValue();
            double maximumSavingStat = ForwardPlayerStatsRanges.MAXIMUM_SAVING_STAT.getDoubleValue();
        }

        for (int i = 0; i < numberOfForwardPlayers; i++) {
            playerBuilder.reset();
            playerBuilder.setProfile("Certain Player", position);
        }

        return null;
    }

    private int calculateNumberOfPlayersToGenerate(String position) {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        double percentageOfTotalPlayers = 0.0;

        switch (position) {
            case "forward":
                percentageOfTotalPlayers = DraftConstants.PERCENTAGE_OF_FORWARD_PLAYERS.getDoubleValue();
                break;
            case "defense":
                percentageOfTotalPlayers = DraftConstants.PERCENTAGE_OF_DEFENSE_PLAYERS.getDoubleValue();
                break;
            case "goalie":
                percentageOfTotalPlayers = DraftConstants.PERCENTAGE_OF_GOALIE_PLAYERS.getDoubleValue();
                break;
        }

        return (int) Math.round(DraftConstants.NUMBER_OF_ROUNDS.getIntValue() * leagueModel.getTotalNumberOfTeams() * percentageOfTotalPlayers);
    }



}
