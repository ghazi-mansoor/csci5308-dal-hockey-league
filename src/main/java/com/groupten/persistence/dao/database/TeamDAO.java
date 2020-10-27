package com.groupten.persistence.dao.database;

import com.groupten.persistence.dao.ITeamDAO;
import com.groupten.persistence.database.StoredProcedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamDAO implements ITeamDAO {

    @Override
    public int createTeam(int divisionId, String teamName, String generalManager, String headCoach) {
        int teamId = 0;

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("createTeam(?,?,?,?,?)");
            storedProcedure.setParameter(1, divisionId);
            storedProcedure.setParameter(2, teamName);
            storedProcedure.setParameter(3, generalManager);
            storedProcedure.setParameter(4, headCoach);

            storedProcedure.registerOutputParameterInt(5);
            storedProcedure.execute();
            teamId = storedProcedure.getOutputParameterInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }

        return teamId;
    }

    @Override
    public List<HashMap<String, Object>> getTeams(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getTeams(?,?)");
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
    public void updateTeam(int teamId, String teamName, String generalManager, String headCoach) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("updateTeam(?,?,?,?)");
            storedProcedure.setParameter(1, teamId);
            storedProcedure.setParameter(2, teamName);
            storedProcedure.setParameter(3, generalManager);
            storedProcedure.setParameter(4, headCoach);
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
    public void deleteTeam(int teamId) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("deleteTeam(?)");
            storedProcedure.setParameter(1,teamId);
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
    public List<HashMap<String, Object>> getTeamPlayers(int teamId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getTeamPlayers(?)");
            storedProcedure.setParameter(1, teamId);
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
    public void attachTeamPlayer(int teamId, int playerId) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("attachTeamPlayer(?,?)");
            storedProcedure.setParameter(1,teamId);
            storedProcedure.setParameter(2, playerId);
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
    public void detachTeamPlayer(int teamId, int playerId) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("attachTeamPlayer(?,?)");
            storedProcedure.setParameter(1,teamId);
            storedProcedure.setParameter(2, playerId);
            storedProcedure.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }
    }
}
