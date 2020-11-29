package com.groupten;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.statemachine.simulation.trophy.Trophy;

import java.util.LinkedHashMap;

public class test {

    public void doTest(){
        Trophy trophy = new Trophy();
        LinkedHashMap<Team, Integer> teamRanking = new LinkedHashMap<>();
        teamRanking.put(new Team("Deep Team"), 100);
        teamRanking.put(new Team("Sneha Team"), 999);
        teamRanking.put(new Team("Iron Man Team"), 2);
        teamRanking.put(new Team("Captain Team"), 4);
        teamRanking.put(new Team("Avengers Team"), 50);
        teamRanking.put(new Team("Sherlock Team"), 48);
        teamRanking.put(new Team("Blah Team"), 1000);
        teamRanking.put(new Team("Zoop Team"), 7);
        teamRanking.put(new Team("a Team"), 1);
        teamRanking.put(new Team("b Team"), 109);

        LinkedHashMap<Coach, Integer> coachRanking = new LinkedHashMap<>();
        coachRanking.put(new Coach("A"), 100);
        coachRanking.put(new Coach("B"), 999);
        coachRanking.put(new Coach("C"), 2);
        coachRanking.put(new Coach("D"), 4);
        coachRanking.put(new Coach("E"), 50);
        coachRanking.put(new Coach("F"), 48);
        coachRanking.put(new Coach("G"), 1000);
        coachRanking.put(new Coach("H"), 7);
        coachRanking.put(new Coach("I"), 109);

        trophy.setCoachRanking(coachRanking);
        trophy.setTeamRanking(teamRanking);

        trophy.awardTrophy();
        trophy.trophyWinners();

        LinkedHashMap<Team, Integer> teamRanking1 = new LinkedHashMap<>();
        teamRanking1.put(new Team("Deep Team"), 1000);
        teamRanking1.put(new Team("Sneha Team"), 9);
        teamRanking1.put(new Team("Iron Man Team"), 20);
        teamRanking1.put(new Team("Captain Team"), 45);
        teamRanking1.put(new Team("Avengers Team"), 8);
        teamRanking1.put(new Team("Sherlock Team"), 1);
        teamRanking1.put(new Team("Blah Team"), 15);
        teamRanking1.put(new Team("Zoop Team"), 44);
        teamRanking1.put(new Team("a Team"), 88);
        teamRanking1.put(new Team("b Team"), 100);

        LinkedHashMap<Coach, Integer> coachRanking1 = new LinkedHashMap<>();
        coachRanking1.put(new Coach("A"), 10);
        coachRanking1.put(new Coach("B"), 9);
        coachRanking1.put(new Coach("C"), 20);
        coachRanking1.put(new Coach("D"), 4500);
        coachRanking1.put(new Coach("E"), 8);
        coachRanking1.put(new Coach("F"), 48);
        coachRanking1.put(new Coach("G"), 4);
        coachRanking1.put(new Coach("H"), 15);
        coachRanking1.put(new Coach("I"), 5);

        trophy.setCoachRanking(coachRanking1);
        trophy.setTeamRanking(teamRanking1);

        trophy.awardTrophy();
        trophy.trophyWinners();

    }

}
