package com.groupten.jdbc.conference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ConferenceInterface {
    int createConference(int leagueId, String conferenceName);
    List<HashMap<String,Object>> listConferences(int leagueId);
    List<HashMap<String,Object>> getConferences(int leagueId, String colName, String colValue);
    void updateConference(int conferenceId, String conferenceName);
    void deleteConference(int conferenceId);
}

