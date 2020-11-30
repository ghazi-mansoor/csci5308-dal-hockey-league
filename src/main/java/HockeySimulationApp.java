import com.groupten.injector.Injector;
import com.groupten.statemachine.StateMachine;
import com.groupten.statemachine.simulation.training.ITraining;
import com.groupten.statemachine.simulation.training.ITrainingObserver;
import com.groupten.statemachine.simulation.trophy.ITrophy;

public class HockeySimulationApp {

    public static void main(String[] args){

        ITraining training = Injector.instance().getTrainingObject();
        ITrophy trophy = Injector.instance().getTrophyObject();

        training.subscribe((ITrainingObserver) trophy);

        StateMachine stateMachine = new StateMachine();
        stateMachine.init();
    }
}
