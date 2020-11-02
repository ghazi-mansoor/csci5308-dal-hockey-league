package com.groupten.statemachine.simulation.trading;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.*;

public interface ITrading {

    void startTrading();
    void initiateTrading(HashMap<Player, Double> initializingWeakestPlayers );
    HashMap<Player,Player> generatePlayersForTrading(HashMap<Player,Double> initializingWeakestPlayers,
                                                            HashMap<Player,Double> finalizingPlayerStrength);
    boolean UITradeOffer();
    void UITradeAccept(HashMap<Player,Player> tradingPlayers);
    void UserTradeAccept(HashMap<Player,Player> tradingPlayers);
    void adjustTeamPlayers();
    Team UIDropPlayers(Team tradingTeam);
    Team userDropPlayers(Team tradingTeam);
    Team UIGetFromFreeAgents(Team tradingTeam);
    Team userGetFromFreeAgents(Team tradingTeam);
    LinkedList sortByPlayerStrength(LinkedList strength);
    HashMap<Player,Double> getWeakestPlayers(LinkedList orderedStrength);

}
