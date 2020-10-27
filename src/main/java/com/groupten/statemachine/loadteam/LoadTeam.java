package com.groupten.statemachine.loadteam;

import com.groupten.persistence.dao.ITeamDAO;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;

import java.util.HashMap;
import java.util.List;

public class LoadTeam implements ILoadTeam {

    private IConsole console;
    private String teamName;
    private int leagueID;

    public LoadTeam() {}

    public LoadTeam(ITeamDAO teamDBObj) {
        Injector.injector().setTeamDatabaseObject(teamDBObj);
    }

    @Override
    public void userPromptForLoadingTeam() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Enter the Team name:");
        teamName = console.readLine();
    }

    @Override
    public boolean validateUserInput() {
        return teamName.length() > 0;
    }

    @Override
    public boolean doesTeamExist() {
        ITeamDAO teamDB = Injector.injector().getTeamDatabaseObject();
        List<HashMap<String, Object>> teamList = teamDB.getTeams("teamName", teamName);
        if(teamList.size() > 0){
            leagueID = (int) teamList.get(0).get("leagueId");
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean loadExistingLeague() {
        LeagueModel leagueModel = new LeagueModel();
        return leagueModel.loadLeague(leagueID);
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
