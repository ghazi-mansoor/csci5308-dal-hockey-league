package com.groupten.leagueobjectmodel.season;

import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.*;

public class Season {

    private Calendar currentDate = Calendar.getInstance();
    private Map<Integer,TeamStanding> teamStandings  = new HashMap<Integer, TeamStanding>();;

    public Season(){
        Date today = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(today);
        this.currentDate.set(now.get(Calendar.YEAR), Calendar.SEPTEMBER,30);
    }

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public boolean addTeamStanding(int teamId, TeamStanding teamStanding) {
        if(teamStandings.containsKey(teamId)){
            return false;
        }else{
            teamStandings.put(teamId,teamStanding);
            return true;
        }
    }

    public Map<Integer,TeamStanding> getTeamStandings() {
        return teamStandings;
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

        Iterator iterator = sortedTeamStandings.entrySet().iterator();
        int i = 1;
        while(iterator.hasNext()){
            Map.Entry me = (Map.Entry) iterator.next();
            TeamStanding teamStanding = (TeamStanding) me.getValue();
            teamStanding.setLeagueRank(i);
            i++;
        }
    }
}
