import com.console.Console;
import com.console.ConsoleInterface;

public class Injector {

    private static Injector injector = null;
    private ConsoleInterface consoleInterface;

    private Injector() {
        consoleInterface = new Console();
    }

    public static Injector injector(){
        if(injector == null) {
            injector = new Injector();
        }

        return injector;
    }

    public static void setInjector(Injector injector) {
        Injector.injector = injector;
    }

    public void setConsole(ConsoleInterface consoleInterface) {
        this.consoleInterface = consoleInterface;
    }

    public ConsoleInterface getConsole() {
        return consoleInterface;
    }

}
