package com.groupten.dao.database;

import com.groupten.dao.IPlayerDAO;
import com.groupten.database.StoredProcedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerDAO implements IPlayerDAO {

    @Override
    public int createPlayer(int leagueId, String playerName, String position, boolean isCaptain) {
        int playerId = 0;

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("createPlayer(?,?,?,?,?)");
            storedProcedure.setParameter(1, leagueId);
            storedProcedure.setParameter(2, playerName);
            storedProcedure.setParameter(3, position);
            storedProcedure.setParameter(4, isCaptain);
            storedProcedure.registerOutputParameterInt(3);
            storedProcedure.execute();
            playerId = storedProcedure.getOutputParameterInt(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }

        return playerId;
    }

    @Override
    public List<HashMap<String, Object>> getPlayers(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getPlayers(?,?)");
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
    public void updatePlayer(int playerId, String playerName, String position, boolean isCaptain) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("updatePlayer(?,?,?,?)");
            storedProcedure.setParameter(1,playerId);
            storedProcedure.setParameter(2,playerName);
            storedProcedure.setParameter(2,position);
            storedProcedure.setParameter(2,isCaptain);
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
    public void deletePlayer(int playerId) {
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("deletePlayer(?)");
            storedProcedure.setParameter(1,playerId);
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
    public List<HashMap<String, Object>> getCaptains(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getCaptains(?,?)");
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
    public List<HashMap<String, Object>> getFreeAgents(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getFreeAgents(?,?)");
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
}
