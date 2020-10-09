package com.groupten.jdbc.conference;

import java.util.HashMap;
import java.util.List;

public interface IConferenceDAO {
    int createConference(int leagueId, String conferenceName);
    List<HashMap<String,Object>> getConferences(String colName, String colValue);
    void updateConference(int conferenceId, String conferenceName);
    void deleteConference(int conferenceId);
    List<HashMap<String,Object>> getConferenceDivisions(int conferenceId);
}

