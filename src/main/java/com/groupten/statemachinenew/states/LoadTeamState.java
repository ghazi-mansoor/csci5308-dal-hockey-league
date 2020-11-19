package com.groupten.statemachinenew.states;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import com.groupten.persistence.dao.ITeamDAO;
import com.groupten.statemachinenew.StateMachine;

import java.util.HashMap;
import java.util.List;

public class LoadTeamState implements IState {
    StateMachine stateMachine;

    public LoadTeamState(StateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    @Override
    public void doAction() {
        IConsole console = Injector.instance().getConsoleObject();
        console.printLine("Enter the Team name:");
        String teamName = console.readLine();
        if(teamName.length() <= 0){
            console.printLine("INVALID: Team name");
            stateMachine.retry();
        }
        console.printLine("Loading team and league");
        if(loadLeague(teamName)){
            console.printLine("SUCCESS: Team Selected");
            stateMachine.setState(new PlayerChoiceState(stateMachine));
            stateMachine.doAction();
        }else{
            console.printLine("FAILURE: Some error occurred");
            stateMachine.retry();
        }
    }

    private boolean loadLeague(String teamName){
        ITeamDAO teamDB = Injector.instance().getTeamDatabaseObject();
        IConsole console = Injector.instance().getConsoleObject();
        int leagueID;

        List<HashMap<String, Object>> teamList = teamDB.getTeams("teamName", teamName);
        if (teamList.size() <= 0) {
            console.printLine("ERROR: Team does not exist");
            return false;
        } else {
            leagueID = (int) teamList.get(0).get("leagueId");
            LeagueModel leagueModel = new LeagueModel();
            return leagueModel.loadLeagueModel(leagueID);
        }
    }
}
