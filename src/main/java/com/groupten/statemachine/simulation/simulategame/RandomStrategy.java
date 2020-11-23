package com.groupten.statemachine.simulation.simulategame;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.Random;

public class RandomStrategy implements IStrategy {

    @Override
    public Team getWinner(Team team1, Team team2) {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        League league = leagueModel.getCurrentLeague();

        GameConfig.GameResolver gameResolver = league.getGameResolverConfig();
        double randomWinChance = gameResolver.getRandomWinChance();

        if(new Random().nextDouble() > randomWinChance){
            if(team1.getTeamStrength() > team2.getTeamStrength()){
                return team1;
            }else{
                return team2;
            }
        }else{
            if(team1.getTeamStrength() < team2.getTeamStrength()){
                return team1;
            }else{
                return team2;
            }
        }
    }
}
