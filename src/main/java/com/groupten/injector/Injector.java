package com.groupten.injector;

import com.groupten.dao.database.ConferenceDAO;
import com.groupten.dao.IConferenceDAO;
import com.groupten.dao.database.DivisionDAO;
import com.groupten.dao.IDivisionDAO;
import com.groupten.dao.database.LeagueDAO;
import com.groupten.dao.ILeagueDAO;
import com.groupten.dao.database.PlayerDAO;
import com.groupten.dao.IPlayerDAO;
import com.groupten.dao.database.TeamDAO;
import com.groupten.dao.ITeamDAO;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.statemachine.console.Console;
import com.groupten.statemachine.console.IConsole;
import com.groupten.statemachine.json.JSON;
import com.groupten.statemachine.json.IJSON;
import com.groupten.statemachine.createteam.CreateTeam;
import com.groupten.statemachine.createteam.ICreateTeam;
import com.groupten.statemachine.loadteam.LoadTeam;
import com.groupten.statemachine.loadteam.ILoadTeam;
import com.groupten.statemachine.simulation.Simulation;
import com.groupten.statemachine.simulation.ISimulation;

public class Injector {

    private static Injector injector = null;
    private IConsole consoleInterface;

    private IJSON jsonInterface;
    private ICreateTeam createTeamInterface;
    private ILoadTeam loadTeamInterface;
    private ISimulation simulationInterface;

    private ILeagueDAO leagueDatabaseInterface;
    private IConferenceDAO conferenceDatabaseInterface;
    private IDivisionDAO divisionDatabaseInterface;
    private ITeamDAO teamDatabaseInterface;
    private IPlayerDAO playerDatabaseInterface;

    private ILeagueModel leagueModel;

    private Injector() {
        consoleInterface = new Console();

        jsonInterface = new JSON();
        createTeamInterface = new CreateTeam();
        loadTeamInterface = new LoadTeam();
        simulationInterface = new Simulation();

        leagueDatabaseInterface = new LeagueDAO();
        conferenceDatabaseInterface = new ConferenceDAO();
        divisionDatabaseInterface = new DivisionDAO();
        teamDatabaseInterface = new TeamDAO();
        playerDatabaseInterface = new PlayerDAO();

        leagueModel = new LeagueModel();
    }

    public static Injector injector(){
        if(injector == null) {
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

    public void setJSONObject(IJSON jsonInterface) {
        this.jsonInterface = jsonInterface;
    }

    public IJSON getJSONObject() {
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
