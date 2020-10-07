package com.groupten.leagueobjectmodel;

import com.groupten.jdbc.conference.ConferenceInterface;
import com.groupten.jdbc.division.DivisionInterface;
import com.groupten.jdbc.league.LeagueInterface;
import com.groupten.jdbc.player.PlayerInterface;
import com.groupten.jdbc.team.TeamInterface;

public class LeagueModelInstantiationMock {
    public static void main(String[] args) {
        LeagueInterface leaguePersistenceAPI = new com.groupten.jdbc.league.League();
        ConferenceInterface conferencePersistenceAPI = new com.groupten.jdbc.conference.Conference();
        DivisionInterface divisionPersistenceAPI = new com.groupten.jdbc.division.Division();
        TeamInterface teamPersistenceAPI = new com.groupten.jdbc.team.Team();
        PlayerInterface playerPersistenceAPI = new com.groupten.jdbc.player.Player();

        LeagueModel leagueModel = new LeagueModel();

        // Create League 1
        League league1 = new League("League 1", leaguePersistenceAPI);

        // Create Conference 1
        Conference conference1 = new Conference("L1-Conference 1", conferencePersistenceAPI);

        // Create Division 1 - Conference 1
        Division division1 = new Division("L1-C1-Division 1", divisionPersistenceAPI);

        // Create Team 1 - Division 1 - Conference 1
        Team team1 = new Team("L1-C1-D1-Team 1", "John Doe", "Bob Marley", teamPersistenceAPI);

        // Create Players - Team 1 - Division 1 - Conference 1
        Player player1 = new Player("L1-C1-D1-T1-Player 1", "forward", false, playerPersistenceAPI, teamPersistenceAPI);
        Player player2 = new Player("L1-C1-D1-T1-Player 2", "goalie", false, playerPersistenceAPI, teamPersistenceAPI);
        team1.addPlayerToTeam(player1);
        team1.addPlayerToTeam(player2);

        // Create Team 2 - Division 1 - Conference 1
        Team team2 = new Team("L1-C1-D1-Team 2", "John Doe", "Bob Marley", teamPersistenceAPI);

        // Create Players - Team 2 - Division 1 - Conference 1
        player1 = new Player("L1-C1-D1-T2-Player 1", "forward", false, playerPersistenceAPI, teamPersistenceAPI);
        player2 = new Player("L1-C1-D1-T2-Player 2", "goalie", false, playerPersistenceAPI, teamPersistenceAPI);
        team2.addPlayerToTeam(player1);
        team2.addPlayerToTeam(player2);

        // Add Teams 1 and 2 to Division 1 - Conference 1 - League 1
        division1.addTeamToDivision(team1);
        division1.addTeamToDivision(team2);

        // Add Divisions to Conference 1 - League 1
        conference1.addDivisionToConference(division1);

        // Add Conference 1 to League 1
        league1.addConferenceToLeague(conference1);

        // Add League 1 to League Model
        leagueModel.addLeagueToModel(league1);

        // Recursively save league object model to DB
        leagueModel.saveLeagueModelToDB();

    }
}
