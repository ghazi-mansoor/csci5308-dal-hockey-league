package com.groupten.statemachine.simulation.trophy;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.season.ISeasonObserver;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.List;
import java.util.Map;

public class Trophy implements ITrophy, IObserver, ISeasonObserver {

    @Override
    public void updateCoachRanking(Map<Coach, Integer> coachRanking) {
        for (Map.Entry<Coach, Integer> entry : coachRanking.entrySet()) {
            System.out.println("Coach = " + entry.getKey().getCoachName() + ", Count = " + entry.getValue());
        }
    }

    @Override
    public void updateRegularSeasonEnd(Season season) {
        List<TeamStanding> teamStandings = season.getTeamStandings();

        System.out.println("-------------------------------------------------------------");

        for (TeamStanding teamStanding : teamStandings) {
            System.out.println(teamStanding.getTeam() + " " + teamStanding.getPoints());
        }

        System.out.println("-------------------------------------------------------------");
    }
}
