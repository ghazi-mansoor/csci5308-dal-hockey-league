package com.groupten.createteam;

import com.groupten.console.ConsoleInterface;
import com.groupten.injector.Injector;

public class CreateTeam implements CreateTeamInterface {

    private ConsoleInterface console;
    private String conferenceName, divisionName, teamName, generalManager, headCoach;

    public CreateTeam(){

    }

    @Override
    public void userPrompt() {

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
        return conferenceName.length() > 0 && divisionName.length() > 0 && teamName.length() > 0 &&
                generalManager.length() > 0 && headCoach.length() > 0;
    }

    @Override
    public boolean ifExist() {
        return false;
    }


    @Override
    public void loadNewTeam() {
    }

    @Override
    public void persistAllData() {
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
