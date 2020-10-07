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
        // Invoke the method of DB to check if team exist
        return false;
    }

    @Override
    public void loadTeamsLeagueID() {
        // Invoke the method of DB to get team and it league ID
    }

    @Override
    public void loadExistingLeague() {
        // Invoke the method in LOM to load required league in memory
    }
}
