package com.groupten.persistence.dao.database;

import com.groupten.persistence.dao.ILeagueDAO;
import com.groupten.persistence.database.StoredProcedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeagueDAO implements ILeagueDAO {

    @Override
    public int createLeague(String leagueName) {
        long leagueId = 0;

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("createLeague(?,?)");
            storedProcedure.setParameter(1,leagueName);
            storedProcedure.registerOutputParameterInt(2);
            storedProcedure.execute();
            leagueId = storedProcedure.getOutputParameterInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }

        return (int)leagueId;
    };

    @Override
    public List<HashMap<String, Object>> getLeagues(String colName, String colValue) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getLeagues(?,?)");
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
    public void updateLeague(int leagueId, String leagueName){
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("updateLeague(?,?)");
            storedProcedure.setParameter(1,leagueId);
            storedProcedure.setParameter(2,leagueName);
            storedProcedure.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }
    };

    @Override
    public void deleteLeague(int leagueId){
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("deleteLeague(?)");
            storedProcedure.setParameter(1,leagueId);
            storedProcedure.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(storedProcedure != null){
                storedProcedure.cleanup();
            }
        }
    };

    @Override
    public List<HashMap<String, Object>> getLeagueConferences(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getLeagueConferences(?)");
            storedProcedure.setParameter(1, leagueId);
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
    public List<HashMap<String, Object>> getLeaguePlayers(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getLeaguePlayers(?)");
            storedProcedure.setParameter(1, leagueId);
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
    public List<HashMap<String, Object>> getLeagueFreeAgents(int leagueId) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = new StoredProcedure("getLeagueFreeAgents(?)");
            storedProcedure.setParameter(1, leagueId);
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
