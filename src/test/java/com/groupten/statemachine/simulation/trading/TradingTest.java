package com.groupten.statemachine.simulation.trading;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TradingTest {
    @Test
    public void sortByPlayerStrengthTest () {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = new League("First League");
        GameConfig.Trading tradingConfig = new GameConfig.Trading(8, 0.05, 2, 0.05);
        league.setTradingConfig(tradingConfig);

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
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = new League("First League");
        GameConfig.Trading tradingConfig = new GameConfig.Trading(8, 0.05, 2, 0.05);
        league.setTradingConfig(tradingConfig);

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

    @Test
    public void UIDropPlayersTest(){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = new League("First League");
        GameConfig.Trading tradingConfig = new GameConfig.Trading(8, 0.05, 2, 0.05);
        league.setTradingConfig(tradingConfig);

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();
        Trading trading = new Trading();
        Team team = new Team();
        Player player = new Player();

        player = new Player("Player1", "goalie", 25, 5, 3, 4, 8);
        team.addPlayer(player);
        player = new Player("Player2", "forward", 31, 2, 4, 9, 8);
        team.addPlayer(player);
        player = new Player("Player3", "defense", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        player = new Player("Player4", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player5", "defense", 30, 10, 5, 3, 7);
        team.addPlayer(player);
        player = new Player("Player6", "forward", 25, 5, 3, 4, 8);
        team.addPlayer(player);
        player = new Player("Player7", "forward", 31, 2, 4, 6, 1);
        team.addPlayer(player);
        player = new Player("Player8", "defense", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        player = new Player("Player9", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player10", "defense", 30, 10, 5, 3, 7);
        team.addPlayer(player);
        player = new Player("Player11", "defense", 25, 5, 3, 4, 8);
        team.addPlayer(player);
        player = new Player("Player12", "forward", 31, 6, 4, 6, 8);
        team.addPlayer(player);
        player = new Player("Player13", "defense", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        player = new Player("Player14", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player15", "forward", 30, 10, 5, 3, 7);
        team.addPlayer(player);
        player = new Player("Player16", "goalie", 25, 9, 1, 8, 8);
        team.addPlayer(player);
        player = new Player("Player17", "forward", 31, 6, 4, 2, 8);
        team.addPlayer(player);
        player = new Player("Player18", "defense", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        player = new Player("Player19", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player20", "defense", 30, 10, 5, 3, 7);
        team.addPlayer(player);
        player = new Player("Player21", "forward", 25, 15, 3, 4, 8);
        team.addPlayer(player);
        player = new Player("Player22", "forward", 31, 2, 9, 6, 8);
        team.addPlayer(player);
        player = new Player("Player23", "goalie", 27, 10, 4, 6, 10);
        team.addPlayer(player);
        player = new Player("Player24", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player25", "goalie", 30, 10, 15, 3, 12);
        team.addPlayer(player);

        trading.UIDropPlayers(team);
        int i = 0;
        assertEquals("Player16",leagueLOM.getFreeAgentsGoalies().get(i).getPlayerName());
        i++;
        assertEquals("Player1",leagueLOM.getFreeAgentsGoalies().get(i).getPlayerName());
        i++;
        int j = 0;
        assertEquals("Player6",leagueLOM.getFreeAgentsSkaters().get(j).getPlayerName());
        j++;
        assertEquals("Player11",leagueLOM.getFreeAgentsSkaters().get(j).getPlayerName());
        j++;
        assertEquals("Player7",leagueLOM.getFreeAgentsSkaters().get(j).getPlayerName());
        j++;

    }

    @Test
    public void UIGetFromFreeAgentsTest(){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = new League("First League");
        GameConfig.Trading tradingConfig = new GameConfig.Trading(8, 0.05, 2, 0.05);
        league.setTradingConfig(tradingConfig);

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();
        Trading trading = new Trading();
        Team team = new Team();
        Player player = new Player();

        player = new Player("Player1", "goalie", 25, 5, 3, 4, 8);
        team.addPlayer(player);
        player = new Player("Player2", "forward", 31, 2, 4, 9, 8);
        team.addPlayer(player);
        player = new Player("Player3", "defense", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        player = new Player("Player4", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player5", "defense", 30, 10, 5, 3, 7);
        team.addPlayer(player);
        player = new Player("Player6", "forward", 25, 5, 3, 4, 8);
        team.addPlayer(player);
        player = new Player("Player7", "forward", 31, 2, 4, 6, 1);
        team.addPlayer(player);
        player = new Player("Player8", "defense", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        player = new Player("Player9", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player10", "defense", 30, 10, 5, 3, 7);
        team.addPlayer(player);
        player = new Player("Player11", "defense", 25, 5, 3, 4, 8);
        team.addPlayer(player);
        player = new Player("Player12", "forward", 31, 6, 4, 6, 8);
        team.addPlayer(player);
        player = new Player("Player13", "defense", 27, 5, 4, 6, 5);
        team.addPlayer(player);
        player = new Player("Player14", "forward", 29, 7, 3, 2, 7);
        team.addPlayer(player);
        player = new Player("Player15", "forward", 30, 10, 5, 3, 7);
        team.addPlayer(player);

        player = new Player("Player16", "goalie", 25, 9, 1, 8, 8);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player17", "forward", 31, 6, 14, 2, 18);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player18", "defense", 27, 2, 4, 6, 5);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player19", "forward", 29, 7, 3, 12, 7);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player20", "defense", 30, 10, 5, 3, 7);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player21", "forward", 25, 15, 3, 4, 8);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player22", "forward", 31, 2, 9, 6, 8);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player23", "goalie", 27, 9, 4, 6, 10);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player24", "forward", 29, 7, 3, 2, 7);
        leagueLOM.addFreeAgent(player);
        player = new Player("Player25", "goalie", 30, 10, 15, 3, 12);
        leagueLOM.addFreeAgent(player);

        trading.UIGetFromFreeAgents(team);

        int i = 15;
        assertEquals("Player25",team.getPlayers().get(i).getPlayerName());
        i++;
        assertEquals("Player17",team.getPlayers().get(i).getPlayerName());
        i++;
        assertEquals("Player21",team.getPlayers().get(i).getPlayerName());
        i++;
        assertEquals("Player20",team.getPlayers().get(i).getPlayerName());
        i++;
        assertEquals("Player19",team.getPlayers().get(i).getPlayerName());

    }

    @Test
    public void UITradeOfferTest(){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = new League("First League");
        GameConfig.Trading tradingConfig = new GameConfig.Trading(8, 0.05, 2, 0.05);
        league.setTradingConfig(tradingConfig);

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();
        Trading trading = new Trading();

        if(trading.UITradeOffer())
        {
            assertTrue(trading.isTrade());
        }
        else
        {
            assertFalse(trading.isTrade());
        }

    }

    @Test
    public void UITradeAcceptTest(){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = new League("First League");
        GameConfig.Trading tradingConfig = new GameConfig.Trading(8, 0.05, 2, 0.05);
        league.setTradingConfig(tradingConfig);

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();
        Trading trading = new Trading();
        Player player = new Player();

        Team team1 = new Team();
        Player player1 = new Player("Player1", "goalie", 25, 5, 3, 4, 8);
        team1.addPlayer(player1);
        Player player2  = new Player("Player2", "forward", 31, 2, 4, 9, 8);
        team1.addPlayer(player2);
        Player player3 = new Player("Player3", "defense", 27, 5, 4, 6, 5);
        team1.addPlayer(player3);
        Player player4 = new Player("Player4", "forward", 29, 7, 3, 2, 7);
        team1.addPlayer(player4);
        Player player5 = new Player("Player5", "defense", 30, 10, 5, 3, 7);
        team1.addPlayer(player5);
        trading.setTradeInitializingTeam(team1);
        trading.getTradeFinalizingTeam().setLossPoint(2);

        Team team2 = new Team();
        Player player6 = new Player("Player6", "forward", 25, 5, 3, 4, 8);
        team2.addPlayer(player6);
        Player player7 = new Player("Player7", "forward", 31, 2, 4, 6, 1);
        team2.addPlayer(player7);
        Player player8 = new Player("Player8", "defense", 27, 5, 4, 6, 5);
        team2.addPlayer(player8);
        Player player9 = new Player("Player9", "forward", 29, 7, 3, 2, 7);
        team2.addPlayer(player9);
        Player player10 = new Player("Player10", "defense", 30, 10, 5, 3, 7);
        team2.addPlayer(player10);
        trading.setTradeFinalizingTeam(team2);
        trading.getTradeFinalizingTeam().setLossPoint(3);

        HashMap<Player,Player> tradingPlayers = new HashMap<>();
        tradingPlayers.put(player2,player7);
        tradingPlayers.put(player3,player8);

        trading.UITradeAccept(tradingPlayers);

        assertEquals(0, trading.getTradeInitializingTeam().getLossPoint());
    }

}
