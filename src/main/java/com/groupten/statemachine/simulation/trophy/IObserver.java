package com.groupten.statemachine.simulation.trophy;

import com.groupten.leagueobjectmodel.coach.Coach;

import java.util.Map;

public interface IObserver {
    void updateCoachRanking(Map<Coach, Integer> coachRanking);
}
