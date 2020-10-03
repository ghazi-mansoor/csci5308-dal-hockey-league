package com.groupten.jdbc.conference;

import java.sql.ResultSet;

public interface ConferenceInterface {
    int createConference(int leagueId, String conferenceName);
    ResultSet listConferences(int leagueId);
    ResultSet getConferences(int leagueId, String colName, String colValue);
    void updateConference(int conferenceId, String conferenceName);
    void deleteConference(int conferenceId);
}

