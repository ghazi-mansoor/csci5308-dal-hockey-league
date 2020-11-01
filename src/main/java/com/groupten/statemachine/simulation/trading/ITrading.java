package com.groupten.statemachine.simulation.trading;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.*;

public interface ITrading {

    public void startTrading();
    public void initiateTrading(HashMap<Player, Double> initializingWeakestPlayers );
    public HashMap<Player,Player> generatePlayersForTrading(HashMap<Player,Double> initializingWeakestPlayers,
                                                            HashMap<Player,Double> finalizingPlayerStrength);
    public boolean UITradeOffer();
    public void UITradeAccept(HashMap<Player,Player> tradingPlayers);
    public void UserTradeAccept(HashMap<Player,Player> tradingPlayers);
    public void adjustTeamPlayers();
    public Team UIDropPlayers(Team tradingTeam);
    public Team userDropPlayers(Team tradingTeam);
    public Team UIGetFromFreeAgents(Team tradingTeam);
    public Team userGetFromFreeAgents(Team tradingTeam);
    public LinkedList sortByPlayerStrength(LinkedList strength);
    public HashMap<Player,Double> getWeakestPlayers(LinkedList orderedStrength);

}
