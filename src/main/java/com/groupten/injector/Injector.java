package com.groupten.injector;

import com.groupten.jdbc.league.League;
import com.groupten.jdbc.league.LeagueInterface;
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
    private LeagueInterface leagueDatabaseInterface;
    private SimulationInterface simulationInterface;

    private Injector() {
        consoleInterface = new Console();
        jsonInterface = new JSON();
        createTeamInterface = new CreateTeam();
        loadTeamInterface = new LoadTeam();
        leagueDatabaseInterface = new League();
        simulationInterface = new Simulation();
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

    public void setJSONObject(JSONInterface jsonInterface) {
        this.jsonInterface = jsonInterface;
    }

    public void setCreateTeamObject(CreateTeamInterface createTeamInterface) {
        this.createTeamInterface = createTeamInterface;
    }

    public void setLoadTeamObject(LoadTeamInterface loadTeamInterface) {
        this.loadTeamInterface = loadTeamInterface;
    }

    public void setLeagueDatabaseObject(LeagueInterface leagueDatabaseInterface) {
        this.leagueDatabaseInterface = leagueDatabaseInterface;
    }

    public void setSimulationObject(SimulationInterface simulationInterface) {
        this.simulationInterface = simulationInterface;
    }

    public ConsoleInterface getConsoleObject() {
        return consoleInterface;
    }

    public JSONInterface getJSONObject() {
        return jsonInterface;
    }

    public CreateTeamInterface getCreateTeamObject() {
        return createTeamInterface;
    }

    public LoadTeamInterface getLoadTeamObject() {
        return loadTeamInterface;
    }

    public LeagueInterface getLeagueDatabaseObject() {
        return leagueDatabaseInterface;
    }

    public SimulationInterface getSimulationObject() {
        return simulationInterface;
    }

}
