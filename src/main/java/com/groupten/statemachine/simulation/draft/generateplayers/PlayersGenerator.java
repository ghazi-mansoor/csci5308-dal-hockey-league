package com.groupten.statemachine.simulation.draft.generateplayers;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.IPlayerBuilder;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.player.PlayerPosition;
import com.groupten.statemachine.simulation.draft.DraftConstants;

import java.util.*;

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
        List<Player> forwardPlayers = buildPlayersForPosition(PlayerPosition.FORWARD.name(), numberOfForwardPlayers);
        playersGenerated.put("forward players", forwardPlayers);
        return playersGenerated;
    }

    private List<Player> buildPlayersForPosition(String position, int numberOfPlayers) {
        List<Player> players = new ArrayList<>();
        IPlayerBuilder playerBuilder = Injector.instance().getPlayerBuilder();
        double minimumAge = 0.0;
        double maximumAge = 0.0;
        double minimumSkatingStat = 0.0;
        double maximumSkatingStat = 0.0;
        double minimumShootingStat = 0.0;
        double maximumShootingStat = 0.0;
        double minimumCheckingStat = 0.0;
        double maximumCheckingStat = 0.0;
        double minimumSavingStat = 0.0;
        double maximumSavingStat = 0.0;

        if (position.equals(PlayerPosition.FORWARD.name())) {
            minimumAge = ForwardPlayerStatsRanges.MINIMUM_AGE.getDoubleValue();
            maximumAge = ForwardPlayerStatsRanges.MAXIMUM_AGE.getDoubleValue();
            minimumSkatingStat = ForwardPlayerStatsRanges.MINIMUM_SKATING_STAT.getDoubleValue();
            maximumSkatingStat = ForwardPlayerStatsRanges.MAXIMUM_SKATING_STAT.getDoubleValue();
            minimumShootingStat = ForwardPlayerStatsRanges.MINIMUM_SHOOTING_STAT.getDoubleValue();
            maximumShootingStat = ForwardPlayerStatsRanges.MAXIMUM_SHOOTING_STAT.getDoubleValue();
            minimumCheckingStat = ForwardPlayerStatsRanges.MINIMUM_CHECKING_STAT.getDoubleValue();
            maximumCheckingStat = ForwardPlayerStatsRanges.MAXIMUM_CHECKING_STAT.getDoubleValue();
            minimumSavingStat = ForwardPlayerStatsRanges.MINIMUM_SAVING_STAT.getDoubleValue();
            maximumSavingStat = ForwardPlayerStatsRanges.MAXIMUM_SAVING_STAT.getDoubleValue();
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            playerBuilder.reset();
            String playerName = "Player " + i;
            playerBuilder.setProfile(playerName, position);

            double playerAge = generateRandomValueBetweenInterval(minimumAge, maximumAge);
            playerBuilder.setAge(playerAge);

            double skatingStat = generateRandomValueBetweenInterval(minimumSkatingStat, maximumSkatingStat);
            double shootingStat = generateRandomValueBetweenInterval(minimumShootingStat, maximumShootingStat);
            double checkingStat = generateRandomValueBetweenInterval(minimumCheckingStat, maximumCheckingStat);
            double savingStat = generateRandomValueBetweenInterval(minimumSavingStat, maximumSavingStat);
            playerBuilder.setPlayerStats(skatingStat, shootingStat, checkingStat, savingStat);

            players.add(playerBuilder.getResult());
        }

        return players;
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

    private double generateRandomValueBetweenInterval(double intervalStart, double intervalEnd) {
        double random = new Random().nextDouble();
        return intervalStart + (random * (intervalEnd - intervalStart));
    }

}
