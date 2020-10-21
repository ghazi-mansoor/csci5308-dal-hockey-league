package com.groupten.leagueobjectmodel.league;

import com.groupten.leagueobjectmodel.coach.Coach;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.generalmanager.GeneralManager;
import com.groupten.leagueobjectmodel.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {
    private int leagueID;
    private String leagueName;
    private Map<String, Conference> conferences = new HashMap<>();
    private List<Player> freeAgents = new ArrayList<>();
    private List<Coach> coaches = new ArrayList<>();
    private List<GeneralManager> generalManagers = new ArrayList<>();

    public League(String lN) {
        leagueName = lN;
    }

    public League(int lID, String lN) {
        this(lN);
        leagueID = lID;
    }

    public boolean addConference(Conference conference) {
        String conferenceName = conference.getConferenceName();
        int initialSize = conferences.size();
        conferences.put(conferenceName, conference);

        return conferences.size() > initialSize;
    }

    public boolean addFreeAgent(Player player) {
        int initialSize = freeAgents.size();
        freeAgents.add(player);
        return freeAgents.size() > initialSize;
    }

    public boolean addCoach(Coach coach) {
        int initialSize = coaches.size();
        coaches.add(coach);
        return coaches.size() > initialSize;
    }

    public boolean addGeneralManager(GeneralManager generalManager) {
        int initialSize = generalManagers.size();
        generalManagers.add(generalManager);
        return generalManagers.size() > initialSize;
    }

    public boolean isNumberOfConferencesEven() {
        return conferences.size() % 2 == 0;
    }

    public boolean containsConference(String conferenceName) {
        return conferences.containsKey(conferenceName);
    }

    public Conference getConference(String conferenceName) {
        return conferences.get(conferenceName);
    }

    public static boolean isLeagueNameValid(String lN) {
        if (lN.isEmpty() || lN.isBlank() || lN.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String lN) {
        leagueName = lN;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int lID) {
        leagueID = lID;
    }
}
