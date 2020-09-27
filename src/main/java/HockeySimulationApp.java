import com.console.ConsoleInterface;

public class HockeySimulationApp {

    public static void main(String[] args) {

        boolean quit_1 = true;
        ConsoleInterface console = Injector.injector().getConsole();

        console.printLine("\t\t\t\t\t\t\t\t##############################" +
                "\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###" +
                "\n\t\t\t\t\t\t\t\t##############################");

        do{
            console.printLine("\nDo you want to import JSON file? (y/n) (q to quit)");
            String choice = console.readLine().toLowerCase();

            switch(choice) {
                case "y":
                    console.printLine("Yes");
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
