package com.groupten.mocks;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.injector.Injector;

import java.util.ArrayList;

public class TradingMock {

    ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
    League leagueLOM;
    League league = new League("First League");
    ArrayList<Player> playersList = new ArrayList<>();


    public ArrayList<Player> createPlayers(){

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();

        String playerPosition[] = new String[30];
        Player player = new Player();

        for(int i=1; i<=16; i++)
        {
            playerPosition[i] = "forward";
        }

        for(int i=17; i<=26; i++)
        {
            playerPosition[i] = "defense";
        }

        for(int i=27; i<=30; i++)
        {
            playerPosition[i] = "goalie";
        }

        for(int i = 0; i<30 ; i++)
        {
            player = new Player("Player"+i, playerPosition[i], 27, 5, 5, 5, 5);
            playersList.add(player);
        }
        return playersList;
    }


    public Team createTeam(){

        leagueModel.setCurrentLeague(league);
        League leagueLOM = leagueModel.getCurrentLeague();

        Team team = new Team();

        for(Player player : createPlayers())
        {
            team.addPlayer(player);
        }

        return team;

    }


}
