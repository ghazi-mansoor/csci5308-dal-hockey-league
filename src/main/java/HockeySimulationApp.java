import com.groupten.statemachine.console.ConsoleInterface;
import com.groupten.injector.Injector;
import com.groupten.statemachine.json.JSONInterface;
import com.groupten.statemachine.createteam.CreateTeamInterface;
import com.groupten.statemachine.loadteam.LoadTeamInterface;
import com.groupten.statemachine.simulation.SimulationInterface;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HockeySimulationApp {

    public static void main(String[] args) throws IOException, ParseException {

        ConsoleInterface console = Injector.injector().getConsoleObject();
        JSONInterface json = Injector.injector().getJSONObject();
        CreateTeamInterface createTeam = Injector.injector().getCreateTeamObject();
        LoadTeamInterface loadTeam = Injector.injector().getLoadTeamObject();
        SimulationInterface simulation = Injector.injector().getSimulationObject();

        console.printLine("\t\t\t\t\t\t\t\t##############################\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###\n\t\t\t\t\t\t\t\t##############################");

        console.printLine("\nDo you want to import JSON file? (y/n)");
        String choice = console.readLine().toLowerCase();

        switch(choice) {
            case "y":
                console.printLine("Please enter the file path:");
                String path = console.readLine();

                if(json.importJSONData(path)){
                    console.printLine("SUCCESS: Reading JSON file.");

                    if(json.isLeagueNameUnique()){

                        if(json.instantiateJSONData()){
                            console.printLine("Proceeding to team creation. Please answer the below questions.");
                            createTeam.userPromptForNewTeam();

                            if(createTeam.validateUserInput()){
                                if(createTeam.ifExist()){
                                    console.printLine("SUCCESS: Ready to add the team.");

                                    if(createTeam.instantiateNewTeam()){
                                        if(createTeam.persistLeagueModel()){
                                            console.printLine("SUCCESS: We can now proceed to simulation.");
                                        }else{
                                            console.printLine("FAILURE: Could not save the data.");
                                        }
                                    }else{
                                        console.printLine("FAILURE: Could not create new team.");
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
                    console.printLine("FAILURE: Reading JSON file");
                }
                break;
            case "n":
                loadTeam.userPromptForLoadingTeam();
                if(loadTeam.doesTeamExist()){
                    console.printLine("SUCCESS");
                    loadTeam.loadExistingLeague();
                }else{
                    console.printLine("FAILURE: Team does not exist.");
                }
                break;
            default:
                console.printLine("Invalid Input. Please try again");
                break;
        }

        console.printLine("How many seasons do you want to simulate");
        int numberOfSeasons = console.readInteger();

        for(int i = 1; i <= numberOfSeasons; i++){
            console.printLine("Season " + i);
            simulation.beginSimulation();
            simulation.fakeState_1();
            simulation.fakeState_2();
            simulation.simulate();
            simulation.fakeState_3();
            simulation.fakeState_4();
            simulation.endSimulation();
            console.printLine("\n");
        }

    }
}
