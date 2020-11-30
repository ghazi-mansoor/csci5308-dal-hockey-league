package com.groupten.statemachine.simulation.trading;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerTradeOffers {
    private IConsole console;
    private ILeagueModel leagueModel;
    private League leagueLOM;

    public HashMap<HashMap<Player,Player>,Double> computePlayerTradeOffers(String weakSection, Team tradeInitializingTeam,
                                                                           Team tradeFinalizingTeam, ArrayList<Player> initialTradingPlayers, ArrayList<Player> finalTradingPlayers) {
        console = Injector.instance().getConsoleObject();
        leagueModel = Injector.instance().getLeagueModelObject();
        leagueLOM = leagueModel.getCurrentLeague();
        double initialTeamStrength = 0.0;
        double finalTeamStrength = 0.0;
        double initialTeamStrengthMinRange = 0.0;
        double initialTeamStrengthMaxRange = 0.0;
        double finalTeamStrengthMinRange = 0.0;
        double finalTeamStrengthMaxRange = 0.0;
        HashMap<Player,Player> tradingPlayers = new HashMap<>();
        HashMap<HashMap<Player,Player>,Double> tempInitialTeamStrength = new HashMap<>();
        HashMap<HashMap<Player,Player>,Double> tempFinalTeamStrength = new HashMap<>();
        HashMap<HashMap<Player,Player>,Double> playerTradeOffer = new HashMap<>();

        initialTeamStrengthMinRange = tradeInitializingTeam.calculateTeamStrength() -
                (tradeInitializingTeam.calculateTeamStrength()*0.1);
        initialTeamStrengthMaxRange = tradeInitializingTeam.calculateTeamStrength() +
                (tradeInitializingTeam.calculateTeamStrength()*0.1);
        finalTeamStrengthMinRange = tradeFinalizingTeam.calculateTeamStrength() -
                (tradeFinalizingTeam.calculateTeamStrength()*0.1);
        finalTeamStrengthMaxRange = tradeFinalizingTeam.calculateTeamStrength() +
                (tradeFinalizingTeam.calculateTeamStrength()*0.1);

        for(Player player1 : initialTradingPlayers ) {
            for(Player player2 : finalTradingPlayers) {
                initialTeamStrength = tradeInitializingTeam.calculateTotalTeamStrength();
                finalTeamStrength = tradeFinalizingTeam.calculateTotalTeamStrength();

                initialTeamStrength = initialTeamStrength - player1.calculateStrength();
                initialTeamStrength = initialTeamStrength + player2.calculateStrength();
                finalTeamStrength = finalTeamStrength - player2.calculateStrength();
                finalTeamStrength = finalTeamStrength + player1.calculateStrength();

                if(initialTeamStrength > initialTeamStrengthMinRange && finalTeamStrength > finalTeamStrengthMinRange) {
                    tradingPlayers.put(player1,player2);
                    tempInitialTeamStrength.put(tradingPlayers,tradeInitializingTeam.calculateTotalTeamStrength());
                    tempFinalTeamStrength.put(tradingPlayers,tradeFinalizingTeam.calculateTotalTeamStrength());
                    tradingPlayers.clear();
                }
            }
        }
        Map<HashMap<Player,Player>,Double> initialSortedTeamStrength = tempInitialTeamStrength.entrySet() .stream()
                .sorted(Map.Entry.<HashMap<Player,Player>,Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Map.Entry<HashMap<Player,Player>,Double> entry = initialSortedTeamStrength.entrySet().iterator().next();
        playerTradeOffer.put(entry.getKey(), entry.getValue());
        return playerTradeOffer;
    }

}
