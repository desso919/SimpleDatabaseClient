package com.sap.idm.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import Database.DatabaseCredentials;

public class TestConnection {

	public static boolean testConnectionToDatabase(Map<DatabaseCredentials, String> databaseCredentials) {
		Properties info = new Properties();
		Connection connection = null;

		info.put("user", databaseCredentials.get(DatabaseCredentials.USERNAME));
		info.put("password", databaseCredentials.get(DatabaseCredentials.PASSWORD));

		String connectionString = "jdbc:sybase:Tds:" + databaseCredentials.get(DatabaseCredentials.DATABASE_HOST) + ":"
				+ databaseCredentials.get(DatabaseCredentials.DATABASE_PORT) + "/"
				+ databaseCredentials.get(DatabaseCredentials.DATABASE_NAME);

		try {
			connection = DriverManager.getConnection(connectionString, info);
			if (connection.isValid(2)) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error while trying to connect: " + e.getMessage());
		}

		return false;
	}
}
