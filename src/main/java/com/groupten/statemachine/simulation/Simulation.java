package com.groupten.statemachine.simulation;

import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.statemachine.console.IConsole;

import java.util.Calendar;

public class Simulation implements ISimulation {
    private static final Season season = new Season();
    private IConsole console;

    @Override
    public void init(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.YEAR, Calendar.SEPTEMBER,30);

        season.setCurrentDate(currentDate);
        season.advanceTime();
    }
}
