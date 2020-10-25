package com.groupten.statemachine.simulation;

import com.groupten.injector.Injector;
import com.groupten.statemachine.console.IConsole;

public class Simulation implements ISimulation {

    private IConsole console;

    @Override
    public void init() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Beginning Simulation");
    }
}
