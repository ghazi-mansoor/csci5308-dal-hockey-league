package com.groupten.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultSetOperation {
    public static List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) {
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        try{
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()) {
                HashMap<String,Object> row = new HashMap<String,Object>(columns);
                for(int i=1; i<=columns; i++) {
                    row.put(md.getColumnName(i),rs.getObject(i));
                }
                list.add(row);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
