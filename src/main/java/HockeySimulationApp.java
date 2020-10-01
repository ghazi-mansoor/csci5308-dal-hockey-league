import com.groupten.console.ConsoleInterface;
import com.groupten.json.JSONInterface;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HockeySimulationApp {

    public static void main(String[] args) throws IOException, ParseException {

        boolean quit_1 = true;
        ConsoleInterface console = Injector.injector().getConsoleObject();
        JSONInterface json = Injector.injector().getJSONObject();

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

                    json.loadJSONData();

//                    if(didImport){
//                        console.printLine("JSON import is successful");
//                        console.printLine("Proceeding to team creation. Please answer the below questions.");
//
//                        console.printLine("Enter the Confernce name:");
//                        String conferenceName = console.readLine();
//
//                        // Check if the conferenceName exist
//
//                        console.printLine("Enter the Division name:");
//                        String divisionName = console.readLine();
//
//                        // Check if the divisionName exist
//                        // Attach the divisionName to conferenceName
//
//                        // Create field Team and attach to divisionName
//
//                        console.printLine("Enter the Team name:");
//                        String teamName = console.readLine();
//
//                        // Attach the teamName to Team
//
//                        console.printLine("Enter the General Manager name:");
//                        String generalManager = console.readLine();
//
//                        // Attach the generalManager to Team
//
//                        console.printLine("Enter the Head Coach name:");
//                        String headCoach = console.readLine();
//
//                        // Attach the headCoach to Team
//
//                    }else{
//                        console.printLine("JSON import is not successful");
//                    }
                    break;
                case "n":
                    console.printLine("No");
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
