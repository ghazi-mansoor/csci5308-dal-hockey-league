package com.groupten.statemachine.loadteam;

public interface LoadTeamInterface {

    void userPromptForLoadingTeam();
    boolean doesTeamExist();
    void loadExistingTeam();
    void loadExistingLeague();

}
