package com.groupten.statemachine.simulation.simulategame.strategy;

import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.shift.Shift;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.*;
import java.util.stream.Collectors;

public class AlgoStrategy implements IStrategy{
    int PERIODS = 3;
    int PERIOD_TIME_SEC = 1200;
    int SHIFTS = 40;
    int SHIFT_FORWARD = 3;
    int SHIFT_DEFENSE = 2;
    int SHIFT_GOALIE = 1;
    double PENALTY_CHANCE = 0.20;
    double SAVING_BENCHMARK = 14;

    Random rand = new Random();
    Season season;
    List<Shift> team1Shifts = new ArrayList<>();
    List<Shift> team2Shifts = new ArrayList<>();
    Map<Player, Integer> shots = new HashMap<>();
    Map<Player, Integer> penalties = new HashMap<>();
    Map<Player, Integer> goals = new HashMap<>();
    Map<Player, Integer> saves = new HashMap<>();
    private List<IAlgoStrategyObserver> observers = new ArrayList<>();

    public void attach(IAlgoStrategyObserver observer) {
        this.observers.add(observer);
    }

    public void detach(IAlgoStrategyObserver observer) {
        this.observers.remove(observer);
    }

    private void notifyObservers() {
        for (IAlgoStrategyObserver observer : this.observers) {
            observer.updateShots(shots);
            observer.updatePenalties(penalties);
            observer.updateGoals(goals);
            observer.updateSaves(saves);
        }
    }

    @Override
    public Team getWinner(Season season, Team team1, Team team2) {
        this.season = season;

        team1Shifts = prepareShifts(team1.getActivePlayers());
        team2Shifts = prepareShifts(team2.getActivePlayers());

        int team1Goals = 0;
        int team2Goals = 0;

        for(int s=0;s<SHIFTS;s++){
            Shift team1Shift = team1Shifts.get(s);
            Shift team2Shift = team2Shifts.get(s);
            int team1Shots = 0;
            int team2Shots = 0;
            int team1GoalAttempt = 0;
            int team2GoalAttempt = 0;

            if(team1Shift.getShootingStat() > team2Shift.getShootingStat()){
                team1Shots++;
                if(team1Shift.getSkatingStat() > team2Shift.getSkatingStat()){
                    team1Shots++;
                }
            }else{
                team2Shots++;
                if(team2Shift.getSkatingStat() > team1Shift.getSkatingStat()){
                    team2Shots++;
                }
            }

            for (int i=0;i<team1Shots;i++){
                if(recordShot(team1Shift)) {
                    team1GoalAttempt++;
                    if (team2Shift.getCheckingStat() > team1Shift.getCheckingStat()) {
                        team1GoalAttempt--;
                        if (rand.nextFloat() < PENALTY_CHANCE) {
                            recordPenalty(team2Shift);
                        }
                    }
                }
            }
            for (int i=0;i<team2Shots;i++){
                if(recordShot(team2Shift)) {
                    team2GoalAttempt++;
                    if (team1Shift.getCheckingStat() > team1Shift.getCheckingStat()) {
                        team2GoalAttempt--;
                        if (rand.nextFloat() < PENALTY_CHANCE) {
                            recordPenalty(team1Shift);
                        }
                    }
                }
            }

            for(int i=0;i<team1GoalAttempt;i++){
                if(team2Shift.getGoalie() == null){
                    recordSave(team2Shift);
                }else{
                    if(team2Shift.getGoalie().getSaving() > SAVING_BENCHMARK){
                        if(recordGoal(team1Shift)){
                            team1Goals++;
                        }
                    }else{
                        recordSave(team2Shift);
                    }
                }
            }
            for(int i=0;i<team2GoalAttempt;i++){
                if(team1Shift.getGoalie() == null){
                    recordSave(team1Shift);
                }else{
                    if(team1Shift.getGoalie().getSaving() > SAVING_BENCHMARK){
                        if(recordGoal(team2Shift)){
                            team2Goals++;
                        }
                    }else{
                        recordSave(team1Shift);
                    }
                }
            }
        }

        notifyObservers();

        resetAvailTOI(team1.getActivePlayers());
        resetAvailTOI(team2.getActivePlayers());

        if(team1Goals > team2Goals){
            return team1;
        }else{
            return team2;
        }
    }

