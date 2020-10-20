package com.groupten.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton for retrieving connections.
public class ConnectionManager
{
	private static ConnectionManager uniqueInstance = null;
	
	private String dbURL;
	private String dbUserName;
	private String dbPassword;
	private Connection nullConnection;
	private CallableStatement nullStatement;
	
	private ConnectionManager()
	{
		nullConnection = new NullConnection();
		nullStatement = new NullStatement();
	}
	
	public static ConnectionManager instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new ConnectionManager();
		}
		return uniqueInstance;
	}

	public void configure(IDatabaseConfiguration config)
	{
		dbURL = config.getDatabaseURL();
		dbUserName = config.getDatabaseUserName();
		dbPassword = config.getDatabasePassword();
	}
	
	public Connection getDBConnection() throws SQLException
	{
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}
	
	public Connection getSafeConnection(Connection connection)
	{
		if (connection == null)
		{
			return nullConnection;
		}
		return connection;
	}
	
	public CallableStatement getSafeCallableStatement(CallableStatement statement)
	{
		if (statement == null)
		{
			return nullStatement;
		}
		return statement;
	}
}
