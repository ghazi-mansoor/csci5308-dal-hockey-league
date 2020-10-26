package com.groupten.leagueobjectmodel.team;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {
    @Test
    public void addPlayerTest() {
        Team team  = new Team(1, "First Team");
        Player player = new Player("First Player", "goalie", false, 27, 5, 5, 5, 5);
        assertTrue(team.addPlayer(player));
    }

    @Test
    public void isPlayersCountValidTest() {
        Team team  = new Team(1, "First Team");
        for (int i = 0; i < 20; i++) {
            Player player = new Player(i, "Player", "goalie", false, 27, 5, 5, 5, 5);
            team.addPlayer(player);
        }
        assertTrue(team.isPlayersCountValid());
    }

    @Test
    public void doesTeamHaveOneCaptainTest() {
        Team team  = new Team(1, "First Team");
        for (int i = 0; i < 18; i++) {
            Player player = new Player(i, "Player", "goalie", false, 27, 5, 5, 5, 5);
            team.addPlayer(player);
        }
        Player player = new Player(19, "Player", "forward", true, 27, 5, 5, 5, 5);
        team.addPlayer(player);
        assertTrue(team.doesTeamHaveOneCaptain());

        player = new Player(20, "Player", "forward", true, 27, 5, 5, 5, 5);
        team.addPlayer(player);
        assertFalse(team.doesTeamHaveOneCaptain());
    }

    @Test
    public void isTeamNameValidTest() {
        String teamName = "First Team";
        assertTrue(Team.isTeamNameValid(teamName));
        teamName = "";
        assertFalse(Team.isTeamNameValid(teamName));
        teamName = " ";
        assertFalse(Team.isTeamNameValid(teamName));
        teamName = "Null";
        assertFalse(Team.isTeamNameValid(teamName));
    }

    @Test
    public void calculateTeamStrengthTest() {
        Team team  = new Team(1, "First Team");
        for (int i = 0; i < 20; i++) {
            Player player = new Player(i, "Player", "goalie", false, 27, 5, 5, 5, 5);
            team.addPlayer(player);
        }
        team.calculateTeamStrength();
        assertEquals(200.0, team.getTeamStrength(), 0.0);
    }

    @Test
    public void setTeamIDTest() {
        Team team  = new Team("First Team");
        team.setTeamID(1);
        assertEquals(1, team.getTeamID());
    }

    @Test
    public void getTeamIDTest() {
        Team team  = new Team(1, "First Team");
        assertEquals(1, team.getTeamID());
    }

    @Test
    public void setTeamNameTest() {
        Team team  = new Team("First Team");
        team.setTeamName("Updated First Team");
        assertEquals("Updated First Team", team.getTeamName());
    }

    @Test
    public void getTeamNameTest() {
        Team team  = new Team("First Team");
        assertEquals("First Team", team.getTeamName());
    }

    @Test
    public void setGeneralManagerTest() {
        Team team  = new Team("First Team");
        GeneralManager generalManager = new GeneralManager("Manager");
        team.setGeneralManager(generalManager);
        assertEquals("Manager", team.getGeneralManager().getManagerName());
    }

    @Test
    public void getGeneralManagerTest() {
        Team team  = new Team("First Team");
        GeneralManager generalManager = new GeneralManager("Manager");
        team.setGeneralManager(generalManager);
        assertEquals("Manager", team.getGeneralManager().getManagerName());
    }

    @Test
    public void setHeadCoachTest() {
        Team team  = new Team("First Team");
        Coach coach = new Coach("Coach", 0.5, 0.5, 0.5, 0.5);
        team.setHeadCoach(coach);
        assertEquals("Coach", team.getHeadCoach().getCoachName());
    }

    @Test
    public void getHeadCoachTest() {
        Team team  = new Team("First Team");
        Coach coach = new Coach("Coach", 0.5, 0.5, 0.5, 0.5);
        team.setHeadCoach(coach);
        assertEquals("Coach", team.getHeadCoach().getCoachName());
    }

    @Test
    public void getPlayersTest() {
        Team team  = new Team(1, "First Team");
        for (int i = 0; i < 20; i++) {
            Player player = new Player(i, "Player", "goalie", false, 27, 5, 5, 5, 5);
            team.addPlayer(player);
        }
        assertEquals(20, team.getPlayers().size());
    }

    @Test
    public void setPlayersTest() {
        Team team  = new Team(1, "First Team");
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < 20; i++) {
            Player player = new Player(i, "Player", "goalie", false, 27, 5, 5, 5, 5);
            players.add(player);
        }
        team.setPlayers(players);
        assertEquals(20,team.getPlayers().size());
    }

    @Test
    public void setTeamStrengthTest() {
        Team team  = new Team(1, "First Team");
        team.setTeamStrength(100.0);
        assertEquals(100.0, team.getTeamStrength(), 0.0);
    }

}
