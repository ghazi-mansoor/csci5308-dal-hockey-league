package com.groupten.leagueobjectmodel.team;

import com.groupten.leagueobjectmodel.player.Player;

public interface ITeam {
    boolean addPlayerToTeam(Player player);
    boolean saveTeamToDB();
    boolean isOnlyOnePlayerCaptain();
    void loadPlayersFromDB();
}
