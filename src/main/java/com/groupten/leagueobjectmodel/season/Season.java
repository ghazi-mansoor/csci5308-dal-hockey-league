package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Season {
    private Calendar currentDate;
    private Map<Integer,TeamStanding> teamStandings;

    public Season(){
        currentDate = Calendar.getInstance();
        teamStandings = new HashMap<Integer, TeamStanding>();
    }

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Calendar currentDate) {
        this.currentDate = currentDate;
    }

    public Map<Integer,TeamStanding> getTeamStandings() {
        return teamStandings;
    }

    public void setTeamStandings(Map<Integer,TeamStanding> teamStandings) {
        this.teamStandings = teamStandings;
    }

    public void advanceTime(){
        currentDate.add(Calendar.DATE,1);
    }

    public void recordWin(int teamId){
        TeamStanding teamStanding = teamStandings.get(teamId);
        int points = teamStanding.getPoints();
        teamStanding.setPoints(points + 2);
        updateRanks();
    }

    private void updateRanks(){
        TreeMap<Integer, TeamStanding> sortedTeamStandings = new TreeMap<>(teamStandings);

        for(int i=0;i< sortedTeamStandings.size(); i++){
            TeamStanding teamStanding = sortedTeamStandings.get(i);
            teamStanding.setLeagueRank(i+1);
        }

        List<Integer> conferenceTeamIds = new ArrayList<>();
        //ToDo Populate conferenceTeamIds
        Map<Integer,TeamStanding> conferenceTeamStandings = conferenceTeamIds.stream()
                .filter(teamStandings::containsKey)
                .collect(Collectors.toMap(Function.identity(), teamStandings::get));

        TreeMap<Integer, TeamStanding> sortedConferenceTeamStandings = new TreeMap<>(teamStandings);

        for(int i=0;i< sortedConferenceTeamStandings.size(); i++){
            TeamStanding teamStanding = sortedConferenceTeamStandings.get(i);
            teamStanding.setLeagueRank(i+1);
        }

        List<Integer> divisionTeamIds = new ArrayList<>();
        //ToDo Populate divisionTeamIds
        Map<Integer,TeamStanding> divisionTeamStandings = divisionTeamIds.stream()
                .filter(teamStandings::containsKey)
                .collect(Collectors.toMap(Function.identity(), teamStandings::get));

        TreeMap<Integer, TeamStanding> sortedDivisionTeamStandings = new TreeMap<>(teamStandings);

        for(int i=0;i< sortedDivisionTeamStandings.size(); i++){
            TeamStanding teamStanding = sortedDivisionTeamStandings.get(i);
            teamStanding.setLeagueRank(i+1);
        }
    }
}
