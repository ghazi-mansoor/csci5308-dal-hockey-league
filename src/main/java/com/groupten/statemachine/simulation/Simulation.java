package com.groupten.statemachine.simulation;

import com.groupten.IO.console.IConsole;
import com.groupten.IO.serializedata.ISerializeData;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.statemachine.simulation.advancetime.IAdvanceTime;
import com.groupten.statemachine.simulation.aging.IAging;
import com.groupten.statemachine.simulation.generateplayoffschedule.IGeneratePlayoffSchedule;
import com.groupten.statemachine.simulation.initializeseason.IInitializeSeason;
import com.groupten.statemachine.simulation.injury.Injury;
import com.groupten.statemachine.simulation.simulategame.ISimulateGame;
import com.groupten.statemachine.simulation.trading.ITrading;
import com.groupten.statemachine.simulation.training.ITraining;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Simulation implements ISimulation {
    private League leagueLOM;
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
    public void init(League leagueLOM,int numberOfSeasons){
        this.leagueLOM = leagueLOM;
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
        season = new Season(leagueLOM,year);
        initializeSeason.setSeason(season);
        if(initializeSeason.generateRegularSchedule()){
            console.printLine("Regular schedule generated.");
            advanceTime();
        }else{
            console.printLine("FAILURE: Some error occurred.");
        }
    }

    private void advanceTime(){
        IConsole console = Injector.instance().getConsoleObject();
        IAdvanceTime advanceTime = Injector.instance().getAdvanceTimeObject();
//        console.printLine("Advancing to next day");
        advanceTime.setSeason(season);
        advanceTime.advanceTime();
        if(season.isTodayRegularSeasonEnd()){
            generatePlayoffSchedule();
        }else{
            training();
        }
    }

    private void generatePlayoffSchedule(){
        IConsole console = Injector.instance().getConsoleObject();
        IGeneratePlayoffSchedule generatePlayoffSchedule = Injector.instance().getGeneratePlayoffScheduleeObject();
        generatePlayoffSchedule.setSeason(season);
        console.printLine("Generating playoff schedule");
        if(generatePlayoffSchedule.generatePlayoffSchedule()){
            console.printLine("Playoff schedule generated");
            training();
        }else{
            console.printLine("FAILURE: Some error occurred.");
        }
    }

    private void training(){
        IConsole console = Injector.instance().getConsoleObject();
        ITraining training = Injector.instance().getTrainingObject();
//        console.printLine("Training teams");
        if(daysSinceStatsIncreased > leagueLOM.getTrainingConfig().getDaysUntilStatIncreaseCheck()){
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
//            console.printLine("Trading ended");
        }else{
            executeTrades();
        }
        aging();
    }

    private void simulateGame(Schedule schedule){
        ISimulateGame simulateGame = Injector.instance().getSimulateGameObject();
        simulateGame.setSeason(season);
        simulateGame.simulateGame(schedule);
        injuryCheck();
    }

    private void injuryCheck(){
        Injury.checkPlayerInjuriesAcrossLeague(leagueLOM);
    }

    private void executeTrades(){
        IConsole console = Injector.instance().getConsoleObject();
        ITrading trading = Injector.instance().getTradingObject();
//        console.printLine("Trading teams");
        trading.startTrading();
    }

    private void aging(){
        IAging aging = Injector.instance().getAgingObject();
        aging.advanceEveryPlayersAge(season.getLeague(),1);
        IConsole console = Injector.instance().getConsoleObject();
        if(season.isWinnerDetermined()){
            console.printLine("Season won by:"+ season.getWinner().getTeamName());
            if(numberOfSeasons > 0){
                year++;
                initializeSeason();
            }else{
                persist();
                end();
            }
        }else{
//            persist();
            advanceTime();
        }
    }

    private void persist(){
        IConsole console = Injector.instance().getConsoleObject();
        console.printLine("Simulation saved to db");
//        leagueLOM.saveLeague();
        console.printLine("Exporting to json file");
        ISerializeData serializeData = Injector.instance().getSerializeDataObject();
        serializeData.exportData(leagueLOM);
    }

    private void end(){
        IConsole console = Injector.instance().getConsoleObject();
        console.printLine("Done.");
    }
}
