package com.groupten.statemachine.simulation.trophy;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.season.ISeasonObserver;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;
import com.groupten.statemachine.simulation.simulategame.strategy.IAlgoStrategyObserver;
import com.groupten.statemachine.simulation.training.ITrainingObserver;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trophy implements ITrophy, ITrainingObserver, ISeasonObserver, IAlgoStrategyObserver {

    private LinkedList<TrophyPerSeason> historicData = new LinkedList<>();
    private LinkedHashMap<Coach, Integer> coachRanking;
    private LinkedHashMap<Team, Integer> teamRanking = new LinkedHashMap<>();

    public void setCoachRanking(LinkedHashMap<Coach, Integer> coachRanking) {
        this.coachRanking = coachRanking;
    }

    public void setTeamRanking(LinkedHashMap<Team, Integer> teamRanking) {
        this.teamRanking = teamRanking;
    }

    @Override
    public void updateCoachRanking(LinkedHashMap<Coach, Integer> coachRanking) {
        this.coachRanking = coachRanking;
    }

    @Override
    public void updateRegularSeasonEnd(Season season) {
        List<TeamStanding> teamStandings = season.getTeamStandings();
        for (TeamStanding teamStanding : teamStandings) {
            teamRanking.put(teamStanding.getTeam(), teamStanding.getPoints());
        }
    }

    @Override
    public void updateShots(Map<Player, Integer> shots) {

    }

    @Override
    public void updatePenalties(Map<Player, Integer> penalties) {

    }

    @Override
    public void updateGoals(Map<Player, Integer> goals) {

    }

    @Override
    public void updateSaves(Map<Player, Integer> goals) {

    }

    @Override
    public void awardTrophy() {

        TrophyPerSeason trophyPerSeason = new TrophyPerSeason();
        historicData.offerFirst(trophyPerSeason);

        int bestTeamPoint = -1;
        Team bestTeam = null;
        for (Map.Entry<Team, Integer> entry : teamRanking.entrySet()) {
            if (entry.getValue() > bestTeamPoint) {
                bestTeamPoint = entry.getValue();
                bestTeam = entry.getKey();
            }
        }

        trophyPerSeason.setPresidentTrophy(bestTeam);

        int bestCoachCount = -1;
        Coach bestCoach = null;
        for (Map.Entry<Coach, Integer> entry : coachRanking.entrySet()) {
            System.out.println(bestCoachCount + " " + entry.getValue() + " " + entry.getKey().getCoachName());
            if (entry.getValue() > bestCoachCount) {
                bestCoachCount = entry.getValue();
                bestCoach = entry.getKey();
            }
        }

        trophyPerSeason.setJackAdamAward(bestCoach);

        int leastTeamPoint = 99999;
        Team leastTeam = null;
        for (Map.Entry<Team, Integer> entry : teamRanking.entrySet()) {
            if (entry.getValue() < leastTeamPoint) {
                leastTeamPoint = entry.getValue();
                leastTeam = entry.getKey();
            }
        }

        trophyPerSeason.setParticipationAward(leastTeam);

        teamRanking.clear();
        coachRanking.clear();

    }

    @Override
    public void trophyWinners() {
        IConsole console = Injector.instance().getConsoleObject();

        int seasonNumber = historicData.size();

        System.out.println(historicData);

        console.printLine("--------- Trophy Winners ----------");
        for (TrophyPerSeason trophyPerSeason : historicData) {
            console.printLine("Winner of Season " + seasonNumber--);
            console.printLine("\nTrophy \t\t\t Winner");
            console.printLine("President's Trophy\t\t\t" + trophyPerSeason.getPresidentTrophy().getTeamName());
//            console.printLine("Calder Memorial Trophy\t\t\t" + trophyPerSeason.getCalderMemorialTrophy().getPlayerName());
//            console.printLine("Vezina Trophy\t\t\t" + trophyPerSeason.getVezinaTrophy().getPlayerName());
            console.printLine("Jack Adam's Award\t\t\t" + trophyPerSeason.getJackAdamAward().getCoachName());
//            console.printLine("Maurice Richard Trophy\t\t\t" + trophyPerSeason.getMauriceRichardTrophy().getPlayerName());
//            console.printLine("Rob Hawkey Memorial Cup\t\t\t" + trophyPerSeason.getRobHawkeyMemorialTrophy().getPlayerName());
            console.printLine("Participation Award\t\t\t" + trophyPerSeason.getParticipationAward().getTeamName());
        }

    }
}
