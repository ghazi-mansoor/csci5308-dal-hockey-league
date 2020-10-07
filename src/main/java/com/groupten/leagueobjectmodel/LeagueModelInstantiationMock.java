package com.groupten.leagueobjectmodel;

public class LeagueModelInstantiationMock {
    public static void main(String[] args) {
        IPersistence persistenceAPI = new PersistenceAPI();
        LeagueModel leagueModel = new LeagueModel(persistenceAPI);

        // Create League 1
        League league1 = new League("League 1", persistenceAPI);

        // Create Conference 1
        Conference conference1 = new Conference("L1-Conference 1", persistenceAPI);

        // Create Division 1 - Conference 1
        Division division1 = new Division("L1-C1-Division 1", persistenceAPI);

        // Create Team 1 - Division 1 - Conference 1
        Team team1 = new Team("L1-C1-D1-Team 1", "John Doe", "Bob Marley", persistenceAPI);

        // Create Players - Team 1 - Division 1 - Conference 1
        Player player1 = new Player("L1-C1-D1-T1-Player 1", "forward", false, persistenceAPI);
        Player player2 = new Player("L1-C1-D1-T1-Player 2", "goalie", false, persistenceAPI);
        team1.addPlayerToTeam(player1);
        team1.addPlayerToTeam(player2);

        // Create Team 2 - Division 1 - Conference 1
        Team team2 = new Team("L1-C1-D1-Team 2", "John Doe", "Bob Marley", persistenceAPI);

        // Create Players - Team 2 - Division 1 - Conference 1
        player1 = new Player("L1-C1-D1-T2-Player 1", "forward", false, persistenceAPI);
        player2 = new Player("L1-C1-D1-T2-Player 2", "goalie", false, persistenceAPI);
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
