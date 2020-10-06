package com.groupten.statemachine.loadteam;

import com.groupten.statemachine.console.ConsoleInterface;
import com.groupten.injector.Injector;

public class LoadTeam implements LoadTeamInterface {

    private ConsoleInterface console;
    private String teamName;

    @Override
    public void userPromptForLoadingTeam() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Enter the Team name:");
        teamName = console.readLine();
    }

    @Override
    public boolean doesTeamExist() {
        // Check in LOM if team exist
        return false;
    }

    @Override
    public void loadExistingTeam() {
        // Invoke the method in DB to instantiate team
    }

    @Override
    public void loadExistingLeague() {
        // Invoke the method in DB to instantiate league
    }
}
