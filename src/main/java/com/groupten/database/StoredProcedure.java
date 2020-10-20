package com.groupten.database;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public class StoredProcedure
{
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	
	public StoredProcedure(String storedProcedureName) throws SQLException
	{
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}
	
	private void createStatement() throws SQLException
	{
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}
	
	private void openConnection() throws SQLException
	{
		connection = ConnectionManager.instance().getDBConnection();
	}
	
	public void cleanup()
	{
		try
		{
			statement = ConnectionManager.instance().getSafeCallableStatement(statement);
			statement.close();
			connection = ConnectionManager.instance().getSafeConnection(connection);
			if (connection.isClosed() == false)
			{
				connection.close();
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Failed to perform DB cleanup:" + e.getMessage());
		}
	}
	
	public void setParameter(int paramIndex, String value) throws SQLException
	{
		statement.setString(paramIndex, value);
	}
	
	public void registerOutputParameterString(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, Types.VARCHAR);
	}
	
	public void setParameter(int paramIndex, long value) throws SQLException
	{
		statement.setLong(paramIndex, value);
	}
	
	public void registerOutputParameterLong(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, Types.BIGINT);
	}
	
	public ArrayList<HashMap<String,Object>> executeWithResults() throws SQLException
	{
		ArrayList<HashMap<String,Object>> results;
		if (statement.execute())
		{
			results = convertResultSetToList(statement.getResultSet());
		}
		else
		{
			results = new ArrayList<HashMap<String,Object>>();
		}
		return results;
	}
	
	public void execute() throws SQLException
	{
		statement.execute();
	}

	private ArrayList<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException
    {
	    ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
    	ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        while (rs.next())
        {
            HashMap<String,Object> row = new HashMap<String,Object>(columns);
            for (int i = 1; i <= columns; i++)
            {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
	    return list;
    }
}
