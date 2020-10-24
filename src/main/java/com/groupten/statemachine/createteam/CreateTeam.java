package com.groupten.statemachine.createteam;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.team.Team;
import com.groupten.statemachine.console.IConsole;
import com.groupten.injector.Injector;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CreateTeam implements ICreateTeam {

    private String conferenceName, divisionName, teamName;
    private GeneralManager generalManager;
    private Coach headCoach;
    private League leagueLOM;

    public CreateTeam() { }

    @Override
    public void userPromptForNewTeam() {

        IConsole console = Injector.injector().getConsoleObject();

        console.printLine("Enter the Conference name:");
        conferenceName = console.readLine();

        console.printLine("Enter the Division name:");
        divisionName = console.readLine();

        console.printLine("Enter the Team name:");
        teamName = console.readLine();

    }

    @Override
    public boolean validateUserInput() {
        return (conferenceName.length() > 0 && divisionName.length() > 0 && teamName.length() > 0);
    }

    @Override
    public boolean ifConferenceAndDivisionExist() {
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        leagueLOM = leagueModel.getCurrentLeague();
        if (leagueLOM.containsConference(conferenceName)) {
            Conference conference = leagueLOM.getConference(conferenceName);
            return conference.containsDivision(divisionName);
        } else {
            return false;
        }
    }

    @Override
    public boolean selectGeneralManager() {
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        IConsole console = Injector.injector().getConsoleObject();
        leagueLOM = leagueModel.getCurrentLeague();

        List<GeneralManager> generalManagers = new ArrayList<>(leagueLOM.getGeneralManagers());
        GeneralManager tempGM;

        console.printLine("\nPlease select a General Manager\n");
        console.printLine("ID\t\tManager Name");

        for(int i = 0; i < generalManagers.size(); i++){
            tempGM = generalManagers.get(i);
            console.printLine((i + 1) + "\t\t" + tempGM.getManagerName());
        }

        console.printLine("\nChoice (ID)?");

        try{
            int choice = console.readInteger();
            if(choice >= 1 && choice <= (generalManagers.size() + 1)){
                this.generalManager = generalManagers.get(choice - 1);
                generalManagers.get(choice - 1).setManagerTeamStatus(true);
                leagueLOM.removeGeneralManager(generalManagers.get(choice - 1));
                return true;
            }else{
                return false;
            }
        }catch (InputMismatchException exception){
            return false;
        }
    }

    @Override
    public boolean selectHeadCoach() {
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        IConsole console = Injector.injector().getConsoleObject();
        leagueLOM = leagueModel.getCurrentLeague();

        List<Coach> coaches = new ArrayList<>(leagueLOM.getCoaches());
        Coach tempCoach;

        console.printLine("\nPlease select a coach\n");

        console.printLine("ID\t\tSkating\t\tShooting\t\tChecking\t\tSaving\t\tName");

        for(int i = 0; i < coaches.size(); i++){
            tempCoach = coaches.get(i);
            console.printLine((i + 1) + "\t\t" + tempCoach.getSkating() +
                    "\t\t\t" + tempCoach.getShooting() + "\t\t\t\t" + tempCoach.getChecking() +
                    "\t\t\t\t" + tempCoach.getSaving() + "\t\t\t" + tempCoach.getCoachName());
        }

        console.printLine("\nChoice (ID)?");

        try{
            int choice = console.readInteger();
            if(choice >= 1 && choice <= (coaches.size() + 1)){
                this.headCoach = coaches.get(choice - 1);
                coaches.get(choice - 1).setCoachTeamStatus(true);
                leagueLOM.removeCoach(coaches.get(choice - 1));
                return true;
            }else{
                return false;
            }
        }catch (InputMismatchException exception){
            return false;
        }
    }

    @Override
    public boolean selectTeamPlayer() {
        return false;
    }

    @Override
    public boolean instantiateNewTeam() {
        ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
        leagueLOM = leagueModel.getCurrentLeague();
        Conference conference = leagueLOM.getConference(conferenceName);
        Division division = conference.getDivision(divisionName);
        Team team = new Team(teamName);
        team.setGeneralManager(generalManager);
        team.setHeadCoach(headCoach);
        return division.addTeam(team);
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
