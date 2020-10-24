package com.groupten.leagueobjectmodel.generalmanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneralManagerTest {
    @Test
    public void isManagerNameValidTest() {
        String managerName = "First Manager";
        assertTrue(GeneralManager.isManagerNameValid(managerName));
        managerName = "";
        assertFalse(GeneralManager.isManagerNameValid(managerName));
        managerName = " ";
        assertFalse(GeneralManager.isManagerNameValid(managerName));
        managerName = "Null";
        assertFalse(GeneralManager.isManagerNameValid(managerName));
    }

    @Test
    public void getManagerIDTest() {
        GeneralManager generalManager = new GeneralManager(1, "First General Manager", true);
        assertEquals(1, generalManager.getManagerID());
    }

    @Test
    public void setManagerIDTest() {
        GeneralManager generalManager = new GeneralManager("First General Manager", true);
        generalManager.setManagerID(1);
        assertEquals(1, generalManager.getManagerID());
    }

    @Test
    public void getManagerNameTest() {
        GeneralManager generalManager = new GeneralManager(1, "First General Manager", true);
        assertEquals("First General Manager", generalManager.getManagerName());
    }

    @Test
    public void setManagerNameTest() {
        GeneralManager generalManager = new GeneralManager("First General Manager", true);
        generalManager.setManagerName("Updated First General Manager");
        assertEquals("Updated First General Manager", generalManager.getManagerName());
    }
}
