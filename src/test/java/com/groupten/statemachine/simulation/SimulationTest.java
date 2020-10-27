package com.groupten.statemachine.simulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulationTest {

    @Test
    public void initTest(){
        Simulation simulation = new Simulation();
        assertTrue(simulation.init());
    }
}
