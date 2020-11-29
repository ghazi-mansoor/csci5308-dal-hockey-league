package com.groupten.statemachine.simulation;

import com.groupten.IO.console.IConsole;
import com.groupten.IO.serializedata.ISerializeData;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.season.ISeasonObserver;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.seasonstat.SeasonStat;
import com.groupten.statemachine.simulation.advancetime.IAdvanceTime;
import com.groupten.statemachine.simulation.aging.IAging;
import com.groupten.statemachine.simulation.generateplayoffschedule.IGeneratePlayoffSchedule;
import com.groupten.statemachine.simulation.initializeseason.IInitializeSeason;
import com.groupten.statemachine.simulation.injury.Injury;
import com.groupten.statemachine.simulation.simulategame.ISimulateGame;
import com.groupten.statemachine.simulation.trading.ITrading;
import com.groupten.statemachine.simulation.training.ITraining;
import com.groupten.statemachine.simulation.trophy.ITrophy;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Simulation implements ISimulation {
    private League league;
    private Season season;
    private int numberOfSeasons;
    private int year;
    private int daysSinceStatsIncreased;

    public Simulation(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.year = cal.get(Calendar.YEAR);
    }

    @Override
    public void init(int numberOfSeasons){
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        this.league = leagueModel.getCurrentLeague();
        this.numberOfSeasons = numberOfSeasons;
        if(this.numberOfSeasons > 0){
            initializeSeason();
        }
    }

    private void initializeSeason(){
        IConsole console = Injector.instance().getConsoleObject();
        IInitializeSeason initializeSeason = Injector.instance().getInitializeSeasonsObject();
        console.printLine("Initializing season");
        numberOfSeasons--;
        daysSinceStatsIncreased = 0;
        season = new Season(year);
        season.attach((ISeasonObserver) Injector.instance().getTrophyObject());
        if(initializeSeason.generateRegularSchedule(season)){
            console.printLine("Regular schedule generated.");
            advanceTime();
        }else{
            console.printLine("FAILURE: Some error occurred.");
        }
    }

    private void advanceTime(){
        IConsole console = Injector.instance().getConsoleObject();
        IAdvanceTime advanceTime = Injector.instance().getAdvanceTimeObject();
        advanceTime.advanceTime(season);
        if(season.isTodayRegularSeasonEnd()){
            generatePlayoffSchedule();
        }else{
            training();
        }
    }

    private void generatePlayoffSchedule(){
        IConsole console = Injector.instance().getConsoleObject();
        IGeneratePlayoffSchedule generatePlayoffSchedule = Injector.instance().getGeneratePlayoffScheduleeObject();
        console.printLine("Generating playoff schedule");
        if(generatePlayoffSchedule.generatePlayoffSchedule(season)){
            console.printLine("Playoff schedule generated");
            training();
        }else{
            console.printLine("FAILURE: Some error occurred.");
        }
    }

    private void training(){
        IConsole console = Injector.instance().getConsoleObject();
        ITraining training = Injector.instance().getTrainingObject();
        if(daysSinceStatsIncreased > league.getTrainingConfig().getDaysUntilStatIncreaseCheck()){
            training.trainPlayers();
            daysSinceStatsIncreased = 0;
        }else{
            daysSinceStatsIncreased++;
        }

        List<Schedule> scheduleList = season.schedulesToday();
        if(scheduleList.size() > 0 ){
            scheduleList.forEach(schedule -> {
                simulateGame(schedule);
            });
        }

        if(season.isTradeEnded()){
        }else{
//            executeTrades();
        }
        aging();
    }

    private void simulateGame(Schedule schedule){
        ISimulateGame simulateGame = Injector.instance().getSimulateGameObject();
        simulateGame.simulateGame(season, schedule);
        injuryCheck();
    }

    private void injuryCheck(){
        Injury.checkPlayerInjuriesAcrossLeague(league);
    }

    private void executeTrades(){
        IConsole console = Injector.instance().getConsoleObject();
        ITrading trading = Injector.instance().getTradingObject();
        trading.startTrading();
    }

    private void aging(){
        IAging aging = Injector.instance().getAgingObject();
        aging.advanceEveryPlayersAge(this.league,1);
        IConsole console = Injector.instance().getConsoleObject();
        if(season.isWinnerDetermined()){
            console.printLine("Season won by:"+ season.getSeasonWinner().getTeamName());
            SeasonStat seasonStat = season.getSeasonStat();
            console.printLine("Season Stats");
            console.printLine("Shots per Game:"+ seasonStat.getAvgShots());
            console.printLine("Penalties per Game:"+ seasonStat.getAvgPenalties());
            console.printLine("Goals per Game:"+ seasonStat.getAvgGoals());
            console.printLine("Saves per Game:"+ seasonStat.getAvgSaves());

            ITrophy trophy = Injector.instance().getTrophyObject();
            trophy.awardTrophy();
            trophy.trophyWinners();

            if(numberOfSeasons > 0){
                year++;
                initializeSeason();
            }else{
                persist();
                end();
            }
        }else{
            advanceTime();
        }
    }

    private void persist(){
        IConsole console = Injector.instance().getConsoleObject();
        console.printLine("Exporting to json file");
        ISerializeData serializeData = Injector.instance().getSerializeDataObject();
        String path = "src/main/resources/";
        serializeData.exportData(league, path);
        console.printLine("Simulation saved to db");
        league.save();
    }

    private void end(){
        IConsole console = Injector.instance().getConsoleObject();
        console.printLine("Done.");
    }
}
