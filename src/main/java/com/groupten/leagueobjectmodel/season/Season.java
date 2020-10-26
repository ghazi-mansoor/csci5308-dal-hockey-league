package com.groupten.leagueobjectmodel.season;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.schedule.Schedule;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.*;

public class Season {
    private Date currentDate;
    private Date regularSeasonStartsAt;
    private Date regularSeasonEndsAt;
    private Date tradeEndsAt;
    private Date playoffStartsAt;
    private Date playoffEndsBy;
    private Map<String,TeamStanding> teamStandings  = new HashMap<String, TeamStanding>();
    private Map<String, Schedule> regularSchedules  = new HashMap<String, Schedule>();
    private Map<String,Schedule> playOffSchedules  = new HashMap<String, Schedule>();

    public Season(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        this.initDates(year);
    }

    public Season(int year){
        this.initDates(year);
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

    public boolean addRegularSchedules(String GroupName, Schedule schedule) {
        if(regularSchedules.containsKey(GroupName)){
            return false;
        }else{
            regularSchedules.put(GroupName,schedule);
            return true;
        }
    }

    public Map<String, Schedule> getRegularSchedules() {
        return regularSchedules;
    }

    public boolean addPlayoffSchedules(String GroupName, Schedule schedule) {
        if(playOffSchedules.containsKey(GroupName)){
            return false;
        }else{
            playOffSchedules.put(GroupName,schedule);
            return true;
        }
    }

    public Map<String, Schedule> getPlayOffSchedules() {
        return playOffSchedules;
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

    public void generateRegularSchedule(){
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        League leagueLOM = leagueModel.getCurrentLeague();
        System.out.println(leagueLOM.toString());
    }

    public void generatePlayoffSchedule(){
//        ToDo populate playoffSchedule
    }

    private void updateRanks(){
        TreeMap<String, TeamStanding> sortedTeamStandings = new TreeMap<>(teamStandings);

        Iterator iterator = sortedTeamStandings.entrySet().iterator();
        int i = 1;
        while(iterator.hasNext()){
            Map.Entry me = (Map.Entry) iterator.next();
            TeamStanding teamStanding = (TeamStanding) me.getValue();
            teamStanding.setLeagueRank(i);
            i++;
        }

//        ToDo filter teamStandings for each division and conference and update rank accordingly
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
