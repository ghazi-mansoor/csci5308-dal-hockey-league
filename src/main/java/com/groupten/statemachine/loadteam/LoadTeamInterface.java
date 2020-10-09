package com.groupten.statemachine.loadteam;

public interface LoadTeamInterface {

    void userPromptForLoadingTeam();
    boolean doesTeamExist();
    boolean loadExistingLeague();
    boolean validateUserInput();

}
