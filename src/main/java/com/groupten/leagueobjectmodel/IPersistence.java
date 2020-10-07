package com.groupten.leagueobjectmodel;

public interface IPersistence {
    int persistLeague(League league);
    int persistConference(Conference conference);
    int persistDivision(Division division);
    int persistTeam(Team team);
    int persistPlayer(Player player);
}
