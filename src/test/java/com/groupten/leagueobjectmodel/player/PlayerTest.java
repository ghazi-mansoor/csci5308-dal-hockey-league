package com.groupten.leagueobjectmodel.player;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void arePlayerFieldsValidTest() {
        String playerName = "First Player";
        String position = "goalie";
        boolean captain = true;
        int age = 25;
        int skating = 5;
        int shooting = 5;
        int checking = 5;
        int saving = 5;
        assertTrue(Player.arePlayerFieldsValid(playerName, position, skating, shooting, checking, saving));
        saving = -10;
        assertFalse(Player.arePlayerFieldsValid(playerName, position, skating, shooting, checking, saving));
    }

    @Test
    public void getPlayerIDTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals(1, player.getPlayerID());
    }

    @Test
    public void setPlayerIDTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setPlayerID(1);
        assertEquals(1, player.getPlayerID());
    }

    @Test
    public void getPlayerNameTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals("First Player", player.getPlayerName());
    }

    @Test
    public void setPlayerNameTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setPlayerName("Updated First Player");
        assertEquals("Updated First Player", player.getPlayerName());
    }

    @Test
    public void getPositionTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals("goalie", player.getPosition());
    }

    @Test
    public void setPositionTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setPosition("forward");
        assertEquals("forward", player.getPosition());
    }

    @Test
    public void isCaptainTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertFalse(player.isCaptain());
    }

    @Test
    public void setCaptainTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setCaptain(true);
        assertTrue(player.isCaptain());
    }

    @Test
    public void setAgeTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals(20, player.getAge(), 0);
    }

    @Test
    public void getAgeTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setAge(19);
        assertEquals(19, player.getAge(), 0);
    }

    @Test
    public void setSkatingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals(5, player.getSkating(), 0);
    }

    @Test
    public void getSkatingTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setSkating(10);
        assertEquals(10, player.getSkating(), 0);
    }

    @Test
    public void setShootingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals(5, player.getShooting(), 0);
    }

    @Test
    public void getShootingTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setShooting(10);
        assertEquals(10, player.getShooting(), 0);
    }

    @Test
    public void setCheckingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals(5, player.getChecking(), 0);
    }

    @Test
    public void getCheckingTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setChecking(10);
        assertEquals(10, player.getChecking(), 0);
    }

    @Test
    public void setSavingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20, 5, 5, 5, 5);
        assertEquals(5, player.getSaving(), 0);
    }

    @Test
    public void getSavingTest() {
        Player player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        player.setSaving(10);
        assertEquals(10, player.getSaving(), 0);
    }

}
