package com.groupten.statemachine.createteam;

public interface CreateTeamInterface {

    void userPromptForNewTeam();
    boolean validateUserInput();
    boolean ifExist();
    boolean instantiateNewTeam();
    boolean persistLeagueModel();

}
