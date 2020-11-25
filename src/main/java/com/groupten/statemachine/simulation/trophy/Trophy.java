package com.groupten.statemachine.simulation.trophy;

import com.groupten.leagueobjectmodel.coach.Coach;

import java.util.Map;

public class Trophy implements ITrophy, IObserver {

    @Override
    public void updateCoachRanking(Map<Coach, Integer> coachRanking) {
        for (Map.Entry<Coach, Integer> entry : coachRanking.entrySet()) {
            System.out.println("Coach = " + entry.getKey().getCoachName() + ", Count = " + entry.getValue());
        }
    }
}
