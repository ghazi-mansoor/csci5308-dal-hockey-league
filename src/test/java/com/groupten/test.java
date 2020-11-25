package com.groupten;

import com.groupten.statemachine.jsonimport.JSONImport;
import com.groupten.statemachine.simulation.training.Training;
import com.groupten.statemachine.simulation.trophy.Trophy;
import org.junit.Test;

public class test {

    @Test
    public void doTest(){
        JSONImport json = new JSONImport();
        json.importJSONData("src/test/java/com/groupten/mocks/league.json");
        json.instantiateJSONData();

        Trophy trophy = new Trophy();
        Training training = new Training();
        training.subscribe(trophy);
        training.trainPlayers();
    }

}
