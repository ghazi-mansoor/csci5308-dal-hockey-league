package com.groupten.statemachine.simulation.simulategame.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.shift.Shift;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

public class AlgoStrategy implements IStrategy{
    int PERIODS = 3;
    int PERIOD_TIME_SEC = 1200;
    int SHIFTS = 40;
    int SHIFT_FORWARD = 3;
    int SHIFT_DEFENSE = 2;
    int SHIFT_GOALIE = 1;
    double PENALITY_CHANCE = 0.8;

    Random rand = new Random();
    List<Shift> team1Shifts = new ArrayList<>();
    List<Shift> team2Shifts = new ArrayList<>();

    @Override
    public Team getWinner(Team team1, Team team2) {
        team1Shifts = prepareShifts(team1.getPlayers());
        team2Shifts = prepareShifts(team2.getPlayers());
        int team1Goals = 0;
        int team2Goals = 0;

        for(int s=0;s<SHIFTS;s++){
            Shift team1Shift = team1Shifts.get(0);
            Shift team2Shift = team1Shifts.get(0);
            int team1Shots = 0;
            int team2Shots = 0;
            int team1GoalAttempt = 0;
            int team2GoalAttempt = 0;

            if(team1Shift.getShootingStat() > team2Shift.getShootingStat()){
                team1Shots++;
                if(team1Shift.getSkatingStat() > team2Shift.getSkatingStat()){
                    team1Shots++;
                }else{
                    team1Shots--;
                }
            }else{
                team2Shots++;
                if(team2Shift.getSkatingStat() > team1Shift.getSkatingStat()){
                    team2Shots++;
                }else{
                    team2Shots--;
                }
            }
            for (int i=0;i<team1Shots;i++){
                if(team2Shift.getCheckingStat() > team1Shift.getCheckingStat()){
                    if(rand.nextDouble() > PENALITY_CHANCE){
                        Player penalty_player = team2Shift.getDefensemen().get(rand.nextInt(team2Shift.getDefensemen().size()));
                        team1GoalAttempt++;
                    }
                }else{
                    team1GoalAttempt++;
                }
            }
            for (int i=0;i<team2Shots;i++){
                if(team1Shift.getCheckingStat() > team1Shift.getCheckingStat()){
                    if(rand.nextDouble() > PENALITY_CHANCE){
                        Player penalty_player = team1Shift.getDefensemen().get(rand.nextInt(team1Shift.getDefensemen().size()));
                        team2GoalAttempt++;
                    }
                }else{
                    team2GoalAttempt++;
                }
            }

            for(int i=0;i<team1GoalAttempt;i++){
                if(team2Shift.getSavingStat() < team1Shift.getSavingStat()){
                    team1Goals++;
                    Player goal_player = team1Shift.getForwards().get(rand.nextInt(team1Shift.getForwards().size()));
                }
            }
            for(int i=0;i<team2GoalAttempt;i++){
                if(team1Shift.getSavingStat() < team2Shift.getSavingStat()){
                    team2Goals++;
                    Player goal_player = team2Shift.getForwards().get(rand.nextInt(team2Shift.getForwards().size()));
                }
            }
        }

        if(team1Goals > team2Goals){
            return team1;
        }else{
            return team2;
        }
    }

    private List<Shift> prepareShifts(List<Player> players){
        int total_time = PERIODS*PERIOD_TIME_SEC;
        int shiftInterval = total_time / SHIFTS;
        List<Shift> teamShifts = new ArrayList<>();

        for(int i=0;i<SHIFTS;i++){
            Shift shift = new Shift(shiftInterval);
            for(int j=0; j < SHIFT_FORWARD; j++){
                try{
                    Player forward = getForward(players,shiftInterval);
                    shift.addForward(forward);
                    forward.setAvailTOI(forward.getAvailTOI()-shiftInterval);
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }

            }
            for(int j=0; j < SHIFT_DEFENSE; j++){
                try{
                    Player defense = getDefense(players,shiftInterval);
                    shift.addDefense(defense);
                    defense.setAvailTOI(defense.getAvailTOI()-shiftInterval);
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }
            }
            for(int j=0; j < SHIFT_GOALIE; j++){
                try{
                    Player goalie = getGoalie(players);
                    shift.setGoalie(goalie);
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }
            }
            teamShifts.add(shift);
        }
        return teamShifts;
    }

    private Player getForward(List<Player> players, int shiftInterval) throws NoSuchElementException {
        List<Player> forwards = players.stream()
                .filter(player -> player.getPosition().equals("forward"))
                .filter(player -> player.getAvailTOI() >= shiftInterval)
                .collect(Collectors.toList());
        if(forwards.size() == 0) {
            players.forEach(player -> {
                System.out.println(player.getAvailTOI());
            });
            throw new NoSuchElementException();
        }
        return forwards.get(rand.nextInt(forwards.size()));
    }

    private Player getDefense(List<Player> players, int shiftInterval) throws NoSuchElementException {
        List<Player> defensemen = players.stream()
                .filter(player -> player.getPosition().equals("defense"))
                .filter(player -> player.getAvailTOI() >= shiftInterval)
                .collect(Collectors.toList());
        if(defensemen.size() == 0) {
            throw new NoSuchElementException();
        }
        return defensemen.get(rand.nextInt(defensemen.size()));
    }

    private Player getGoalie(List<Player> players) throws NoSuchElementException {
        List<Player> goalies = players.stream()
                .filter(player -> player.getPosition().equals("goalie"))
                .collect(Collectors.toList());
        if(goalies.size() == 0) {
            throw new NoSuchElementException();
        }
        return goalies.get(rand.nextInt(goalies.size()));
    }
}


