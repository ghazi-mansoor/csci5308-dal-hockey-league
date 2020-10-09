package com.groupten.statemachine.loadteam;

import com.groupten.jdbc.team.Team;
import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.statemachine.console.ConsoleInterface;
import com.groupten.injector.Injector;

import java.util.HashMap;
import java.util.List;

public class LoadTeam implements LoadTeamInterface {

    private ConsoleInterface console;
    private String teamName;
    private int leagueID;

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
    public boolean validateUserInput() {
        return teamName.length() > 0;
    }

    @Override
    public boolean doesTeamExist() {
        TeamInterface teamDB = Injector.injector().getTeamDatabaseObject();
        List<HashMap<String, Object>> teamList = teamDB.getTeams("teamName", teamName);
        if(teamList.size() > 0){
            leagueID = (int) teamList.get(0).get("leagueId");
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean loadExistingLeague() {
        System.out.println(leagueID);
//        LeagueModel leagueModel = new LeagueModel();
//        return leagueModel.loadLeagueFromDB(leagueID);
        return true;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
