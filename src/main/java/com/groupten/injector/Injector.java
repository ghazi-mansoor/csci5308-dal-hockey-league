package com.groupten.injector;

import com.groupten.jdbc.conference.Conference;
import com.groupten.jdbc.conference.ConferenceInterface;
import com.groupten.jdbc.division.Division;
import com.groupten.jdbc.division.DivisionInterface;
import com.groupten.jdbc.league.League;
import com.groupten.jdbc.league.LeagueInterface;
import com.groupten.jdbc.player.Player;
import com.groupten.jdbc.player.PlayerInterface;
import com.groupten.jdbc.team.Team;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.LeagueModel;
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

    private LeagueInterface leagueDatabaseInterface;
    private ConferenceInterface conferenceDatabaseInterface;
    private DivisionInterface divisionDatabaseInterface;
    private TeamInterface teamDatabaseInterface;
    private PlayerInterface playerDatabaseInterface;

    private LeagueModel leagueModel;

    private Injector() {
        consoleInterface = new Console();

        jsonInterface = new JSON();
        createTeamInterface = new CreateTeam();
        loadTeamInterface = new LoadTeam();
        simulationInterface = new Simulation();

        leagueDatabaseInterface = new League();
        conferenceDatabaseInterface = new Conference();
        divisionDatabaseInterface = new Division();
        teamDatabaseInterface = new Team();
        playerDatabaseInterface = new Player();

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

    public void setLeagueDatabaseObject(LeagueInterface leagueDatabaseInterface) {
        this.leagueDatabaseInterface = leagueDatabaseInterface;
    }

    public LeagueInterface getLeagueDatabaseObject() {
        return leagueDatabaseInterface;
    }

    public void setConferenceDatabaseObject(ConferenceInterface conferenceDatabaseInterface) {
        this.conferenceDatabaseInterface = conferenceDatabaseInterface;
    }

    public ConferenceInterface getConferenceDatabaseObject() {
        return conferenceDatabaseInterface;
    }

    public void setDivisionDatabaseObject(DivisionInterface divisionDatabaseInterface) {
        this.divisionDatabaseInterface = divisionDatabaseInterface;
    }

    public DivisionInterface getDivisionDatabaseObject() {
        return divisionDatabaseInterface;
    }

    public void setTeamDatabaseObject(TeamInterface teamDatabaseInterface) {
        this.teamDatabaseInterface = teamDatabaseInterface;
    }

    public TeamInterface getTeamDatabaseObject() {
        return teamDatabaseInterface;
    }

    public void setPlayerDatabaseObject(PlayerInterface playerDatabaseInterface) {
        this.playerDatabaseInterface = playerDatabaseInterface;
    }

    public PlayerInterface getPlayerDatabaseObject() {
        return playerDatabaseInterface;
    }

    public void setLeagueModelObject(LeagueModel leagueModel) {
        this.leagueModel = leagueModel;
    }

    public LeagueModel getLeagueModelObject() {
        return leagueModel;
    }

}
