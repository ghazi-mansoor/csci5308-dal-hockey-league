package com.groupten.statemachine.createteam;

import com.groupten.statemachine.console.ConsoleInterface;
import com.groupten.injector.Injector;

public class CreateTeam implements CreateTeamInterface {

    private ConsoleInterface console;
    private String conferenceName, divisionName, teamName, generalManager, headCoach;

    @Override
    public void userPromptForNewTeam() {

        console = Injector.injector().getConsoleObject();

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
    public boolean ifExist() {
        // Invoke the methods in LOM to check if data exist
        return false;
    }

    @Override
    public boolean instantiateNewTeam() {
        // Invoke the method in LOM to instantiate new team
        return false;
    }

    @Override
    public boolean persistLeagueModel() {
        // Invoke the method in LOM to call stored procedure for persistence
        return false;
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
