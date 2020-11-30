package com.groupten.statemachine.simulation.draft.strategy;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModelFactory;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.ITeamRoster;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.Comparator;
import java.util.List;

public class WeakTeamPicksFirstStrategy implements IDraftStrategy {
    @Override
    public void execute(List<TeamStanding> teamStandings, List<Player> players) {
        ILeagueModelFactory leagueModelFactory = Injector.instance().getLeagueModelFactory();
        ITeamRoster teamRoster = leagueModelFactory.createTeamRoster();
        players.sort(Comparator.comparingDouble(Player::calculateStrength).reversed());

        for (TeamStanding teamStanding : teamStandings) {
            Team team = teamStanding.getTeam();

            List<Player> activePlayers = team.getActivePlayers();
            List<Player> inActivePlayers = team.getInActivePlayers();
            activePlayers.addAll(inActivePlayers);

            Player bestPlayer = players.get(0);
            System.out.println(bestPlayer.getPlayerName() + " drafted by " + team.getTeamName());
            activePlayers.add(bestPlayer);

            teamRoster.setPlayers(activePlayers);
            team.setActivePlayers(teamRoster.createActivePlayerRoster());
            team.setInActivePlayers(teamRoster.createInActivePlayerRoster());

            players.remove(bestPlayer);
        }
    }
}
