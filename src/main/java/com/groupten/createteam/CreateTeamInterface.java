package com.groupten.createteam;

public interface CreateTeamInterface {

    void userPromptForNewTeam();
    boolean validateUserInput();
    boolean ifExist();
    void instantiateNewTeam();
    void persistAllData();

}
