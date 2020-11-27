package com.groupten.statemachine.createteam;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CreateTeam implements ICreateTeam {

    private final int NO_OF_GOALIE = 2;
    private final int NO_OF_SKATERS = 18;

    private String conferenceName, divisionName, teamName;
    private GeneralManager generalManager;
    private Coach headCoach;
    private List<Player> freeAgents = new ArrayList<>();
    private League leagueLOM;

    public CreateTeam() {}

    @Override
    public void userPromptForNewTeam() {

        IConsole console = Injector.instance().getConsoleObject();

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
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        leagueLOM = leagueModel.getCurrentLeague();
        if (leagueLOM.containsConference(conferenceName)) {
            Conference conference = leagueLOM.getConference(conferenceName);
            return conference.containsDivision(divisionName);
        } else {
            return false;
        }
    }

    @Override
    public boolean selectTeamGeneralManager() {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        IConsole console = Injector.instance().getConsoleObject();
        leagueLOM = leagueModel.getCurrentLeague();

        List<GeneralManager> generalManagers = new ArrayList<>(leagueLOM.getGeneralManagers());
        GeneralManager tempGM;

        console.printLine("\nPlease select a General Manager\n");
        console.printLine("ID\t\tManager Name");

        for (int i = 0; i < generalManagers.size(); i++) {
            tempGM = generalManagers.get(i);
            console.printLine((i + 1) + "\t\t" + tempGM.getManagerName());
        }

        console.printLine("\nEnter General Manager (ID)?");

        try {
            int choice = console.readInteger();
            if (choice >= 1 && choice <= (generalManagers.size())) {
                this.generalManager = generalManagers.get(choice - 1);
                leagueLOM.removeGeneralManager(generalManagers.get(choice - 1));
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException exception) {
            return false;
        }
    }

    @Override
    public boolean selectTeamHeadCoach() {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        IConsole console = Injector.instance().getConsoleObject();
        leagueLOM = leagueModel.getCurrentLeague();

        List<Coach> coaches = new ArrayList<>(leagueLOM.getCoaches());
        Coach tempCoach;

        console.printLine("\nPlease select a coach\n");

        console.printLine("ID\t\tSkating\t\tShooting\t\tChecking\t\tSaving\t\tName");

        for (int i = 0; i < coaches.size(); i++) {
            tempCoach = coaches.get(i);
            console.printLine((i + 1) + "\t\t" + tempCoach.getSkating() +
                    "\t\t\t" + tempCoach.getShooting() + "\t\t\t\t" + tempCoach.getChecking() +
                    "\t\t\t\t" + tempCoach.getSaving() + "\t\t\t" + tempCoach.getCoachName());
        }

        console.printLine("\nEnter Head Coach (ID)?");

        try {
            int choice = console.readInteger();
            if (choice >= 1 && choice <= (coaches.size())) {
                this.headCoach = coaches.get(choice - 1);
                leagueLOM.removeCoach(coaches.get(choice - 1));
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException exception) {
            return false;
        }
    }

    @Override
    public boolean selectTeamGoalies() {

        boolean status = false;

        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        IConsole console = Injector.instance().getConsoleObject();
        leagueLOM = leagueModel.getCurrentLeague();

        for (int i = 1; i <= NO_OF_GOALIE; i++) {
            List<Player> goalies = new ArrayList<>(leagueLOM.getFreeAgentsGoalies());
            Player goalie;

            console.printLine("\nPlease select a goalie\n");

            console.printLine("ID\t\tAge\t\t\tSkating\t\t\tShooting\t\t\tChecking\t\tSaving\t\tName");

            for (int j = 0; j < goalies.size(); j++) {
                goalie = goalies.get(j);
                console.printLine((j + 1) + "\t\t" + goalie.getAge() + "\t\t" + goalie.getSkating() +
                        "\t\t\t" + goalie.getShooting() + "\t\t\t\t\t" + goalie.getChecking() +
                        "\t\t\t\t" + goalie.getSaving() + "\t\t\t" + goalie.getPlayerName());
            }

            console.printLine("\nEnter Goalie (ID)?");

            try {
                int choice = console.readInteger();
                if (choice >= 1 && choice <= (goalies.size())) {
                    freeAgents.add(goalies.get(choice - 1));
                    leagueLOM.removeFreeAgent(goalies.get(choice - 1));
                    status = true;
                } else {
                    status = false;
                    break;
                }
            } catch (InputMismatchException exception) {
                status = false;
                break;
            }
            if (status == false) {
                break;
            }
        }
        return status;
    }

    @Override
    public boolean selectTeamSkaters() {
        boolean status = false;

        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        IConsole console = Injector.instance().getConsoleObject();
        leagueLOM = leagueModel.getCurrentLeague();

        for (int i = 1; i <= NO_OF_SKATERS; i++) {
            List<Player> skaters = new ArrayList<>(leagueLOM.getFreeAgentsSkaters());
            Player skater;

            console.printLine("\nPlease select a skater\n");

            console.printLine("ID\t\tAge\t\t\tSkating\t\t\tShooting\t\tChecking\t\tSaving\t\tPosition\t\tName");

            for (int j = 0; j < skaters.size(); j++) {
                skater = skaters.get(j);
                console.printLine((j + 1) + "\t\t" + skater.getAge() + "\t\t" + skater.getSkating() +
                        "\t\t\t" + skater.getShooting() + "\t\t\t\t" + skater.getChecking() +
                        "\t\t\t\t" + skater.getSaving() + "\t\t\t" + skater.getPosition() + "\t\t\t" + skater.getPlayerName());
            }

            console.printLine("\nEnter Skater (ID)?");

            try {
                int choice = console.readInteger();
                if (choice >= 1 && choice <= (skaters.size())) {
                    freeAgents.add(skaters.get(choice - 1));
                    leagueLOM.removeFreeAgent(skaters.get(choice - 1));
                    status = true;
                } else {
                    status = false;
                    break;
                }
            } catch (InputMismatchException exception) {
                status = false;
                break;
            }
            if (status == false) {
                break;
            }
        }
        return status;
    }

    @Override
    public boolean instantiateNewTeam() {
        ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
        leagueLOM = leagueModel.getCurrentLeague();
        Conference conference = leagueLOM.getConference(conferenceName);
        Division division = conference.getDivision(divisionName);
        Team team = new Team(teamName);
        leagueLOM.setUserTeam(teamName);
        team.setGeneralManager(generalManager);
        team.setHeadCoach(headCoach);
        team.setaITeam(false);
        for (Player player : freeAgents) {
            team.addActivePlayer(player);
        }
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

    public void setGeneralManager(GeneralManager generalManager) {
        this.generalManager = generalManager;
    }

    public void setHeadCoach(Coach headCoach) {
        this.headCoach = headCoach;
    }

    public void setFreeAgents(List<Player> freeAgents) {
        this.freeAgents = freeAgents;
    }

}
