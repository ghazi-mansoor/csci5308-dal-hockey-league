package com.groupten.statemachine.simulation.training;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.List;
import java.util.Map;

public class Training implements ITraining {

    @Override
    public void train() {
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League leagueLOM = leagueModel.getCurrentLeague();
        int daysUntilStatIncreaseCheck = leagueLOM.getDaysUntilStatIncreaseCheck();

        Map<String, Conference> conferences = leagueLOM.getConferences();
        for (Conference conference: conferences.values()) {

            Map<String, Division> divisions = conference.getDivisions();
            for(Division division: divisions.values()){

                Map<String, Team> teams = division.getTeams();
                for(Team team: teams.values()){

                    double skating = team.getHeadCoach().getSkating();
                    double shooting = team.getHeadCoach().getShooting();
                    double checking = team.getHeadCoach().getChecking();
                    double saving = team.getHeadCoach().getSaving();

                    List<Player> players = team.getPlayers();

                    for(Player player: players){
                        if(compareStatisticWithRandomValue(skating)){
                            if(player.getSkating() < 20){
                                player.setSkating(player.getSkating() + 1);
                            }
                        }else{
                            // Injury Check
                        }

                        if(compareStatisticWithRandomValue(shooting)){
                            if(player.getShooting() < 20){
                                player.setShooting(player.getShooting() + 1);
                            }
                        }else{
                            // Injury Check
                        }

                        if(compareStatisticWithRandomValue(checking)){
                            if(player.getChecking() < 20){
                                player.setChecking(player.getChecking() + 1);
                            }
                        }else{
                            // Injury Check
                        }

                        if(compareStatisticWithRandomValue(saving)){
                            if(player.getSaving() < 20){
                                player.setSaving(player.getSaving() + 1);
                            }
                        }else{
                            // Injury Check
                        }
                    }
                }
            }
        }
    }

    private boolean compareStatisticWithRandomValue(double statistic){
        double random = Math.round(Math.random() * 100.0) / 100.0;
        return (random < statistic);
    }
}
