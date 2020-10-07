package com.groupten.leagueobjectmodel;

public interface IPersistence {
    // The return type of int denotes the primary key of the saved record
    int persistLeague(League league);
    int persistConference(Conference conference);
    int persistDivision(Division division);
    int persistTeam(Team team);
    int persistPlayer(Player player);
}
