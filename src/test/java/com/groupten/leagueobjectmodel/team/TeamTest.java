package com.groupten.leagueobjectmodel.team;

import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TeamTest {

    @Test
    public void addPlayerToTeamTest() {
        Team team = new Team("Team 1", "Bob", "Marley");
        Player player = new Player("Player 1", "goalie", false);

        assertTrue(team.addPlayerToTeam(player));
    }

    @Test
    public void isOnlyOnePlayerCaptainTest() {
        Team team = new Team("Team 1", "Bob", "Marley");
        Player player1 = new Player("Player 1", "goalie", false);
        Player player2 = new Player("Player 1", "goalie", true);

        team.addPlayerToTeam(player1);
        team.addPlayerToTeam(player2);

        assertTrue(team.isOnlyOnePlayerCaptain());
    }

}