    private boolean recordShot(Shift shift){
        if(shift.getForwards().size() > 0){
            shots.merge(shift.getForwards().get(rand.nextInt(shift.getForwards().size())),1, Integer::sum);
            return  true;
        }else{
            return false;
        }
    }

    private boolean recordPenalty(Shift shift){
        if(shift.getDefensemen().size() > 0){
            penalties.merge(shift.getDefensemen().get(rand.nextInt(shift.getDefensemen().size())),1, Integer::sum);
            return true;
        }else{
            return false;
        }
    }

    private boolean recordGoal(Shift shift){
        if(shift.getForwards().size() > 0){
            penalties.merge(shift.getForwards().get(rand.nextInt(shift.getForwards().size())),1, Integer::sum);
            return  true;
        }else{
            return false;
        }
    }

    private boolean recordSave(Shift shift){
        saves.merge(shift.getGoalie(),1, Integer::sum);
        return true;
    }

    private void resetAvailTOI(List<Player> players){
        players.forEach(Player::resetAvailTOI);
    }

    private List<Shift> prepareShifts(List<Player> players){
        int total_time = PERIODS*PERIOD_TIME_SEC;
        int shiftInterval = total_time / SHIFTS;
        List<Shift> teamShifts = new ArrayList<>();

        for(int i=0;i<SHIFTS;i++){
            Shift shift = new Shift(shiftInterval);
            for(int j=0; j < SHIFT_FORWARD; j++){
                Player forward = getForward(players,shiftInterval);
                if(forward != null){
                    shift.addForward(forward);
                    forward.setAvailTOI(forward.getAvailTOI()-shiftInterval);
                }
            }
            for(int j=0; j < SHIFT_DEFENSE; j++){
                Player defense = getDefense(players,shiftInterval);
                if(defense != null) {
                    shift.addDefense(defense);
                    defense.setAvailTOI(defense.getAvailTOI()-shiftInterval);
                }
            }
            for(int j=0; j < SHIFT_GOALIE; j++){
                Player goalie = getGoalie(players);
                if(goalie != null){
                    shift.setGoalie(goalie);
                }
            }
            teamShifts.add(shift);
        }
        return teamShifts;
    }

    private Player getForward(List<Player> players, int shiftInterval) throws NoSuchElementException {
        Player forward = null;
        List<Player> forwards = players.stream()
                .filter(player -> player.getPosition().equals("forward"))
                .filter(player -> player.getAvailTOI() >= shiftInterval)
                .collect(Collectors.toList());
        if(forwards.size() > 0) {
            forward = forwards.get(rand.nextInt(forwards.size()));
        }
        return forward;
    }

    private Player getDefense(List<Player> players, int shiftInterval) throws NoSuchElementException {
        Player defence = null;
        List<Player> defensemen = players.stream()
                .filter(player -> player.getPosition().equals("defense"))
                .filter(player -> player.getAvailTOI() >= shiftInterval)
                .collect(Collectors.toList());
        if(defensemen.size() > 0) {
            defence = defensemen.get(rand.nextInt(defensemen.size()));
        }
        return defence;
    }

    private Player getGoalie(List<Player> players) throws NoSuchElementException {
        List<Player> goalies = players.stream()
                .filter(player -> player.getPosition().equals("goalie"))
                .collect(Collectors.toList());

        Player goalie = null;

        if(season.getCurrentDate().getTime() > season.getRegularSeasonEndsAt().getTime()){
            goalie = goalies.get(0);
            for (Player player : goalies) {
                if (player.getSaving() > goalie.getSaving()) {
                    goalie = player;
                }
            }
        }else{
            if(goalies.size() > 0){
                goalie = goalies.get(rand.nextInt(goalies.size()));
            }
        }
        return goalie;
    }
}


