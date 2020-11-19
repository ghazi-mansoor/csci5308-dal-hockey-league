package com.groupten.statemachinenew.states;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.statemachinenew.StateMachine;

public class ReadyState implements IState {
    StateMachine stateMachine;

    public ReadyState(StateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    @Override
    public void doAction() {
        IConsole console = Injector.instance().getConsoleObject();
        console.printLine("\t\t\t\t\t\t\t\t##############################\n\t\t\t\t\t\t\t\t### Hockey Game Simulation ###\n\t\t\t\t\t\t\t\t##############################");
        console.printLine("\nDo you want to import JSON file? (y/n)");
        String choice = console.readLine().toLowerCase();
        if (choice.equals("y")) {
            stateMachine.setState(new ImportState(stateMachine));
            stateMachine.doAction();
        } else if (choice.equals("n")) {
            stateMachine.setState(new LoadTeamState(stateMachine));
            stateMachine.doAction();
        } else {
            console.printLine("ERROR: Invalid Input.");
            stateMachine.retry();
        }
    }
}
