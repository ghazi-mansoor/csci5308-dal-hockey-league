package com.groupten.leagueobjectmodel.league;

import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.player.Player;

public interface ILeague {
    boolean addConferenceToLeague(Conference conference);
    boolean addFreeAgentToLeague(Player player);
    boolean saveLeagueToDB();
    boolean doEntitiesExistInMemory(String conferenceName, String divisionName);
    boolean addTeamToLeagueModel(String teamName, String generalManager, String headCoach, TeamInterface persistenceAPI);
    boolean doesContainConference(String conferenceName);
    String getLeagueName();
    Conference getConference(String conferenceName);
    boolean areNumberOfConferencesEven();
    void loadConferencesFromDB();
}
