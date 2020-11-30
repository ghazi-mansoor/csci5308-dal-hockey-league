package com.groupten.statemachine.simulation.trading;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.statemachine.jsonimport.JSONImport;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TradingTest {
    private IConsole console;
    private ILeagueModel leagueModel;
    private League leagueLOM;
    GameConfig.Trading tradingConfig;
    ITradeFactory tradeFactory = TradeFactory.FactorySingleton();
    PlayerTradeOffers playerTradeOffers = tradeFactory.createPlayerTradeOffers();
    PlayersTradeOffers playersTradeOffers = tradeFactory.createPlayersTradeOffers();
    DraftPickTradeOffers draftPickTradeOffers = tradeFactory.createDraftPickTradeOffers();
    Trading trading = tradeFactory.createTrading();
    @Before
    public void instantiate(){
        console = Injector.instance().getConsoleObject();
        leagueModel = Injector.instance().getLeagueModelObject();
        JSONImport jsonImport = new JSONImport();
        String path = "src/test/java/com/groupten/mocks/JsonMock.json";
        jsonImport.importJSONData(path);
        jsonImport.instantiateJSONData();
        leagueLOM = leagueModel.getCurrentLeague();
        tradingConfig = leagueLOM.getTradingConfig();
    }

    @Test
    public void UIDropPlayersTest(){
        for (Conference c : leagueLOM.getConferences().values()) {
            for (Division d : c.getDivisions().values()) {
                for (Team team : d.getTeams().values()) {
                   // System.out.println(team.getTeamName()+"  "+team.getAllPlayers().size());
                   while(team.getAllPlayers().size() > 30) {
                       trading.UIDropPlayers(team);
                       assertNotEquals(team.getAllPlayers().size(),30);
                   }
                   assertEquals(team.getAllPlayers().size(),30);
                }
            }
        }
    }

    @Test
    public void UIGetFromFreeAgentsTest(){
        for (Conference c : leagueLOM.getConferences().values()) {
            for (Division d : c.getDivisions().values()) {
                for (Team team : d.getTeams().values()) {
                    //System.out.println(team.getTeamName()+"  "+team.getAllPlayers().size());
                    while(team.getAllPlayers().size() < 30) {
                        trading.UIDropPlayers(team);
                        assertNotEquals(team.getAllPlayers().size(),30);
                    }
                    assertEquals(team.getAllPlayers().size(),30);
                }
            }
        }
    }

    @Test
    public void UITradeOfferTest(){

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
    public void UIPlayerTradeAcceptTest(){
        HashMap<Player, Player> tradingPlayers = new HashMap<>();

    }

    @Test
    public void getAveragePlayerStrengthTest(){
        assertEquals(trading.getAverageDefenseStrength(),0.0,0);
        assertEquals(trading.getAverageForwardStrength(),0.0,0);
        assertEquals(trading.getAverageGoalieStrength(),0.0,0);

        trading.getAveragePlayerStrength();
        /*System.out.println(trading.getAverageDefenseStrength());
        System.out.println(trading.getAverageForwardStrength());
        System.out.println(trading.getAverageGoalieStrength());*/
        assertNotEquals(trading.getAverageDefenseStrength(),0.0,0);
        assertNotEquals(trading.getAverageForwardStrength(),0.0,0);
        assertNotEquals(trading.getAverageGoalieStrength(),0.0,0);

    }

    @Test
    public void getInitialTeamTest(){
        for (Conference c : leagueLOM.getConferences().values()) {
            for (Division d : c.getDivisions().values()) {
                for (Team team : d.getTeams().values()) {
                    if(team.getTeamName().equals("Ottawa Blues")) {
                        team.setLossPoint(12);
                    }
                }
            }
        }
        trading.getInitialTeam();
        assertEquals(trading.getTradeInitializingTeam().getTeamName(),"Ottawa Blues");

    }

    @Test
    public void getFinalTeamTest(){
        trading.setAverageDefenseStrength(26.33096590909091);
        trading.setAverageForwardStrength(26.741666666666667);
        trading.setAverageGoalieStrength(22.15625);
        trading.setWeakSection("defense");
        assertEquals(trading.getTradeFinalizingTeam().getTeamName(),null);
        trading.getFinalTeam();
        assertEquals(trading.getTradeFinalizingTeam().getTeamName(),"Winnipeg Hound Dogs");
    }

    @Test
    public void getInitialAndFinalTradingPlayersTest(){
        for (Conference c : leagueLOM.getConferences().values()) {
            for (Division d : c.getDivisions().values()) {
                for (Team team : d.getTeams().values()) {
                   if(team.getTeamName().equals("Ottawa Blues")) {
                       trading.setTradeInitializingTeam(team);
                   } else if(team.getTeamName().equals("Winnipeg Hound Dogs")) {
                       trading.setTradeFinalizingTeam(team);
                   }
                }
            }
        }
        trading.setWeakSection("defense");
        assertEquals(trading.getInitialTradingPlayers().size(),0);
        assertEquals(trading.getFinalTradingPlayers().size(),0);
        trading.getInitialAndFinalTradingPlayers(trading.getWeakSection());
        assertNotEquals(trading.getInitialTradingPlayers().size(),0);
        assertNotEquals(trading.getFinalTradingPlayers().size(),0);
    }

}
