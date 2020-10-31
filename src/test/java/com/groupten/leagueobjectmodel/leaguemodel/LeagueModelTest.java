package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.conference.ConferenceTest;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.division.DivisionTest;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeagueModelTest {
    @Test
    public void setCurrentLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League("League 1");
        leagueModel.setCurrentLeague(league);
        assertEquals(league, leagueModel.getCurrentLeague());
        league = new League(" ");
        assertFalse(leagueModel.setCurrentLeague(league));
    }

    @Test
    public void loadLeagueTest() {
        LeagueModel leagueModel = new LeagueModel();
        assertTrue(leagueModel.loadLeague("Team 1"));
        assertTrue(leagueModel.loadLeague(1));
    }

    @Test
    public void saveLeagueModelTest() {
        LeagueModel leagueModel = new LeagueModel();
        League league = new League(1, "League 1");
        leagueModel.setCurrentLeague(league);

        Conference conference = new Conference(1, "Conference 1");
        league.addConference(conference);

        Division division = new Division(1, "Division 1");
        conference.addDivision(division);

        Team team = new Team(1, "Team 1");
        division.addTeam(team);

        Player player = new Player(1, "Player 1", "goalie", 20, 5, 5, 5, 5);
        team.addPlayer(player);
        player = new Player(2, "Player 2", "goalie", 20, 5, 5, 5, 5);
        team.addPlayer(player);

        assertTrue(leagueModel.saveLeagueModel());
    }

}
