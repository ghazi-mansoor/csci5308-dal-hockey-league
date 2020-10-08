package com.groupten.statemachine.createteam;

public interface CreateTeamInterface {

    void userPromptForNewTeam();
    boolean validateUserInput();
    boolean ifConferenceAndDivisionExist();
    boolean instantiateNewTeam();
    boolean persistLeagueModel();

}
