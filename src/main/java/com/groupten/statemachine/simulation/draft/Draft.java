package com.groupten.statemachine.simulation.draft;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;
import com.groupten.statemachine.simulation.draft.strategy.IPlayerCreationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Draft implements IDraft {
    private ILeagueModel leagueModel;
    private IPlayerCreationContext playerCreationContext;
    private List<TeamStanding> regularSeasonStandings = new ArrayList<>();
    private List<TeamStanding> playoffStandings = new ArrayList<>();

    @Override
    public void execute(Season season) {
        leagueModel = Injector.instance().getLeagueModelObject();
        regularSeasonStandings = season.getTeamStandings();
        playoffStandings = season.getPlayoffTeamStandings();
        sortTeamStandingsInAscendingOrder();
        removePlayoffTeamsFromRegularTeamStandings();

        System.out.println(regularSeasonStandings.get(0).getPoints());
        System.out.println(regularSeasonStandings.get(1).getPoints());
        System.out.println(playoffStandings.get(0).getPoints());
        System.out.println(playoffStandings.get(1).getPoints());
    }

    private void sortTeamStandingsInAscendingOrder() {
        Collections.sort(regularSeasonStandings);
        Collections.sort(playoffStandings);
    }

    private void removePlayoffTeamsFromRegularTeamStandings() {
        for (TeamStanding teamStanding : playoffStandings) {
            regularSeasonStandings.remove(teamStanding);
        }
    }
}
