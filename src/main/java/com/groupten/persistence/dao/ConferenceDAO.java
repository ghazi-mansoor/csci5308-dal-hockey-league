package com.groupten.persistence.dao;

import com.groupten.persistence.dao.IConferenceDAO;
import com.groupten.persistence.database.StoredProcedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConferenceDAO implements IConferenceDAO {
    @Override
    public int createConference(int leagueId, String conferenceName) {
        int conferenceId = 0;

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("createConference(?,?,?)");
            storedProcedure.setParameter(1,leagueId);
            storedProcedure.setParameter(2,conferenceName);
            storedProcedure.registerOutputParameterInt(3);
            storedProcedure.execute();
            conferenceId = storedProcedure.getOutputParameterInt(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }

        return conferenceId;
    }

    @Override
    public List<HashMap<String, Object>> getConferences(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getConferences(?,?)");
            storedProcedure.setParameter(1, colName);
            storedProcedure.setParameter(2, colValue);
            list = storedProcedure.executeWithResults();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }

        return list;
    }

    @Override
    public void updateConference(int conferenceId, String conferenceName) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("updateConference(?,?)");
            storedProcedure.setParameter(1,conferenceId);
            storedProcedure.setParameter(2,conferenceName);
            storedProcedure.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }
    }

    @Override
    public void deleteConference(int conferenceId) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("deleteConference(?)");
            storedProcedure.setParameter(1,conferenceId);
            storedProcedure.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }
    }

    @Override
    public List<HashMap<String, Object>> getConferenceDivisions(int conferenceId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getConferenceDivisions(?)");
            storedProcedure.setParameter(1, conferenceId);
            list = storedProcedure.executeWithResults();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }

        return list;
    }
}
