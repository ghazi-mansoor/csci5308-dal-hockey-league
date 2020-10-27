package com.groupten.statemachine.simulation;

import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.statemachine.console.IConsole;

public class Simulation implements ISimulation {
    private IConsole console;

    @Override
    public boolean init(){
        return true;
    }
}
