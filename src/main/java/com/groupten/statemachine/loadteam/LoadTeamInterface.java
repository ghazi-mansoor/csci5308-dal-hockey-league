package com.groupten.statemachine.loadteam;

public interface LoadTeamInterface {

    void userPromptForLoadingTeam();
    boolean doesTeamExist();
    void loadExistingLeague();
    boolean validateUserInput();

}
