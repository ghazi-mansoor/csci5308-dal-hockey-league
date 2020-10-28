package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Season {
    private League league = null;
    private Date currentDate;
    private Date regularSeasonStartsAt;
    private Date regularSeasonEndsAt;
    private Date tradeEndsAt;
    private Date playoffStartsAt;
    private Date playoffEndsBy;
    private List<TeamStanding> teamStandings  = new ArrayList<>();
    private List<Schedule> regularSchedules  = new ArrayList<>();
    private List<Schedule> playoffSchedules  = new ArrayList<>();

    public Season(League league){
        this.league = league;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        this.initDates(year);

        this.generateTeamStandings();
    }

    public Season(League league,int year){
        this.league = league;

        this.initDates(year);

        this.generateTeamStandings();
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

    public void addTeamStanding(TeamStanding teamStanding) {
        teamStandings.add(teamStanding);
    }

    public List<TeamStanding> getTeamStandings() {
        return teamStandings;
    }

    public void advanceTime(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.currentDate);
        cal.add(Calendar.DATE,1);
        this.currentDate = cal.getTime();
    }

    public void recordWin(String teamName){
        teamStandings.forEach(teamStanding -> {
            if(teamStanding.getTeamName().equals(teamName)){
                teamStanding.addWin();
            }
        });
        updateRanks();
    }

    public void recordLoss(String teamName){
        teamStandings.forEach(teamStanding -> {
            if(teamStanding.getTeamName().equals(teamName)){
                teamStanding.addLoss();
            }
        });
        updateRanks();
    }

    public boolean generateRegularSchedule(){
        if(teamStandings.size() <= 0){
            return false;
        }
        HashSet<String> conferenceNames = new HashSet<>();
        HashSet<String> divisionNames = new HashSet<>();
        teamStandings.forEach(teamStanding -> {
            conferenceNames.add(teamStanding.getConferenceName());
            divisionNames.add(teamStanding.getDivisionName());
        });

        divisionNames.forEach(divisionName -> {
            ArrayList<TeamStanding> sameDivisionTeamStanding = new ArrayList<>();
            teamStandings.forEach(teamStanding -> {
                if(teamStanding.getDivisionName().equals(divisionName)){
                    sameDivisionTeamStanding.add(teamStanding);
                }
            });
            sameDivisionTeamStanding.forEach(teamStanding1 -> {
                sameDivisionTeamStanding.forEach(teamStanding2 -> {
                    if(!teamStanding1.getTeamName().equals(teamStanding2.getTeamName())){
                        for(int i =0; i<4; i++){
                            Schedule schedule = new Schedule();
                            schedule.addTeamName(teamStanding1.getTeamName());
                            schedule.addTeamName(teamStanding2.getTeamName());
                            regularSchedules.add(schedule);
                        }
                    }
                });
            });
        });

        conferenceNames.forEach(conferenceName -> {
            ArrayList<TeamStanding> sameConferenceTeamStanding = new ArrayList<>();
            teamStandings.forEach(teamStanding -> {
                if(teamStanding.getConferenceName().equals(conferenceName)){
                    sameConferenceTeamStanding.add(teamStanding);
                }
            });
            sameConferenceTeamStanding.forEach(teamStanding1 -> {
                sameConferenceTeamStanding.forEach(teamStanding2 -> {
                    if(!teamStanding1.getDivisionName().equals(teamStanding2.getDivisionName())){
                        for(int i =0; i<3; i++){
                            Schedule schedule = new Schedule();
                            schedule.addTeamName(teamStanding1.getTeamName());
                            schedule.addTeamName(teamStanding2.getTeamName());
                            regularSchedules.add(schedule);
                        }
                    }
                });
            });
        });

        teamStandings.forEach(teamStanding1 -> {
            teamStandings.forEach(teamStanding2 -> {
                if (!teamStanding1.getConferenceName().equals(teamStanding2.getConferenceName())) {
                    for (int i = 0; i < 2; i++) {
                        Schedule schedule = new Schedule();
                        schedule.addTeamName(teamStanding1.getTeamName());
                        schedule.addTeamName(teamStanding2.getTeamName());
                        regularSchedules.add(schedule);
                    }
                }
            });
        });

        Collections.shuffle(regularSchedules, new Random());
        regularSchedules.forEach(regularSchedule -> {
            regularSchedule.setGameDate(randomDateBetween(this.regularSeasonStartsAt,this.regularSeasonEndsAt));
        });
        return true;
    };

    public boolean generatePlayoffSchedule(){
        HashSet<String> conferenceNames = new HashSet<>();
        teamStandings.forEach(teamStanding -> {
            conferenceNames.add(teamStanding.getConferenceName());
        });

        conferenceNames.forEach(conferenceName -> {
            HashSet<String> divisionNames = new HashSet<>();
            teamStandings.forEach(teamStanding -> {
                if(teamStanding.getConferenceName().equals(conferenceName)){
                    divisionNames.add(teamStanding.getDivisionName());
                }
            });
            List<List<TeamStanding>> divisionTop5 = new ArrayList<>();
            divisionNames.forEach(divisionName -> {
                List<TeamStanding> top5 = new ArrayList<>();
                teamStandings.forEach(teamStanding -> {
                    if(teamStanding.getDivisionName().equals(divisionName)){
                        if(teamStanding.getDivisionRank() <=5){
                            top5.add(teamStanding);
                        }
                    }
                });
                divisionTop5.add(top5);
            });
            TeamStanding team1 = divisionTop5.get(0).get(0);
            TeamStanding team2 = divisionTop5.get(1).get(0);
            TeamStanding team3 = divisionTop5.get(0).get(1);
            TeamStanding team4 = divisionTop5.get(1).get(1);
            TeamStanding team5 = divisionTop5.get(0).get(2);
            TeamStanding team6 = divisionTop5.get(1).get(2);
            TeamStanding team7;
            TeamStanding team8;
            TeamStanding div1Top4 = divisionTop5.get(0).get(3);
            TeamStanding div2Top4 = divisionTop5.get(1).get(3);
            TeamStanding div1Top5 = divisionTop5.get(1).get(4);
            TeamStanding div2Top5 = divisionTop5.get(1).get(4);
            if(div1Top4.getPoints() > div2Top4.getPoints()){
                team7 = div1Top4;
                if(div1Top5.getPoints() > div2Top4.getPoints()){
                    team8 = div1Top5;
                }else{
                    team8 = div2Top4;
                }
            }else{
                team7 = div2Top4;
                if(div1Top4.getPoints() > div2Top5.getPoints()){
                    team8 = div1Top4;
                }else{
                    team8 = div2Top5;
                }
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(this.playoffStartsAt);

            Schedule schedule = new Schedule();
            schedule.setGameDate(cal.getTime());
            schedule.addTeamName(team1.getTeamName());
            schedule.addTeamName(team8.getTeamName());
            playoffSchedules.add(schedule);

            cal.add(Calendar.DATE, 1);
            schedule = new Schedule();
            schedule.setGameDate(cal.getTime());
            schedule.addTeamName(team2.getTeamName());
            schedule.addTeamName(team7.getTeamName());
            playoffSchedules.add(schedule);

            cal.add(Calendar.DATE, 1);
            schedule = new Schedule();
            schedule.setGameDate(cal.getTime());
            schedule.addTeamName(team3.getTeamName());
            schedule.addTeamName(team4.getTeamName());
            playoffSchedules.add(schedule);

            cal.add(Calendar.DATE, 1);
            schedule = new Schedule();
            schedule.setGameDate(cal.getTime());
            schedule.addTeamName(team5.getTeamName());
            schedule.addTeamName(team6.getTeamName());
            playoffSchedules.add(schedule);
        });

        return true;
    }

    private void updateRanks(){
        HashSet<String> conferenceNames = new HashSet<>();
        HashSet<String> divisionNames = new HashSet<>();
        teamStandings.forEach(teamStanding -> {
            conferenceNames.add(teamStanding.getConferenceName());
            divisionNames.add(teamStanding.getDivisionName());
        });

        //Update Division Ranks
        divisionNames.forEach(divisionName -> {
            List<TeamStanding> divisionTeamStandings = new ArrayList<>();

            teamStandings.forEach(teamStanding -> {
                if(teamStanding.getDivisionName().equals(divisionName)){
                    divisionTeamStandings.add(teamStanding);
                }
            });

            Collections.sort(divisionTeamStandings);
            Collections.reverse(divisionTeamStandings);
            for(int i =0 ; i < divisionTeamStandings.size(); i++){
                divisionTeamStandings.get(i).setDivisionRank(i+1);
            }
        });

        //Update Conference Ranks
        conferenceNames.forEach(conferenceName -> {
            List<TeamStanding> conferenceTeamStandings = new ArrayList<>();

            teamStandings.forEach(teamStanding -> {
                if(teamStanding.getConferenceName().equals(conferenceName)){
                    conferenceTeamStandings.add(teamStanding);
                }
            });

            Collections.sort(conferenceTeamStandings);
            Collections.reverse(conferenceTeamStandings);
            for(int i =0 ; i < conferenceTeamStandings.size(); i++){
                conferenceTeamStandings.get(i).setConferenceRank(i+1);
            }
        });

        //Update League Ranks
        List<TeamStanding> leagueTeamStandings = teamStandings;
        Collections.sort(leagueTeamStandings);
        Collections.reverse(leagueTeamStandings);
        for(int i =0 ; i < leagueTeamStandings.size(); i++){
            leagueTeamStandings.get(i).setDivisionRank(i+1);
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

    private void generateTeamStandings(){
        if(league != null){
            Map<String, Conference> conferences = league.getConferences();
            conferences.forEach((conferenceName,conference) ->{
                Map<String, Division> divisions = conference.getDivisions();
                divisions.forEach((divisionName, division) -> {
                    Map<String, Team> teams = division.getTeams();
                    teams.forEach((teamName, team) -> {
                        TeamStanding teamStanding = new TeamStanding(team.getTeamName(),division.getDivisionName(),conference.getConferenceName(),0,0,0,0,0,0);
                        this.teamStandings.add(teamStanding);
                    });
                });
            });
        }
    }

    //Based on: https://www.baeldung.com/java-random-dates
    public  Date randomDateBetween(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }
}
