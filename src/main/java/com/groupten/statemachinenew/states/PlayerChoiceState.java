package com.groupten.statemachinenew.states;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.statemachinenew.StateMachine;

public class PlayerChoiceState implements IState {
    StateMachine stateMachine;

    public PlayerChoiceState(StateMachine stateMachine){
        this.stateMachine = stateMachine;
    }

    @Override
    public void doAction() {
        IConsole console = Injector.instance().getConsoleObject();

        console.printLine("\nHow many seasons do you want to simulate?");
        int numberOfSeasons = console.readInteger();

        //ToDo:: Simulation states
    }
}
