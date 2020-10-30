package com.groupten.injector;

import com.groupten.IO.comparator.Comparator;
import com.groupten.IO.comparator.IComparator;
import com.groupten.persistence.dao.database.ConferenceDAO;
import com.groupten.persistence.dao.IConferenceDAO;
import com.groupten.persistence.dao.database.DivisionDAO;
import com.groupten.persistence.dao.IDivisionDAO;
import com.groupten.persistence.dao.database.LeagueDAO;
import com.groupten.persistence.dao.ILeagueDAO;
import com.groupten.persistence.dao.database.PlayerDAO;
import com.groupten.persistence.dao.IPlayerDAO;
import com.groupten.persistence.dao.database.TeamDAO;
import com.groupten.persistence.dao.ITeamDAO;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.IO.console.Console;
import com.groupten.IO.console.IConsole;
import com.groupten.statemachine.createteam.CreateTeam;
import com.groupten.statemachine.jsonimport.JSONImport;
import com.groupten.statemachine.jsonimport.IJSONImport;
import com.groupten.statemachine.createteam.ICreateTeam;
import com.groupten.statemachine.loadteam.LoadTeam;
import com.groupten.statemachine.loadteam.ILoadTeam;
import com.groupten.statemachine.simulation.Simulation;
import com.groupten.statemachine.simulation.ISimulation;

public class Injector {

    private static Injector instance = null;
    private IConsole consoleInterface;

    private IJSONImport jsonInterface;
    private ICreateTeam createTeamInterface;
    private ILoadTeam loadTeamInterface;
    private ISimulation simulationInterface;
    private IComparator comparatorInterface;

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
        comparatorInterface = new Comparator();

        leagueDatabaseInterface = new LeagueDAO();
        conferenceDatabaseInterface = new ConferenceDAO();
        divisionDatabaseInterface = new DivisionDAO();
        teamDatabaseInterface = new TeamDAO();
        playerDatabaseInterface = new PlayerDAO();

        leagueModel = new LeagueModel();
    }

    public static Injector instance() {
        if (instance == null) {
            instance = new Injector();
        }

        return instance;
    }

    public static void setInstance(Injector instance) {
        Injector.instance = instance;
    }

    public void setConsoleObject(IConsole consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public IConsole getConsoleObject() {
        return consoleInterface;
    }

    public void setComparatorObject(IComparator comparatorInterface) {
        this.comparatorInterface = comparatorInterface;
    }

    public IComparator getComparatorObject() {
        return comparatorInterface;
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
