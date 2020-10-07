package com.groupten.statemachine.loadteam;

import com.groupten.jdbc.team.Team;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.statemachine.console.ConsoleInterface;
import com.groupten.injector.Injector;

import java.util.HashMap;
import java.util.List;

public class LoadTeam implements LoadTeamInterface {

    private ConsoleInterface console;
    private String teamName;

    public LoadTeam() {}

    public LoadTeam(TeamInterface teamDBObj) {
        Injector.injector().setTeamDatabaseObject(teamDBObj);
    }

    @Override
    public void userPromptForLoadingTeam() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Enter the Team name:");
        teamName = console.readLine();
    }

    @Override
    public boolean doesTeamExist() {
        TeamInterface teamDB = Injector.injector().getTeamDatabaseObject();
        List<HashMap<String, Object>> teamList = teamDB.getTeams("teamName", teamName);
        return teamList.size() > 0;
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
