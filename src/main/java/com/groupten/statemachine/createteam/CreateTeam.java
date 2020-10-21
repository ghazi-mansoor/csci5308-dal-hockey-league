package com.groupten.statemachine.createteam;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.statemachine.console.IConsole;
import com.groupten.injector.Injector;

public class CreateTeam implements ICreateTeam {

    private String conferenceName, divisionName, teamName, generalManager, headCoach;
    private League leagueLOM;

    public CreateTeam() { }

    @Override
    public void userPromptForNewTeam() {

        IConsole console = Injector.injector().getConsoleObject();

        console.printLine("Enter the Conference name:");
        conferenceName = console.readLine();

        console.printLine("Enter the Division name:");
        divisionName = console.readLine();

        console.printLine("Enter the Team name:");
        teamName = console.readLine();

        console.printLine("Enter the General Manager name:");
        generalManager = console.readLine();

        console.printLine("Enter the Head Coach name:");
        headCoach = console.readLine();

    }

    @Override
    public boolean validateUserInput() {
        return (conferenceName.length() > 0 && divisionName.length() > 0 && teamName.length() > 0 &&
                generalManager.length() > 0 && headCoach.length() > 0);
    }

    @Override
    public boolean ifConferenceAndDivisionExist() {
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        // leagueLOM = (League) leagueModel.getLeagues().values().toArray()[0];
        leagueLOM = leagueModel.getCurrentLeague();
        //return leagueLOM.doEntitiesExistInMemory(conferenceName, divisionName);
        if (leagueLOM.containsConference(conferenceName)) {
            Conference conference = leagueLOM.getConference(conferenceName);
            return conference.containsDivision(divisionName);
        } else {
            return false;
        }
    }

    @Override
    public boolean instantiateNewTeam() {
        // return leagueLOM.addTeamToLeagueModel(teamName, generalManager, headCoach, Injector.injector().getTeamDatabaseObject());
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        leagueLOM = leagueModel.getCurrentLeague();
        Conference conference = leagueLOM.getConference(conferenceName);
        Division division = conference.getDivision(divisionName);
        // Check the Team classe's fields: GeneralManager and Coach
        // I have added a constructor that accepts objects of GeneralManager and Coach instances
        Team team = new Team(teamName);
        return division.addTeam(team);
    }

    @Override
    public void persistLeagueModel() {
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        // leagueModel.saveLeagueModelToDB();
        leagueModel.saveLeagueModel();
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setGeneralManager(String generalManager) {
        this.generalManager = generalManager;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }
}
