package com.groupten.statemachine.simulation;

import com.groupten.injector.Injector;
import com.groupten.statemachine.console.ConsoleInterface;

public class Simulation implements SimulationInterface {

    private ConsoleInterface console;

    @Override
    public void beginSimulation() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Beginning Simulation");
    }

    @Override
    public void fakeState_1() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Fake State 1");
    }

    @Override
    public void fakeState_2() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Fake State 2");
    }

    @Override
    public void simulate() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Simulation in Progress");
    }

    @Override
    public void fakeState_3() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Fake State 3");
    }

    @Override
    public void fakeState_4() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Fake State 4");
    }

    @Override
    public void endSimulation() {
        console = Injector.injector().getConsoleObject();
        console.printLine("Ending Simulation");
    }
}
