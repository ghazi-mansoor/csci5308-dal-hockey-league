package com.groupten.leagueobjectmodel.leaguemodel;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.List;
import java.util.Map;

public class LeagueModel implements ILeagueModel {
    private League currentLeague;

    @Override
    public boolean loadLeague(int leagueID) {
        // TODO: Load league, conferences, divisions, teams, and players from DB via league's ID
        return true;
    }

    @Override
    public boolean loadLeague(String teamName) {
        // TODO: Load league, conferences, divisions, teams, and players from DB via team's name
        return true;
    }

    @Override
    public boolean saveLeagueModel() {
        boolean leagueSaved = currentLeague.saveLeague();
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
            boolean conferenceSaved = conference.saveConference();

            if (conferenceSaved == false) {
                return false;
            } else {
                int conferenceID = conference.getConferenceID();
                Map <String, Division> divisions = conference.getDivisions();

                return saveDivisions(conferenceID, divisions);
            }
        }
        return true;
    }

    private boolean saveDivisions(int conferenceID, Map<String, Division> divisions) {
        for (Division division : divisions.values()) {
            division.setConferenceID(conferenceID);
            boolean divisionSaved = division.saveDivision();

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
            boolean teamSaved = team.saveTeam();

            if (teamSaved == false) {
                return false;
            } else {
                int teamID = team.getTeamID();
                List<Player> players = team.getPlayers();

                return savePlayers(teamID, players);
            }
        }
        return true;
    }

    private boolean savePlayers(int teamID, List<Player> players) {
        for (Player player : players) {
            player.setTeamID(teamID);
            boolean playerSaved = player.savePlayer();

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
}
