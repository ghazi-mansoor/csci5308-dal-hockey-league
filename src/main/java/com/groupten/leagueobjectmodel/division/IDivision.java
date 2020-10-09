package com.groupten.leagueobjectmodel.division;

import com.groupten.leagueobjectmodel.team.Team;

public interface IDivision {
    boolean addTeamToDivision(Team team);
    boolean saveDivisionToDB();
    boolean doesTeamExistInMemory(String teamName);
    void loadTeamsFromDB();
}
