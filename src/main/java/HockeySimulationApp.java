import com.groupten.statemachine.StateMachine;

import java.io.IOException;

public class HockeySimulationApp {

    public static void main(String[] args) throws IOException {
        StateMachine stateMachine = new StateMachine();
        stateMachine.init();
    }
}
