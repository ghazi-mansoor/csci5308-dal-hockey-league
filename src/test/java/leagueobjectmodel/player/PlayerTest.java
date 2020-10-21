package leagueobjectmodel.player;

import com.groupten.leagueobjectmodel.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void arePlayerFieldsValidTest() {
        String playerName = "First Player";
        String position = "goalie";
        boolean captain = true;
        double age = 15.0;
        double skating = 5.0;
        double shooting = 5.0;
        double checking = 5.0;
        double saving = 5.0;
        assertTrue(Player.arePlayerFieldsValid(playerName, position, skating, shooting, checking, saving));
        saving = -10;
        assertFalse(Player.arePlayerFieldsValid(playerName, position, skating, shooting, checking, saving));
    }

    @Test
    public void getPlayerIDTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals(1, player.getPlayerID());
    }

    @Test
    public void setPlayerIDTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setPlayerID(1);
        assertEquals(1, player.getPlayerID());
    }

    @Test
    public void getPlayerNameTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals("First Player", player.getPlayerName());
    }

    @Test
    public void setPlayerNameTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setPlayerName("Updated First Player");
        assertEquals("Updated First Player", player.getPlayerName());
    }

    @Test
    public void getPositionTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals("goalie", player.getPosition());
    }

    @Test
    public void setPositionTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setPosition("forward");
        assertEquals("forward", player.getPosition());
    }

    @Test
    public void isCaptainTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertFalse(player.isCaptain());
    }

    @Test
    public void setCaptainTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setCaptain(true);
        assertTrue(player.isCaptain());
    }

    @Test
    public void setAgeTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals(20.0, player.getAge(), 0.0);
    }

    @Test
    public void getAgeTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setAge(19.0);
        assertEquals(19.0, player.getAge(), 0.0);
    }

    @Test
    public void setSkatingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals(5.0, player.getSkating(), 0.0);
    }

    @Test
    public void getSkatingTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setSkating(10.0);
        assertEquals(10.0, player.getSkating(), 0.0);
    }

    @Test
    public void setShootingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals(5.0, player.getShooting(), 0.0);
    }

    @Test
    public void getShootingTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setShooting(10.0);
        assertEquals(10.0, player.getShooting(), 0.0);
    }

    @Test
    public void setCheckingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals(5.0, player.getChecking(), 0.0);
    }

    @Test
    public void getCheckingTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setChecking(10.0);
        assertEquals(10.0, player.getChecking(), 0.0);
    }

    @Test
    public void setSavingTest() {
        Player player = new Player(1, "First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        assertEquals(5.0, player.getSaving(), 0.0);
    }

    @Test
    public void getSavingTest() {
        Player player = new Player("First Player", "goalie", false, 20.0, 5.0, 5.0, 5.0, 5.0);
        player.setSaving(10.0);
        assertEquals(10.0, player.getSaving(), 0.0);
    }

}
