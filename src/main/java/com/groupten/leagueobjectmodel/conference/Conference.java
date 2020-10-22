package com.groupten.leagueobjectmodel.conference;

import com.groupten.leagueobjectmodel.division.Division;

import java.util.HashMap;
import java.util.Map;

public class Conference {
    private int conferenceID;
    private String conferenceName;
    private Map<String, Division> divisions = new HashMap<>();

    public Conference(String cN) {
        conferenceName = cN;
    }

    public Conference(int cID, String cN) {
        this(cN);
        conferenceID = cID;
    }

    public Conference() {
	}
    

	public Map<String, Division> getDivisions() {
		return divisions;
	}

	public void setDivisions(Map<String, Division> divisions) {
		this.divisions = divisions;
	}

	public boolean addDivision(Division division) {
        String divisionName = division.getDivisionName();
        int initialSize = divisions.size();
        divisions.put(divisionName, division);

        return divisions.size() > initialSize;
    }

    public boolean isNumberOfDivisionsEven() {
        return divisions.size() % 2 == 0;
    }

    public boolean containsDivision(String divisionName) {
        return divisions.containsKey(divisionName);
    }

    public static boolean isConferenceNameValid(String cN) {
        if (cN.isEmpty() || cN.isBlank() || cN.toLowerCase().equals("null")) {
            return false;
        } else {
            return true;
        }
    }

    public Division getDivision(String divisionName) {
        return divisions.get(divisionName);
    }

    public int getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(int cID) {
        conferenceID = cID;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String cN) {
        conferenceName = cN;
    }
}
