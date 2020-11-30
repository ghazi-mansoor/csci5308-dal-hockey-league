package com.groupten.statemachine.simulation.trading;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public interface ITrading {

    void startTrading();
    void getInitialTeam();
    void getFinalTeam();
    void calculateWeakSection();
    void generateTradeOffers();
    boolean UITradeOffer();
    void UIPlayerTradeAccept(HashMap<Player,Player> tradingPlayers);
    void UIPlayersTradeAccept(HashMap<ArrayList<Player>,Player> tradingPlayers);
    void userPlayerTradeAccept(HashMap<Player,Player> tradingPlayers);
    void userPlayersTradeAccept(HashMap<ArrayList<Player>,Player> tradingPlayers);
    HashMap<HashMap<Team,Team>,Integer> UIdraftPickTradeAccept(HashMap<Integer,Player> draftPickTradeOffer);
    HashMap<HashMap<Team,Team>,Integer>  userDraftPickTradeAccept(HashMap<Integer,Player> draftPickTradeOffer);
    void adjustTeamPlayers();
    Team UIDropPlayers(Team tradingTeam);
    Team userDropPlayers(Team tradingTeam);
    Team UIGetFromFreeAgents(Team tradingTeam);
    Team userGetFromFreeAgents(Team tradingTeam);
    void getAveragePlayerStrength();
    void getInitialAndFinalTradingPlayers(String weakSection);
}
