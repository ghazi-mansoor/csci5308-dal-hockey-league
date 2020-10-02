import com.groupten.console.ConsoleInterface;
import com.groupten.injector.Injector;
import com.groupten.json.JSONInterface;
import com.groupten.useraction.UserAction;
import com.groupten.useraction.UserActionInterface;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HockeySimulationApp {

    public static void main(String[] args) throws IOException, ParseException {

        boolean quit_1 = true;
        ConsoleInterface console = Injector.injector().getConsoleObject();
        JSONInterface json = Injector.injector().getJSONObject();
        UserActionInterface userAction = Injector.injector().getUserActionObject();

        console.printLine("\t\t\t\t\t\t\t\t##############################" +
                "\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###" +
                "\n\t\t\t\t\t\t\t\t##############################");

        do{
            console.printLine("\nDo you want to import JSON file? (y/n) (q to quit)");
            String choice = console.readLine().toLowerCase();

            switch(choice) {
                case "y":
                    console.printLine("Please enter the file path:");
                    String path = console.readLine();

                    path = "E:\\Project\\Dalhousie\\Advance_Software_Development\\csci5308\\src\\main\\resources\\testData.json";

                    boolean didImport = json.importJSONData(path);

                    if(didImport){
                        boolean isValid = json.validateJSONData();

                        if(isValid){
                            userAction.createTeam();
                        }else{
                            console.printLine("There are empty fields in JSON file.");
                        }

                    }else{
                        console.printLine("JSON import is not successful");
                    }

                    break;
                case "n":
                    userAction.loadTeam();
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
