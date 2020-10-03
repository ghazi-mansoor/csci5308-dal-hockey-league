package com.groupten.leagueobjectmodel;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LeagueModel leagueModel = new LeagueModel();
        leagueModel.addLeague(1, "NHL");
        leagueModel.addConferenceToLeague(1, "Canada");
        leagueModel.addDivisionToConference(1, "Eastern Canada");
        leagueModel.addDivisionToConference(2, "Western Canada");
        leagueModel.addConferenceToLeague(2, "USA");
        leagueModel.addDivisionToConference(1, "Eastern USA");
        leagueModel.addDivisionToConference(2, "Western USA");

        ArrayList<Conference> conferences;
        ArrayList<Division> divisions;

        conferences = leagueModel.league.getConferences();

        for (Conference conference : conferences) {
            System.out.println("% ---- Conference ---- %");
            System.out.println(conference.getConferenceName());

            divisions = conference.getDivisions();
            System.out.println("% ---- Division ---- %");
            for (Division division : divisions) {
                System.out.println(division.getDivisionName());
            }
        }
    }
}
