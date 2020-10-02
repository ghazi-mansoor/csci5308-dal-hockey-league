import com.groupten.console.ConsoleInterface;
import com.groupten.injector.Injector;
import com.groupten.json.JSONInterface;
import com.groupten.createteam.CreateTeamInterface;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HockeySimulationApp {

    public static void main(String[] args) throws IOException, ParseException {

        boolean quit_1 = true;
        ConsoleInterface console = Injector.injector().getConsoleObject();
        JSONInterface json = Injector.injector().getJSONObject();
        CreateTeamInterface createTeam = Injector.injector().getCreateTeamObject();

        console.printLine("\t\t\t\t\t\t\t\t##############################\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###\n\t\t\t\t\t\t\t\t##############################");

        do{
            console.printLine("\nDo you want to import JSON file? (y/n) (q to quit)");
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
                            console.printLine("Proceeding to team creation. Please answer the below questions.");
                            createTeam.userPrompt();
                        }else{
                            console.printLine("There are empty fields in JSON file.");
                        }

                    }else{
                        console.printLine("JSON import is not successful");
                    }

                    break;
                case "n":
                    break;
                case "q":
                    quit_1 = false;
                    break;
                default:
                    console.printLine("Invalid Input. Please try again");
                    break;
            }

        }while (quit_1);

    }
}
