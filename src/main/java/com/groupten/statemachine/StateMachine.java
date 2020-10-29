package com.groupten.statemachine;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.statemachine.createteam.ICreateTeam;
import com.groupten.statemachine.jsonimport.IJSONImport;
import com.groupten.statemachine.loadteam.ILoadTeam;
import com.groupten.statemachine.simulation.ISimulation;

import java.io.IOException;

public class StateMachine {
    IConsole console = Injector.injector().getConsoleObject();
    IJSONImport json = Injector.injector().getJSONObject();
    ICreateTeam createTeam = Injector.injector().getCreateTeamObject();
    ILoadTeam loadTeam = Injector.injector().getLoadTeamObject();
    ISimulation simulation = Injector.injector().getSimulationObject();

    public void init() {
        console.printLine("\t\t\t\t\t\t\t\t##############################\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###\n\t\t\t\t\t\t\t\t##############################");
        console.printLine("\nDo you want to import JSON file? (y/n)");
        String choice = console.readLine().toLowerCase();
        if (choice.equals("y")) {
            importJson();
        } else if (choice.equals("n")) {
            loadTeam();
        } else {
            console.printLine("ERROR: Invalid Input.");
            continueOrExit();
            init();
        }
    }

    private void importJson() {
        console.printLine("Please enter the file path:");

        String path = console.readLine();
        try {
            json.importJSONData(path);
            console.printLine("SUCCESS: Reading JSON file.");
        } catch (IOException e) {
            console.printLine("ERROR: Invalid File Path.");
            continueOrExit();
            importJson();
        }

        if (json.isLeagueNameUnique()) {
            if (json.instantiateJSONData()) {
                console.printLine("SUCCESS: JSON Loaded.");
                createTeam();
            } else {
                console.printLine("ERROR: Invalid JSON Data.");
                continueOrExit();
                importJson();
            }
        } else {
            console.printLine("ERROR: League Already Exists.");
            continueOrExit();
            importJson();
        }
    }

    private void createTeam() {
        console.printLine("Proceeding to team creation. Please answer the below questions.");
        createTeam.userPromptForNewTeam();

        if (createTeam.validateUserInput()) {
            if (createTeam.ifConferenceAndDivisionExist()) {
                if (createTeam.selectTeamGeneralManager()) {
                    if (createTeam.selectTeamHeadCoach()) {
                        if (createTeam.selectTeamGoalies()) {
                            if (createTeam.selectTeamSkaters()) {
                                if (createTeam.instantiateNewTeam()) {
                                    console.printLine("SUCCESS: Team Created.");
                                    playerChoice();
                                } else {
                                    console.printLine("FAILURE: Some error occurred.");
                                }
                            } else {
                                console.printLine("ERROR: Invalid Input.");
                            }
                        } else {
                            console.printLine("ERROR: Invalid Input.");
                        }
                    } else {
                        console.printLine("ERROR: Invalid Input.");
                    }
                } else {
                    console.printLine("ERROR: Invalid Input.");
                }

            } else {
                console.printLine("ERROR: Conference or Division does not exist.");
            }
        } else {
            console.printLine("ERROR: Invalid Input.");
        }
        continueOrExit();
        createTeam();

    }

    private void loadTeam() {
        console.printLine("Proceeding to team load. Please answer the below questions.");
        loadTeam.userPromptForLoadingTeam();
        if (loadTeam.doesTeamExist()) {
            if (loadTeam.loadExistingLeague()) {
                console.printLine("SUCCESS: Team Selected.");
                playerChoice();
            }else{
                console.printLine("FAILURE: Some error occurred.");
            }
        }else{
            console.printLine("ERROR: Team does not exist.");
        }
        continueOrExit();
        loadTeam();
    }

    private void playerChoice() {
        console.printLine("\nHow many seasons do you want to simulate?");
        int numberOfSeasons = console.readInteger();
        simulate(numberOfSeasons);
    }

    private void simulate(int numberOfSeasons) {
        console.printLine("Simulating " + numberOfSeasons + " Seasons.");
        simulation.init(numberOfSeasons);
        end();
    }

    private void continueOrExit() {
        console.printLine("\nDo you want to Retry? (y/n)");
        String choice = console.readLine().toLowerCase();
        if(choice.equals("y")){
            //do nothing
        }else if(choice.equals("n")){
            end();
        }else{
            console.printLine("ERROR: Invalid Input.");
        }
    }

    private void end() {
        console.printLine("Good Bye!");
        System.exit(0);
    }
}
