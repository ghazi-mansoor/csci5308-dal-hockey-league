package com.groupten.injector;

import com.groupten.console.Console;
import com.groupten.console.ConsoleInterface;
import com.groupten.json.JSON;
import com.groupten.json.JSONInterface;
import com.groupten.createteam.CreateTeam;
import com.groupten.createteam.CreateTeamInterface;
import com.groupten.loadteam.LoadTeam;
import com.groupten.loadteam.LoadTeamInterface;

public class Injector {

    private static Injector injector = null;
    private ConsoleInterface consoleInterface;
    private JSONInterface jsonInterface;
    private CreateTeamInterface createTeamInterface;
    private LoadTeamInterface loadTeamInterface;

    private Injector() {
        consoleInterface = new Console();
        jsonInterface = new JSON();
        createTeamInterface = new CreateTeam();
        loadTeamInterface = new LoadTeam();
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

}
