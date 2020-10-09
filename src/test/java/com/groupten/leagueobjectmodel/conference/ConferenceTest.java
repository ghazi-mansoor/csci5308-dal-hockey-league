package com.groupten.leagueobjectmodel.conference;

import com.groupten.leagueobjectmodel.division.Division;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConferenceTest {

    @Test
    public void addDivisionToConferenceTest() {
        Conference conference = new Conference("L1-Conference 3");
        Division division = new Division("L1-C3 Division 1");

        assertTrue(conference.addDivisionToConference(division));
    }

    @Test
    public void doesContainDivsionTest() {
        Conference conference = new Conference("L1-Conference 3");
        Division division = new Division("L1-C3 Division 1");
        conference.addDivisionToConference(division);

        assertTrue(conference.doesContainDivision("L1-C3 Division 1"));
        assertFalse(conference.doesContainDivision("L1-C3 Division 2"));
    }

    @Test
    public void areNumberOfDivisionsEvenTest() {
        Conference conference = new Conference("L1-Conference 3");
        Division division1 = new Division("L1-C3 Division 1");
        Division division2 = new Division("L1-C3 Division 2");
        conference.addDivisionToConference(division1);
        conference.addDivisionToConference(division2);

        assertTrue(conference.areNumberOfDivisionsEven());
    }

}
