package com.groupten.leagueobjectmodel.league;

import com.groupten.jdbc.team.TeamInterface;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.player.Player;

import java.util.Map;

public interface ILeague {
    boolean addConferenceToLeague(Conference conference);
    boolean addFreeAgentToLeague(Player player);
    boolean saveLeagueToDB();
    boolean doEntitiesExistInMemory(String conferenceName, String divisionName);
    boolean addTeamToLeagueModel(String teamName, String generalManager, String headCoach, TeamInterface persistenceAPI);
    boolean doesContainConference(String conferenceName);
    String getLeagueName();
    Map<String, Conference> getConferences();
    Conference getConference(String conferenceName);
    boolean areNumberOfConferencesEven();
    void loadConferencesFromDB();

}
