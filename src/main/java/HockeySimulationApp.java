import com.groupten.statemachine.console.ConsoleInterface;
import com.groupten.statemachine.injector.Injector;
import com.groupten.statemachine.json.JSONInterface;
import com.groupten.statemachine.createteam.CreateTeamInterface;
import com.groupten.statemachine.loadteam.LoadTeamInterface;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HockeySimulationApp {

    public static void main(String[] args) throws IOException, ParseException {

        ConsoleInterface console = Injector.injector().getConsoleObject();
        JSONInterface json = Injector.injector().getJSONObject();
        CreateTeamInterface createTeam = Injector.injector().getCreateTeamObject();
        LoadTeamInterface loadTeam = Injector.injector().getLoadTeamObject();

        console.printLine("\t\t\t\t\t\t\t\t##############################\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###\n\t\t\t\t\t\t\t\t##############################");

        console.printLine("\nDo you want to import JSON file? (y/n)");
        String choice = console.readLine().toLowerCase();

        switch(choice) {
            case "y":
                console.printLine("Please enter the file path:");
                String path = console.readLine();

                path = "src/main/resources/testData.json";

                boolean didImport = json.importJSONData(path);

                if(didImport){
                    boolean isValid = json.validateJSONData();

                    if(isValid){
                        console.printLine("JSON data import is successful");

                        json.instantiateJSONData();

                        console.printLine("Proceeding to team creation. Please answer the below questions.");
                        createTeam.userPromptForNewTeam();
                        if(createTeam.validateUserInput()){
                            console.printLine("Checking the conference and division");
                            if(createTeam.ifExist()){
                                console.printLine("Ready to add the team");
                            }else{
                                console.printLine("Conference or Division does not exist");
                            }
                        }else{
                            console.printLine("Invalid Name. Please try again");
                        }
                    }else{
                        console.printLine("There are empty fields in JSON file.");
                    }
                    createTeam.instantiateNewTeam();
                    createTeam.persistAllData();
                }else{
                    console.printLine("JSON import is not successful");
                }
                break;
            case "n":
                loadTeam.userPromptForLoadingTeam();
                if(loadTeam.doesTeamExist()){
                    loadTeam.loadExistingTeam();
                    loadTeam.loadExistingLeague();
                }else{
                    console.printLine("Team does not exist");
                }
                break;
            default:
                console.printLine("Invalid Input. Please try again");
                break;
        }

    }
}
