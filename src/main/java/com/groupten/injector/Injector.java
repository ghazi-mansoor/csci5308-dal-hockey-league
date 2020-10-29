package com.groupten.injector;

import com.groupten.IO.console.Console;
import com.groupten.IO.console.IConsole;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.persistence.dao.*;
import com.groupten.persistence.dao.database.*;
import com.groupten.statemachine.createteam.CreateTeam;
import com.groupten.statemachine.createteam.ICreateTeam;
import com.groupten.statemachine.jsonimport.IJSONImport;
import com.groupten.statemachine.jsonimport.JSONImport;
import com.groupten.statemachine.loadteam.ILoadTeam;
import com.groupten.statemachine.loadteam.LoadTeam;
import com.groupten.statemachine.simulation.ISimulation;
import com.groupten.statemachine.simulation.Simulation;
import com.groupten.statemachine.simulation.advancetime.AdvanceTime;
import com.groupten.statemachine.simulation.advancetime.IAdvanceTime;
import com.groupten.statemachine.simulation.aging.Aging;
import com.groupten.statemachine.simulation.aging.IAging;
import com.groupten.statemachine.simulation.generateplayoffschedule.GeneratePlayoffSchedule;
import com.groupten.statemachine.simulation.generateplayoffschedule.IGeneratePlayoffSchedule;
import com.groupten.statemachine.simulation.initializeseason.IInitializeSeason;
import com.groupten.statemachine.simulation.initializeseason.InitializeSeason;
import com.groupten.statemachine.simulation.training.ITraining;
import com.groupten.statemachine.simulation.training.Training;

public class Injector {

    private static Injector injector = null;
    private IConsole consoleInterface;

    private IJSONImport jsonInterface;
    private ICreateTeam createTeamInterface;
    private ILoadTeam loadTeamInterface;
    private ISimulation simulationInterface;
    private IInitializeSeason initializeSeasonInterface;
    private IAdvanceTime advanceTimeInterface;
    private IGeneratePlayoffSchedule generatePlayoffScheduleInterface;
    private ITraining trainingInterface;
    private IAging agingInterface;

    private ILeagueDAO leagueDatabaseInterface;
    private IConferenceDAO conferenceDatabaseInterface;
    private IDivisionDAO divisionDatabaseInterface;
    private ITeamDAO teamDatabaseInterface;
    private IPlayerDAO playerDatabaseInterface;

    private ILeagueModel leagueModel;

    private Injector() {
        consoleInterface = new Console();

        jsonInterface = new JSONImport();
        createTeamInterface = new CreateTeam();
        loadTeamInterface = new LoadTeam();
        simulationInterface = new Simulation();
        initializeSeasonInterface = new InitializeSeason();
        advanceTimeInterface = new AdvanceTime();
        generatePlayoffScheduleInterface = new GeneratePlayoffSchedule();
        trainingInterface = new Training();
        agingInterface = new Aging();

        leagueDatabaseInterface = new LeagueDAO();
        conferenceDatabaseInterface = new ConferenceDAO();
        divisionDatabaseInterface = new DivisionDAO();
        teamDatabaseInterface = new TeamDAO();
        playerDatabaseInterface = new PlayerDAO();

        leagueModel = new LeagueModel();
    }

    public static Injector injector() {
        if (injector == null) {
            injector = new Injector();
        }

        return injector;
    }

    public static void setInjector(Injector injector) {
        Injector.injector = injector;
    }

    public void setConsoleObject(IConsole consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public IConsole getConsoleObject() {
        return consoleInterface;
    }

    public void setJSONObject(IJSONImport jsonInterface) {
        this.jsonInterface = jsonInterface;
    }

    public IJSONImport getJSONObject() {
        return jsonInterface;
    }

    public void setCreateTeamObject(ICreateTeam createTeamInterface) {
        this.createTeamInterface = createTeamInterface;
    }

    public ICreateTeam getCreateTeamObject() {
        return createTeamInterface;
    }

    public void setLoadTeamObject(ILoadTeam loadTeamInterface) {
        this.loadTeamInterface = loadTeamInterface;
    }

    public ILoadTeam getLoadTeamObject() {
        return loadTeamInterface;
    }

    public void setSimulationObject(ISimulation simulationInterface) {
        this.simulationInterface = simulationInterface;
    }

    public ISimulation getSimulationObject() {
        return simulationInterface;
    }

    public void setInitializeSeasonObject(IInitializeSeason initializeSeasonInterface) {
        this.initializeSeasonInterface = initializeSeasonInterface;
    }

    public IInitializeSeason getInitializeSeasonsObject() {
        return initializeSeasonInterface;
    }

    public void setAdvanceTimeObject(IAdvanceTime advanceTimeInterface) {
        this.advanceTimeInterface = advanceTimeInterface;
    }

    public IAdvanceTime getAdvanceTimeObject() {
        return advanceTimeInterface;
    }

    public void setGeneratePlayoffScheduleObject(IGeneratePlayoffSchedule generatePlayoffScheduleInterface) {
        this.generatePlayoffScheduleInterface = generatePlayoffScheduleInterface;
    }

    public IGeneratePlayoffSchedule getGeneratePlayoffScheduleeObject() {
        return generatePlayoffScheduleInterface;
    }

    public void setTrainingObject(ITraining trainingInterface) {
        this.trainingInterface = trainingInterface;
    }

    public ITraining getTrainingObject() {
        return trainingInterface;
    }

    public void setAgingObject(IAging agingInterface) {
        this.agingInterface = agingInterface;
    }

    public IAging getAgingObject() {
        return agingInterface;
    }


    public void setLeagueDatabaseObject(ILeagueDAO leagueDatabaseInterface) {
        this.leagueDatabaseInterface = leagueDatabaseInterface;
    }

    public ILeagueDAO getLeagueDatabaseObject() {
        return leagueDatabaseInterface;
    }

    public void setConferenceDatabaseObject(IConferenceDAO conferenceDatabaseInterface) {
        this.conferenceDatabaseInterface = conferenceDatabaseInterface;
    }

    public IConferenceDAO getConferenceDatabaseObject() {
        return conferenceDatabaseInterface;
    }

    public void setDivisionDatabaseObject(IDivisionDAO divisionDatabaseInterface) {
        this.divisionDatabaseInterface = divisionDatabaseInterface;
    }

    public IDivisionDAO getDivisionDatabaseObject() {
        return divisionDatabaseInterface;
    }

    public void setTeamDatabaseObject(ITeamDAO teamDatabaseInterface) {
        this.teamDatabaseInterface = teamDatabaseInterface;
    }

    public ITeamDAO getTeamDatabaseObject() {
        return teamDatabaseInterface;
    }

    public void setPlayerDatabaseObject(IPlayerDAO playerDatabaseInterface) {
        this.playerDatabaseInterface = playerDatabaseInterface;
    }

    public IPlayerDAO getPlayerDatabaseObject() {
        return playerDatabaseInterface;
    }

    public void setLeagueModelObject(ILeagueModel leagueModel) {
        this.leagueModel = leagueModel;
    }

    public ILeagueModel getLeagueModelObject() {
        return leagueModel;
    }

}
