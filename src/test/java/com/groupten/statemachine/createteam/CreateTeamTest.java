package com.groupten.statemachine.createteam;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.statemachine.json.JSON;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CreateTeamTest {

    @Test
    public void validateUserInputTest(){
        CreateTeam createTeam = new CreateTeam();
        createTeam.setConferenceName("XYZ");
        createTeam.setDivisionName("ABC");
        createTeam.setTeamName("LMO");
        assertTrue(createTeam.validateUserInput());
    }

    @Test
    public void ifConferenceAndDivisionExistTest(){

        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League league = new League("Deep Test League");
        Conference conference = new Conference("Deep Test Conference");
        Division division = new Division("Deep Division Division");
        conference.addDivision(division);
        league.addConference(conference);
        leagueModel.addLeague(league);

        CreateTeam createTeam = new CreateTeam();
        createTeam.setConferenceName("Deep Test Conference");
        createTeam.setDivisionName("Deep Division Division");

        assertTrue(createTeam.ifConferenceAndDivisionExist());

    }

    @Test
    public void instantiateNewTeamTest(){
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League league = new League("Deep Test League");
        Conference conference = new Conference("Deep Test Conference");
        Division division = new Division("Deep Division Division");
        conference.addDivision(division);
        league.addConference(conference);
        leagueModel.addLeague(league);

        CreateTeam createTeam = new CreateTeam();
        createTeam.setConferenceName("Deep Test Conference");
        createTeam.setDivisionName("Deep Division Division");
        createTeam.setTeamName("Deep Test 3");

        createTeam.ifConferenceAndDivisionExist();

        assertTrue(createTeam.instantiateNewTeam());
    }
}
