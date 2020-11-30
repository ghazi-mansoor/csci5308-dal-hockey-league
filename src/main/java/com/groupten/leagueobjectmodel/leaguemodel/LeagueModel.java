package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.persistence.m1DB.dao.ILeagueDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueModel implements ILeagueModel {
    private League currentLeague;

    @Override
    public boolean loadLeagueModel(int leagueID) {
        ILeagueDAO leagueDAO = Injector.instance().getLeagueDatabaseObject();
        String leagueId = String.valueOf(leagueID);
        List<HashMap<String, Object>> leaguesMap = leagueDAO.getLeagues("leagueId", leagueId);
        HashMap<String, Object> leagueMap = leaguesMap.get(0);

        int leagueIDPrimaryKey = (int) leagueMap.get("leagueId");
        String leagueName = (String) leagueMap.get("leagueName");

        currentLeague = new League(leagueIDPrimaryKey, leagueName);

        return (currentLeague.getLeagueID() == leagueID);
    }

    @Override
    public boolean saveLeagueModel() {
        boolean leagueSaved = currentLeague.save();
        if (leagueSaved) {
            int leagueID = currentLeague.getLeagueID();
            Map<String, Conference> conferences = currentLeague.getConferences();

            return saveConferences(leagueID, conferences);
        } else {
            return false;
        }
    }

    private boolean saveConferences(int leagueID, Map<String, Conference> conferences) {
        for (Conference conference : conferences.values()) {
            conference.setLeagueID(leagueID);
            boolean conferenceSaved = conference.save();

            if (conferenceSaved == false) {
                return false;
            } else {
                int conferenceID = conference.getConferenceID();
                Map<String, Division> divisions = conference.getDivisions();

                return saveDivisions(conferenceID, divisions);
            }
        }
        return true;
    }

    private boolean saveDivisions(int conferenceID, Map<String, Division> divisions) {
        for (Division division : divisions.values()) {
            division.setConferenceID(conferenceID);
            boolean divisionSaved = division.save();

            if (divisionSaved == false) {
                return false;
            } else {
                int divisionID = division.getDivisionID();
                Map<String, Team> teams = division.getTeams();

                return saveTeams(divisionID, teams);
            }
        }
        return true;
    }

    private boolean saveTeams(int divisionID, Map<String, Team> teams) {
        for (Team team : teams.values()) {
            team.setDivisionID(divisionID);
            boolean teamSaved = team.save();

            if (teamSaved == false) {
                return false;
            } else {
                int teamID = team.getTeamID();
                List<Player> players = team.getActivePlayers();
                boolean playersSaved = savePlayers(teamID, players);

                if (playersSaved) {
                    for (Player player : players) {
                        team.persistPlayerWithTeam(player);
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean savePlayers(int teamID, List<Player> players) {
        for (Player player : players) {
            player.setTeamID(teamID);
            boolean playerSaved = player.save();

            if (playerSaved == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public League getCurrentLeague() {
        return currentLeague;
    }

    @Override
    public boolean setCurrentLeague(League currentLeague) {
        if (League.isLeagueNameValid(currentLeague.getLeagueName())) {
            this.currentLeague = currentLeague;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getTotalNumberOfTeams() {
        int teamCount = 0;
        for (Conference conference : currentLeague.getConferences().values()) {
            for (Division division : conference.getDivisions().values()) {
                teamCount += division.getTeams().size();
            }
        }

        return teamCount;
    }

    @Override
    public void addExcessPlayersToFreeAgentsList(List<Player> excessPlayers) {
        for (Player player : excessPlayers) {
            this.currentLeague.addFreeAgent(player);
        }
    }

}
