package com.groupten.statemachine.simulation;

import com.groupten.IO.console.IConsole;

public class Simulation implements ISimulation {
    private IConsole console;

    @Override
    public boolean init(){
        return true;
    }
}
