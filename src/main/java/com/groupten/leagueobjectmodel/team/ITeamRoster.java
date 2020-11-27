package com.groupten.leagueobjectmodel.team;

import com.groupten.leagueobjectmodel.player.Player;

import java.util.List;

public interface ITeamRoster {
    List<Player> createActivePlayerRoster();
    List<Player> createInActivePlayerRoster();
}
