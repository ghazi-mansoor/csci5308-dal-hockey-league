package com.groupten.statemachine.simulation.draft;

import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.season.Season;
import com.groupten.leagueobjectmodel.teamstanding.TeamStanding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Draft implements IDraft {
    private List<TeamStanding> regularSeasonStandings = new ArrayList<>();
    private List<TeamStanding> playoffStandings = new ArrayList<>();

    @Override
    public void execute(Season season) {
        initialize(season);
    }

    private void initialize(Season season) {
        preprocessTeamStandings(season);
    }

    private void preprocessTeamStandings(Season season) {
        regularSeasonStandings = season.getTeamStandings();
        playoffStandings = season.getPlayoffTeamStandings();
        sortTeamStandingsInAscendingOrder();
        removePlayoffTeamsFromRegularTeamStandings();
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
