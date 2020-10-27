package com.groupten.statemachine.simulation.aging;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Aging implements IAging {

    @Override
    public void advanceEveryPlayersAge(League league, int days) {
        advanceTeamPlayerAges(league, days);
        advanceFreeAgentAges(league, days);
    }

    private void advanceTeamPlayerAges(League league, int days) {
        Map<String, Conference> conferences = league.getConferences();
        List<Player> freeAgents = league.getFreeAgents();
        List<Player> updatedPlayersList = new ArrayList<Player>();

        for (Conference conference : conferences.values()) {
            Map<String, Division> divisions = conference.getDivisions();
            for (Division division : divisions.values()) {
                Map<String, Team> teams = division.getTeams();
                for (Team team : teams.values()) {
                    List<Player> players = team.getPlayers();
                    for (Player player : players) {
                        boolean playerShouldRetire = player.increaseAgeAndCheckIfPlayerShouldBeRetired(days);
                        if (playerShouldRetire) {
                            Player bestFreeAgent = findBestFreeAgent(freeAgents, player);
                            updatedPlayersList.add(bestFreeAgent);
                            freeAgents.remove(bestFreeAgent);
                        } else {
                            updatedPlayersList.add(player);
                        }
                    }
                    team.setPlayers(updatedPlayersList);
                }
            }
        }
    }

    private void advanceFreeAgentAges(League league, int days) {
        List<Player> freeAgents = league.getFreeAgents();
        freeAgents.removeIf(player -> player.increaseAgeAndCheckIfPlayerShouldBeRetired(days));
    }

    private Player findBestFreeAgent(List<Player> freeAgents, Player player) {
         String playerPosition = player.getPosition();
         boolean isPlayerCaptain = player.isCaptain();

         Predicate<Player> byPosition = freeAgent -> freeAgent.getPosition().equals(playerPosition);
         List<Player> freeAgentsWithSamePosition = freeAgents.stream().filter(byPosition).collect(Collectors.toList());

         TreeMap<Double, Player> freeAgentsRanked = new TreeMap<Double, Player>();

         for (Player pl : freeAgentsWithSamePosition) {
             double strength = pl.calculateStrength();
             freeAgentsRanked.put(strength, pl);
         }

         Map.Entry<Double, Player> bestFreeAgentEntry = freeAgentsRanked.lastEntry();
         Player bestFreeAgent = bestFreeAgentEntry.getValue();
         bestFreeAgent.setCaptain(isPlayerCaptain);

         return bestFreeAgent;
    }

}
