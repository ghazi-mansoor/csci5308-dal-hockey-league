package com.groupten.statemachine.simulation;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.statemachine.simulation.advancetime.IAdvanceTime;
import com.groupten.statemachine.simulation.aging.IAging;
import com.groupten.statemachine.simulation.generateplayoffschedule.IGeneratePlayoffSchedule;
import com.groupten.statemachine.simulation.initializeseason.IInitializeSeason;
import com.groupten.statemachine.simulation.training.ITraining;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Simulation implements ISimulation {
    private Season season;
    private int numberOfSeasons;
    private int year;

    public Simulation(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.year = cal.get(Calendar.YEAR);
    }

    @Override
    public void init(int numberOfSeasons){
        this.numberOfSeasons = numberOfSeasons;
        if(this.numberOfSeasons > 0){
            initializeSeason();
        }
    }

    private void initializeSeason(){
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League leagueLOM = leagueModel.getCurrentLeague();
        IConsole console = Injector.injector().getConsoleObject();
        IInitializeSeason initializeSeason = Injector.injector().getInitializeSeasonsObject();
        console.printLine("Initializing season");
        numberOfSeasons--;
        this.season = new Season(leagueLOM,year);
        initializeSeason.setSeason(season);
        if(initializeSeason.generateRegularSchedule()){
            advanceTime();
        }else{
            console.printLine("FAILURE: Some error occurred.");
        }
        console.printLine("Regular schedule generated.");

    }

    private void advanceTime(){
        IConsole console = Injector.injector().getConsoleObject();
        IAdvanceTime advanceTime = Injector.injector().getAdvanceTimeObject();
        console.printLine("Advancing to next day");
        advanceTime.advanceTime();
        if(season.isTodayRegularSeasonEnd()){
            generatePlayoffSchedule();
        }else{
            training();
        }
    }

    private void generatePlayoffSchedule(){
        IConsole console = Injector.injector().getConsoleObject();
        IGeneratePlayoffSchedule generatePlayoffSchedule = Injector.injector().getGeneratePlayoffScheduleeObject();
        console.printLine("Generating playoff schedule");
        if(generatePlayoffSchedule.generatePlayoffSchedule()){
            console.printLine("Playoff schedule generated");
            training();
        }else{
            console.printLine("FAILURE: Some error occurred.");
        }
    }

    private void training(){
        IConsole console = Injector.injector().getConsoleObject();
        ITraining training = Injector.injector().getTrainingObject();
        console.printLine("Training teams");
        training.train();

        List<Schedule> scheduleList = season.schedulesToday();
        if(scheduleList.size() > 0 ){
            scheduleList.forEach(schedule -> {
                simulateGame();
            });
        }

        if(season.isTradeEnded()){
            aging();
        }else{
            executeTrades();
        }
    }

    private void simulateGame(){
        //ToDo Simulate Games
        injuryCheck();

    }

    private void injuryCheck(){
        //ToDo Injury Check
    }

    private void executeTrades(){
        //ToDo execute trade
    }

    private void aging(){
        IAging aging = Injector.injector().getAgingObject();
        aging.advanceEveryPlayersAge(season.getLeague(),1);

        if(season.isWinnerDetermined()){
            if(numberOfSeasons > 0){
                year++;
                initializeSeason();
            }else{
                persist();
            }
        }else{
            persist();
            advanceTime();
        }
    }

    private void persist(){
        //ToDo persist
        IConsole console = Injector.injector().getConsoleObject();

        end();
        console.printLine("Simulation saved to db");

    }

    private void end(){
        IConsole console = Injector.injector().getConsoleObject();
        console.printLine("Done.");
    }
}
