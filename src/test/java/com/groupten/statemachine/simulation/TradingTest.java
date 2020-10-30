package com.groupten.statemachine.simulation;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TradingTest {

    @Test
    public void sortByPlayerStrengthTest () {

        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League league = new League("First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();
        Trading trading = new Trading();
        Player player = new Player();
        HashMap<Object,Object> playerStrength = new HashMap<Object, Object>();
        LinkedList strength = new LinkedList();

        player = new Player("Player1", "goalie", 27, 5, 5, 5, 5);
        Integer integer = 7;
        playerStrength.put(player,integer);

        player = new Player("Player2", "defense", 27, 5, 5, 5, 5);
        integer = 3;
        playerStrength.put(player,integer);

        player = new Player("Player3", "forward", 27, 5, 5, 5, 5);
        integer = 4;
        playerStrength.put(player,integer);

        player = new Player("Player4", "defense", 27, 5, 5, 5, 5);
        integer = 2;
        playerStrength.put(player,integer);

        player = new Player("Player5", "goalie", 27, 5, 5, 5, 5);
        integer = 6;
        playerStrength.put(player,integer);

        strength = new LinkedList(playerStrength.entrySet());

        trading.sortByPlayerStrength(strength);

        ArrayList<Object> values = new ArrayList<>();

        for (Iterator it = strength.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            values.add(entry.getValue());
        }

        assertEquals(2,values.get(0));
        assertEquals(3,values.get(1));
        assertEquals(4,values.get(2));
        assertEquals(6,values.get(3));
        assertEquals(7,values.get(4));


    }

    @Test
    public void getWeakestPlayersTest(){

        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League league = new League("First League", 35, 50, 0.1, 0.05, 1, 260, 100, 8, 0.05,
                2, 0.05);

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();

        Trading trading = new Trading();
        Team team = new Team();
        LinkedHashMap<Player,Double> playerStrength = new LinkedHashMap<>();

        Player player = new Player();

        player = new Player("Player3", "defense", 25, 5, 3, 4, 8);
        team.addPlayer(player);
        playerStrength.put(player, player.calculateStrength());
        player = new Player("Player5", "forward", 31, 2, 4, 6, 8);
        team.addPlayer(player);
        playerStrength.put(player, player.calculateStrength());
        player = new Player("Player1", "goalie", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        playerStrength.put(player, player.calculateStrength());
        player = new Player("Player2", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        playerStrength.put(player, player.calculateStrength());
        player = new Player("Player4", "goalie", 30, 10, 5, 3, 7);
        team.addPlayer(player);
        playerStrength.put(player, player.calculateStrength());

        LinkedList orderedStrength = new LinkedList(playerStrength.entrySet());
        HashMap<Player,Double> weakestPlayers = trading.getWeakestPlayers(orderedStrength);

        ArrayList<String> weakPlayerName = new ArrayList<>();
        for (Map.Entry<Player, Double> entry : weakestPlayers.entrySet())
        {
            weakPlayerName.add(entry.getKey().getPlayerName());
        }
        System.out.println(weakPlayerName.get(0));
        assertEquals("Player4",weakPlayerName.get(0));
        assertEquals("Player2",weakPlayerName.get(1));

    }


}
