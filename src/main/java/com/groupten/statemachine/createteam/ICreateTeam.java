package com.groupten.statemachine.createteam;

public interface ICreateTeam {

    void userPromptForNewTeam();
    boolean validateUserInput();
    boolean ifConferenceAndDivisionExist();
    boolean instantiateNewTeam();
    void persistLeagueModel();

}
