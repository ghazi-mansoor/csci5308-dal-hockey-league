package com.groupten.statemachine.simulation.trading;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.HashMap;
import java.util.LinkedList;

public interface ITrading {

    void getInitialTeam();
    void getFinalTeam();
    String getWeakSection();
    double computePlayerTradeOffers(String weakSection);
    double computePlayersTradeOffers(String weakSection);
    void generateTradeOffers();
    boolean UITradeOffer();
    void UITradeAccept(HashMap<Player,Player> tradingPlayers);
    void userTradeAccept(HashMap<Player,Player> tradingPlayers);
    void adjustTeamPlayers();
    Team UIDropPlayers(Team tradingTeam);
    Team userDropPlayers(Team tradingTeam);
    Team UIGetFromFreeAgents(Team tradingTeam);
    Team userGetFromFreeAgents(Team tradingTeam);
    LinkedList sortByStrength(LinkedList strength);
    void getAveragePlayerStrength();
}
