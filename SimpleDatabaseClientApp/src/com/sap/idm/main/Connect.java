package com.sap.idm.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import Database.DatabaseCredentials;

public class Connect {
	
	private long executionTime = 0;

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long newExecutionTime) {
		executionTime = newExecutionTime;
	}
	
	public boolean execute(Map<DatabaseCredentials, String> userInput, String query, List<Object> params) {		
		return connect(userInput, query, params);
	}
	
	public boolean connect(Map<DatabaseCredentials, String> userInput, String query, List<Object> params) {
		PreparedStatement preparedStatement = null;
		
		Connection dbConnection = ConnectToDatabase(userInput);	
		
		try {
			
			if(params.isEmpty()) {
			    preparedStatement = bindParametars(dbConnection, params);
			} else {
				preparedStatement = dbConnection.prepareStatement(query);
			}
			
			ResultSet resultSet = executeAndTimeQuery(preparedStatement, query);
		} catch (SQLException e) {
			System.out.println("Error while trying to execute the query: " + e.getMessage());
			return false;
		}
		return true;
	}

	private PreparedStatement bindParametars(Connection connection, List<Object> params) {
		PreparedStatement preparedStatement = null;
		int parametarIndex = 1;

		try {
			preparedStatement = connection.prepareStatement(Queries.TEST_QUERY);
			for (Iterator<Object> iterator = params.iterator(); iterator.hasNext();) {			
				Object parametar = iterator.next();
				
				if(parametar instanceof Integer) {
					preparedStatement.setInt(parametarIndex, Integer.valueOf((String) parametar));
				}
				else if(parametar instanceof String) {
					preparedStatement.setString(parametarIndex, (String) parametar);
				}
				
				parametarIndex++;
			}
		} catch (SQLException e) {
			System.out.println("Error while trying to bind parameters to the query: " + e.getMessage());
		}
		
		return preparedStatement;
	}

	private Connection ConnectToDatabase(Map<DatabaseCredentials, String> databaseCredentials) {
		Properties info = new Properties();
		Connection c = null;

		info.put("user", databaseCredentials.get(DatabaseCredentials.USERNAME));
		info.put("password", databaseCredentials.get(DatabaseCredentials.PASSWORD));

		String connectionString = "jdbc:sybase:Tds:" + databaseCredentials.get(DatabaseCredentials.DATABASE_HOST) + ":"
				+ databaseCredentials.get(DatabaseCredentials.DATABASE_PORT) + "/"
				+ databaseCredentials.get(DatabaseCredentials.DATABASE_NAME);
		try {
			c = DriverManager.getConnection(connectionString, info);
		} catch (SQLException e) {
			System.out.println("Error while trying to connect: " + e.getMessage());
		}
		
		return c;
	}

	public ResultSet executeAndTimeQuery(PreparedStatement ps, String sql) throws SQLException {
		long start = 0, end = 0;
		start = System.currentTimeMillis();
		try {
			ResultSet rs = ps.executeQuery();
			return rs;
		} finally {
			end = System.currentTimeMillis();
			setExecutionTime(end - start);			
		}
	}
}
