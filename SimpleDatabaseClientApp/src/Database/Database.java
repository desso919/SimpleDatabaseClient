package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class Database {

	private Map<DatabaseCredentials, String> databaseConnectionDetails;
	private long executionTime = 0;

	public void setDatabaseConnectionDetails(Map<DatabaseCredentials, String> databaseConnectionDetails) {
		this.databaseConnectionDetails = databaseConnectionDetails;
	}
	
	protected Map<DatabaseCredentials, String> getDatabaseConnectionDetails() {
		return databaseConnectionDetails;
	}

	public long getExecutionTime() {
		return executionTime;
	}
	
	private PreparedStatement getPrepareStatement(String sqlQuery) throws SQLException{
			return getConnection().prepareStatement(sqlQuery);
	}
	
	private PreparedStatement bindParametarsToPrepareStatement(String sqlQuery, List<Object> params) {		
		PreparedStatement preparedStatement = null;
		int parametarIndex = 1;
		
		try {
			preparedStatement = getPrepareStatement(sqlQuery);
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

	public ResultSet execute(String sqlQuery) {
		ResultSet rs = null;
		
		try {
			rs = getPrepareStatement(sqlQuery).executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Database connection error while trying to execute the query!");
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) { }
		}
	}
	
	public ResultSet execute(String sqlQuery, List<Object> params) {
		ResultSet rs = null;
		
		try {
			rs = bindParametarsToPrepareStatement(sqlQuery, params).executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Database connection error while trying to execute the query!");
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) { }
		}
	}
	

	public ResultSet executeAndTimeQuery(String sqlQuery) {
		ResultSet rs = null;
		long start = 0, end = 0;
		start = System.currentTimeMillis();
		
		try {
			rs = getPrepareStatement(sqlQuery).executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Database connection error while trying to execute the query!");
			return null;
		} finally {
			end = System.currentTimeMillis();
			executionTime = end - start;
			try {
				rs.close();
			} catch (SQLException e) { }
		}	
	}
	
	public ResultSet executeAndTimeQuery(String sqlQuery, List<Object> params) {
		ResultSet rs = null;
		long start = 0, end = 0;
		start = System.currentTimeMillis();
		
		try {
			rs = bindParametarsToPrepareStatement(sqlQuery, params).executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Database connection error while trying to execute the query!");
			return null;
		} finally {
			end = System.currentTimeMillis();
			executionTime = end - start;
			try {
				rs.close();
			} catch (SQLException e) { }
		}	
	}
	
	public boolean testConnection() {
		if(getConnection() != null) {
			return true;
		}
		return false;
	}

	public abstract Connection getConnection();
}
