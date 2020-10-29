package com.groupten.statemachine.createteam;

public interface ICreateTeam {

    void userPromptForNewTeam();

    boolean validateUserInput();

    boolean ifConferenceAndDivisionExist();

    boolean selectTeamGeneralManager();

    boolean selectTeamHeadCoach();

    boolean selectTeamGoalies();

    boolean selectTeamSkaters();

    boolean instantiateNewTeam();

}
