package com.groupten.statemachine.createteam;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.LeagueModel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CreateTeamTest {

    @Test
    public void validateUserInputTest(){
        CreateTeam createTeam = new CreateTeam();
        createTeam.setConferenceName("XYZ");
        createTeam.setDivisionName("ABC");
        createTeam.setGeneralManager("PQR");
        createTeam.setHeadCoach("DEF");
        createTeam.setTeamName("LMO");
        assertTrue(createTeam.validateUserInput());
    }

    @Test
    public void ifConferenceAndDivisionExistTest(){

        LeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League league = new League("Deep Test League");
        Conference conference = new Conference("Deep Test Conference");
        Division division = new Division("Deep Division Division");
        conference.addDivisionToConference(division);
        league.addConferenceToLeague(conference);
        leagueModel.addLeagueToModel(league);

        CreateTeam createTeam = new CreateTeam();
        createTeam.setConferenceName("Deep Test Conference");
        createTeam.setDivisionName("Deep Division Division");

        assertTrue(createTeam.ifConferenceAndDivisionExist());

    }

//    @Test
//    public void instantiateNewTeamTest(){
//        LeagueModel leagueModel = new LeagueModel();
//        League league = new League("Deep Test League");
//        Conference conference = new Conference("Deep Test Conference");
//        Division division = new Division("Deep Division Conference");
//        conference.addDivisionToConference(division);
//        league.addConferenceToLeague(conference);
//        leagueModel.addLeagueToModel(league);
//
//        CreateTeam createTeam = new CreateTeam();
//        createTeam.setConferenceName("Deep Test Conference");
//        createTeam.setDivisionName("Deep Division Conference");
//        createTeam.setGeneralManager("Deep Test 1");
//        createTeam.setHeadCoach("Deep Test 2");
//        createTeam.setTeamName("Deep Test 3");
//
//        assertTrue(createTeam.instantiateNewTeam());
//    }

}
