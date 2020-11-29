package com.groupten.leagueobjectmodel.team;

import com.groupten.leagueobjectmodel.player.Player;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeamRoster implements ITeamRoster {
    private final int MAX_INACTIVE_PLAYERS = 10;
    private final int MAX_ACTIVE_FORWARD_PLAYERS = 12;
    private final int MAX_ACTIVE_DEFENSE_PLAYERS = 6;
    private final int MAX_ACTIVE_GOALIES = 2;

    private final List<Player> players;
    private List<Player> activePlayerRoster;
    private List<Player> inActivePlayerRooster;

    public TeamRoster(List<Player> players) {
        this.players = players;
        rankPlayersAccordingToStrength();
    }

    @Override
    public List<Player> createActivePlayerRoster() {
        List<Player> activeForwardPlayers = filterPlayersByPosition("forward").stream().limit(MAX_ACTIVE_FORWARD_PLAYERS).collect(Collectors.toList());
        List<Player> activeDefensePlayers = filterPlayersByPosition("defense").stream().limit(MAX_ACTIVE_DEFENSE_PLAYERS).collect(Collectors.toList());
        List<Player> activeGoaliePlayers = filterPlayersByPosition("goalie").stream().limit(MAX_ACTIVE_GOALIES).collect(Collectors.toList());

        this.activePlayerRoster = Stream.of(activeForwardPlayers, activeDefensePlayers, activeGoaliePlayers).flatMap(Collection::stream).collect(Collectors.toList());
        return this.activePlayerRoster;
    }

    @Override
    public List<Player> createInActivePlayerRoster() {
        for (Player player : activePlayerRoster) {
            this.players.remove(player);
        }

        this.inActivePlayerRooster = players.subList(0, MAX_INACTIVE_PLAYERS);
        return this.inActivePlayerRooster;
    }

    private void rankPlayersAccordingToStrength() {
        this.players.sort(Comparator.comparingDouble(Player::calculateStrength).reversed());
    }

    private List<Player> filterPlayersByPosition(String position) {
        return this.players.stream().filter(player -> player.getPosition().equals(position)).collect(Collectors.toList());
    }
}
