package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamindex.TeamIndex;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.*;
import java.util.stream.Collectors;

public class Season {
    private League league = null;
    private Date currentDate;
    private Date regularSeasonStartsAt;
    private Date regularSeasonEndsAt;
    private Date tradeEndsAt;
    private Date playoffStartsAt;
    private Date playoffEndsBy;
    private Map<String,TeamStanding> teamStandings  = new HashMap<>();
    private List<Schedule> regularSchedules  = new ArrayList<>();
    private List<TeamIndex> teamIndices = null;

    public Season(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        this.initDates(year);
    }

    public Season(int year){
        this.initDates(year);
    }

    public League getLeague() {
        return league;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public Date getRegularSeasonStartsAt() {
        return regularSeasonStartsAt;
    }

    public Date getRegularSeasonEndsAt() {
        return regularSeasonEndsAt;
    }

    public Date getTradeEndsAt() {
        return tradeEndsAt;
    }

    public Date getPlayoffStartsAt() {
        return playoffStartsAt;
    }

    public Date getPlayoffEndsBy() {
        return playoffEndsBy;
    }

    public List<Schedule> getRegularSchedules() {
        return regularSchedules;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public boolean addTeamStanding(String teamName, TeamStanding teamStanding) {
        if(teamStandings.containsKey(teamName)){
            return false;
        }else{
            teamStandings.put(teamName,teamStanding);
            return true;
        }
    }

    public Map<String, TeamStanding> getTeamStandings() {
        return teamStandings;
    }

    public void advanceTime(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.currentDate);
        cal.add(Calendar.DATE,1);
        this.currentDate = cal.getTime();
    }

    public void recordWin(String teamName){
        TeamStanding teamStanding = teamStandings.get(teamName);
        int points = teamStanding.getPoints();
        teamStanding.setPoints(points + 2);
        updateRanks();
    }

    private void generateTeamIndices(){
        Map<String, Conference> conferences = league.getConferences();
        conferences.forEach((conferenceName,conference) ->{
            Map<String, Division> divisions = conference.getDivisions();
            divisions.forEach((divisionName, division) -> {
                Map<String, Team> teams = division.getTeams();
                teams.forEach((teamName, team) -> {
                    TeamIndex teamIndex = new TeamIndex();
                    teamIndex.setLeagueName(league.getLeagueName());
                    teamIndex.setConferenceName(conference.getConferenceName());
                    teamIndex.setDivisionName(division.getDivisionName());
                    teamIndex.setTeamName(team.getTeamName());
                    teamIndices.add(teamIndex);
                });
            });
        });
    }

    public void generateRegularSchedule(){
        if(teamIndices == null){
            generateTeamIndices();
        }

        teamIndices.forEach(teamIndex ->{
            //Same Division
            ArrayList<TeamIndex> sameDivisionTeams = (ArrayList<TeamIndex>) teamIndices.stream()
                    .filter(tI -> tI.getDivisionName().equals(teamIndex.getDivisionName()))
                    .collect(Collectors.toList());
            sameDivisionTeams.forEach(sameDivisionTeam -> {
                if(!teamIndex.getTeamName().equals(sameDivisionTeam.getTeamName())){
                    for(int i =0; i<4; i++){
                        Schedule schedule = new Schedule();
                        schedule.addTeamName(teamIndex.getTeamName());
                        schedule.addTeamName(sameDivisionTeam.getTeamName());
                        regularSchedules.add(schedule);
                    }
                }
            });
            //Same Conference Different Division
            ArrayList<TeamIndex> sameConferenceTeams = (ArrayList<TeamIndex>) teamIndices.stream()
                    .filter(tI -> !tI.getDivisionName().equals(teamIndex.getDivisionName()) && tI.getConferenceName().equals(teamIndex.getConferenceName()))
                    .collect(Collectors.toList());
            sameConferenceTeams.forEach(sameConferenceTeam -> {
                if(!teamIndex.getTeamName().equals(sameConferenceTeam.getTeamName())){
                    for(int i =0; i<3; i++) {
                        Schedule schedule = new Schedule();
                        schedule.addTeamName(teamIndex.getTeamName());
                        schedule.addTeamName(sameConferenceTeam.getTeamName());
                        regularSchedules.add(schedule);
                    }
                }
            });
            //Same League Different Conference
            ArrayList<TeamIndex> sameLeagueTeams = (ArrayList<TeamIndex>) teamIndices.stream()
                    .filter(tI -> !tI.getConferenceName().equals(teamIndex.getConferenceName()))
                    .collect(Collectors.toList());
            sameLeagueTeams.forEach(sameLeagueTeam -> {
                if(!teamIndex.getTeamName().equals(sameLeagueTeam.getTeamName())){
                    for(int i =0; i<2; i++) {
                        Schedule schedule = new Schedule();
                        schedule.addTeamName(teamIndex.getTeamName());
                        schedule.addTeamName(sameLeagueTeam.getTeamName());
                        regularSchedules.add(schedule);
                    }
                }
            });
        });
    };

    private void updateRanks(){
        if(teamIndices == null){
            generateTeamIndices();
        }

        HashSet<String> conferences = new HashSet<>();
        HashSet<String> divisions = new HashSet<>();
        teamIndices.forEach(teamIndex ->{
            conferences.add(teamIndex.getConferenceName());
            divisions.add(teamIndex.getDivisionName());
        });

        conferences.forEach(conference -> {
            List<String> conferenceTeamNames = new ArrayList<>();

            teamIndices.forEach(teamIndex -> {
                if(teamIndex.getConferenceName().equals(conference)){
                    conferenceTeamNames.add(teamIndex.getTeamName());
                }
            });
            Map<String,TeamStanding> conferenceTeamStandings = teamStandings.entrySet().stream()
                    .filter(map -> conferenceTeamNames.contains(map.getKey() ))
                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

            TreeMap<String, TeamStanding> sortedConferenceTeamStandings = new TreeMap<>(teamStandings);

            Iterator iterator = sortedConferenceTeamStandings.entrySet().iterator();
            int i = 1;
            while(iterator.hasNext()){
                Map.Entry me = (Map.Entry) iterator.next();
                TeamStanding teamStanding = (TeamStanding) me.getValue();
                teamStanding.setConferenceRank(i);
                i++;
            }
        });

        divisions.forEach(division -> {
            List<String> divisionTeamNames = new ArrayList<>();

            teamIndices.forEach(teamIndex -> {
                if(teamIndex.getDivisionName().equals(division)){
                    divisionTeamNames.add(teamIndex.getTeamName());
                }
            });
            Map<String,TeamStanding> divisionTeamStandings = teamStandings.entrySet().stream()
                    .filter(map -> divisionTeamNames.contains(map.getKey() ))
                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

            TreeMap<String, TeamStanding> sortedDivisionTeamStandings = new TreeMap<>(teamStandings);

            Iterator iterator = sortedDivisionTeamStandings.entrySet().iterator();
            int i = 1;
            while(iterator.hasNext()){
                Map.Entry me = (Map.Entry) iterator.next();
                TeamStanding teamStanding = (TeamStanding) me.getValue();
                teamStanding.setDivisionRank(i);
                i++;
            }
        });

        TreeMap<String, TeamStanding> sortedTeamStandings = new TreeMap<>(teamStandings);

        Iterator iterator = sortedTeamStandings.entrySet().iterator();
        int i = 1;
        while(iterator.hasNext()){
            Map.Entry me = (Map.Entry) iterator.next();
            TeamStanding teamStanding = (TeamStanding) me.getValue();
            teamStanding.setLeagueRank(i);
            i++;
        }
    }

    private void initDates(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        cal.set(Calendar.DATE, 30);
        this.currentDate = cal.getTime();

        cal.set(Calendar.MONTH, Calendar.OCTOBER);
        cal.set(Calendar.DATE, 1);
        this.regularSeasonStartsAt = cal.getTime();

        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 4);
        this.tradeEndsAt = lastDayOfMonth(cal.getTime(),Calendar.MONDAY);

        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 2);
        this.regularSeasonEndsAt = nthDayOfMonth(cal.getTime(),Calendar.SATURDAY,1);

        this.playoffStartsAt = nthDayOfMonth(cal.getTime(),Calendar.WEDNESDAY,2);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        this.playoffEndsBy = cal.getTime();
    }

    // Based on: https://praveenlobo.com/blog/get-last-friday-of-the-month-in-java
    private Date lastDayOfMonth(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // set calendar to the first day of the next month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);

        // calculate the number of days to subtract to get the last desired day of the month
        int lobosMagicNumber = (13 - day) % 7;
        cal.add(Calendar.DAY_OF_MONTH, -(((lobosMagicNumber + cal.get(Calendar.DAY_OF_WEEK)) % 7) + 1));

        return cal.getTime();
    }

    private Date nthDayOfMonth(Date date, int day, int nth){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set( Calendar.DAY_OF_MONTH, 1 );
        cal.add( Calendar.DAY_OF_MONTH, -1 );

        int occCount = 0;

        while ( occCount != nth ) {
            cal.add( Calendar.DAY_OF_MONTH, 1 );
            if ( cal.get( Calendar.DAY_OF_WEEK ) == day ) {
                occCount++;
            }
        }
        return cal.getTime();
    }
}
