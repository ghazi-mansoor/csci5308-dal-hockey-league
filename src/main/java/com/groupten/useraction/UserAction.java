package com.groupten.useraction;

import com.groupten.console.ConsoleInterface;
import com.groupten.injector.Injector;

public class UserAction implements UserActionInterface {

    ConsoleInterface console;

    public UserAction(){
        console = Injector.injector().getConsoleObject();
    }

    @Override
    public boolean createTeam() {

        console.printLine("JSON import is successful");
        console.printLine("Proceeding to team creation. Please answer the below questions.");

        console.printLine("Enter the Confernce name:");
        String conferenceName = console.readLine();

        // Check if the conferenceName exist

        console.printLine("Enter the Division name:");
        String divisionName = console.readLine();

        // Check if the divisionName exist
        // Attach the divisionName to conferenceName

        // Create field Team and attach to divisionName

        console.printLine("Enter the Team name:");
        String teamName = console.readLine();

        // Attach the teamName to Team

        console.printLine("Enter the General Manager name:");
        String generalManager = console.readLine();

        // Attach the generalManager to Team

        console.printLine("Enter the Head Coach name:");
        String headCoach = console.readLine();

        // Attach the headCoach to Team

        return false;
    }

    @Override
    public void loadTeam() {

    }
}
