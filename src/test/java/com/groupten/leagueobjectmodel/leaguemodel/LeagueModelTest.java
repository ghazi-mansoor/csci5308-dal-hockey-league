package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.conference.ConferenceTest;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.division.DivisionTest;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.persistence.dao.*;
import com.groupten.persistence.dao.database.*;
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
        ILeagueDAO leagueDAO = new LeagueDAO();

        League league = new League("League 1", leagueDAO);
        GameConfig.Aging agingConfig = new GameConfig.Aging(35, 50);
        league.setAgingConfig(agingConfig);
        GameConfig.GameResolver gameResolverConfig = new GameConfig.GameResolver(0.1);
        league.setGameResolverConfig(gameResolverConfig);
        GameConfig.Injuries injuriesConfig = new GameConfig.Injuries(0.05, 1, 260);
        league.setInjuriesConfig(injuriesConfig);
        GameConfig.Training trainingConfig = new GameConfig.Training(100);
        league.setTrainingConfig(trainingConfig);
        GameConfig.Trading tradingConfig = new GameConfig.Trading(8, 0.05, 2, 0.05);
        league.setTradingConfig(tradingConfig);

        leagueModel.setCurrentLeague(league);

        IConferenceDAO conferenceDAO = new ConferenceDAO();
        Conference conference = new Conference("Conference 1", conferenceDAO);
        league.addConference(conference);

        IDivisionDAO divisionDAO = new DivisionDAO();
        Division division = new Division("Division 1", divisionDAO);
        conference.addDivision(division);

        ITeamDAO teamDAO = new TeamDAO();
        Team team = new Team("Team 1", teamDAO);
        division.addTeam(team);

        IPlayerDAO playerDAO = new PlayerDAO();
        Player player = new Player("Player 1", "goalie", 20.0, 5.0, 5.0, 5.0, 5.0, playerDAO);
        team.addPlayer(player);
        player = new Player("Player 2", "goalie", 20.0, 5.0, 5.0, 5.0, 5.0, playerDAO);
        team.addPlayer(player);

        assertTrue(leagueModel.saveLeagueModel());
    }

}
