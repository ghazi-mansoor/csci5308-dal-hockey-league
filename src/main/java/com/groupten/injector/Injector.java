package com.groupten.injector;

import com.groupten.jdbc.conference.ConferenceDAO;
import com.groupten.jdbc.conference.IConferenceDAO;
import com.groupten.jdbc.division.DivisionDAO;
import com.groupten.jdbc.division.IDivisionDAO;
import com.groupten.jdbc.league.LeagueDAO;
import com.groupten.jdbc.league.ILeagueDAO;
import com.groupten.jdbc.player.PlayerDAO;
import com.groupten.jdbc.player.IPlayerDAO;
import com.groupten.jdbc.team.TeamDAO;
import com.groupten.jdbc.team.ITeamDAO;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.statemachine.console.Console;
import com.groupten.statemachine.console.ConsoleInterface;
import com.groupten.statemachine.json.JSON;
import com.groupten.statemachine.json.JSONInterface;
import com.groupten.statemachine.createteam.CreateTeam;
import com.groupten.statemachine.createteam.CreateTeamInterface;
import com.groupten.statemachine.loadteam.LoadTeam;
import com.groupten.statemachine.loadteam.LoadTeamInterface;
import com.groupten.statemachine.simulation.Simulation;
import com.groupten.statemachine.simulation.SimulationInterface;

public class Injector {

    private static Injector injector = null;
    private ConsoleInterface consoleInterface;

    private JSONInterface jsonInterface;
    private CreateTeamInterface createTeamInterface;
    private LoadTeamInterface loadTeamInterface;
    private SimulationInterface simulationInterface;

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

    public void setConsoleObject(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public ConsoleInterface getConsoleObject() {
        return consoleInterface;
    }

    public void setJSONObject(JSONInterface jsonInterface) {
        this.jsonInterface = jsonInterface;
    }

    public JSONInterface getJSONObject() {
        return jsonInterface;
    }

    public void setCreateTeamObject(CreateTeamInterface createTeamInterface) {
        this.createTeamInterface = createTeamInterface;
    }

    public CreateTeamInterface getCreateTeamObject() {
        return createTeamInterface;
    }

    public void setLoadTeamObject(LoadTeamInterface loadTeamInterface) {
        this.loadTeamInterface = loadTeamInterface;
    }

    public LoadTeamInterface getLoadTeamObject() {
        return loadTeamInterface;
    }

    public void setSimulationObject(SimulationInterface simulationInterface) {
        this.simulationInterface = simulationInterface;
    }

    public SimulationInterface getSimulationObject() {
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
