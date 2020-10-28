import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.statemachine.simulation.initializeseason.InitializeSeason;

public class HelloWorld {
    public static void main(String[] args) {
        InitializeSeason initializeSeason = new InitializeSeason();
        Season season = initializeSeason.getSeason();

        season.generateRegularSchedule();
        System.out.println(season.randomDateBetween(season.getRegularSeasonStartsAt(), season.getRegularSeasonEndsAt()));
    }
}
