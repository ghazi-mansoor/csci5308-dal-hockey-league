import com.groupten.statemachine.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.statemachine.json.IJSON;
import com.groupten.statemachine.createteam.ICreateTeam;
import com.groupten.statemachine.loadteam.ILoadTeam;
import com.groupten.statemachine.simulation.ISimulation;

import java.io.IOException;

public class HockeySimulationApp {

    public static void main(String[] args) throws IOException {

        boolean startSimulation = false;
        IConsole console = Injector.injector().getConsoleObject();
        IJSON json = Injector.injector().getJSONObject();
        ICreateTeam createTeam = Injector.injector().getCreateTeamObject();
        ILoadTeam loadTeam = Injector.injector().getLoadTeamObject();
        ISimulation simulation = Injector.injector().getSimulationObject();

        console.printLine("\t\t\t\t\t\t\t\t##############################\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###\n\t\t\t\t\t\t\t\t##############################");

        console.printLine("\nDo you want to import JSON file? (y/n)");
        String choice = console.readLine().toLowerCase();

        switch(choice) {
            case "y":
                console.printLine("Please enter the file path:");
                String path = console.readLine();

                if(json.importJSONData(path)){
                    console.printLine("SUCCESS: Reading JSON file.\n");

                    if(json.isLeagueNameUnique()){

                        if(json.instantiateJSONData()){
                            console.printLine("Proceeding to team creation. Please answer the below questions.");
                            createTeam.userPromptForNewTeam();

                            if(createTeam.validateUserInput()){
                                if(createTeam.ifConferenceAndDivisionExist()){
                                    if(createTeam.selectTeamGeneralManager()){
                                        if(createTeam.selectTeamHeadCoach()){
                                            if(createTeam.selectTeamGoalies()){
                                                if(createTeam.selectTeamSkaters()){
                                                    console.printLine("SUCCESS: Ready to add the team.\n");
                                                    if(createTeam.instantiateNewTeam()){
                                                        console.printLine("SUCCESS: We can now proceed to simulation.");
                                                        startSimulation = true;
                                                    }else{
                                                        console.printLine("FAILURE: Could not create new team.");
                                                    }
                                                }else{
                                                    console.printLine("FAILURE: Invalid Input. Please try again.");
                                                }
                                            }else{
                                                console.printLine("FAILURE: Invalid Input. Please try again.");
                                            }
                                        }else{
                                            console.printLine("FAILURE: Invalid Input. Please try again.");
                                        }
                                    }else{
                                        console.printLine("FAILURE: Invalid Input. Please try again.");
                                    }
                                }else{
                                    console.printLine("FAILURE: Conference or Division does not exist.");
                                }
                            }else{
                                console.printLine("FAILURE: Issue in the input.");
                            }
                        }else{
                            console.printLine("FAILURE: Issue in JSON Data. Please try again.");
                        }
                    }else{
                        console.printLine("FAILURE: League already exist.");
                    }
                }else{
                    console.printLine("FAILURE: Reading JSON file.");
                }
                break;
            case "n":
                loadTeam.userPromptForLoadingTeam();
                if(loadTeam.doesTeamExist()){
                    console.printLine("\nSUCCESS: The team exist.");
                    if(loadTeam.loadExistingLeague()){
                        console.printLine("SUCCESS: Loading League Successful.");
                        console.printLine("SUCCESS: We can now proceed to simulation.");
                        startSimulation = true;
                    }else{
                        console.printLine("FAILURE: Loading league failed.");
                    }
                }else{
                    console.printLine("FAILURE: Team does not exist. Please try again");
                }
                break;
            default:
                console.printLine("Invalid Input. Please try again");
                break;
        }

        if(startSimulation){
            console.printLine("\nHow many seasons do you want to simulate?");
            int numberOfSeasons = console.readInteger();

            for(int i = 1; i <= numberOfSeasons; i++){
                console.printLine("Season " + i);
                console.printLine("\n");
            }
        }

    }
}
