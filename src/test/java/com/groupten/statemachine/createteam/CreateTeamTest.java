package com.groupten.statemachine.createteam;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.statemachine.json.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        Player player;
        List<Player> listOfPlayer = new ArrayList<>();
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
        createTeam.setGeneralManager(new GeneralManager("First General Manager"));
        createTeam.setHeadCoach(new Coach("First Coach", 0.5, 0.5, 0.5, 0.5));

        player = new Player("First Player", "goalie", false, 20, 5, 5, 5, 5);
        listOfPlayer.add(player);
        player = new Player("Second Player", "goalie", false, 20, 5, 5, 5, 5);
        listOfPlayer.add(player);
        player = new Player("Third Player", "forward", false, 20, 5, 5, 5, 5);
        listOfPlayer.add(player);
        player = new Player("Forth Player", "forward", false, 20, 5, 5, 5, 5);
        listOfPlayer.add(player);
        player = new Player("Fifth Player", "defense", false, 20, 5, 5, 5, 5);
        listOfPlayer.add(player);

        createTeam.setFreeAgents(listOfPlayer);

        createTeam.ifConferenceAndDivisionExist();

        assertTrue(createTeam.instantiateNewTeam());
    }
}
