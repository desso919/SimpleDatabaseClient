package com.sap.idm.main;

import java.util.HashMap;
import java.util.Map;

import Database.DatabaseCredentials;

public class DatabaseConnectionDetails {

	private static Map<DatabaseCredentials, String> databaseCredentials = new HashMap<DatabaseCredentials, String>();
	
	public static Map<DatabaseCredentials, String> getDatabaseConnectionDetails() {
		return databaseCredentials;
	}
	
	public static void setDatabaseConnectionDetails(Map<DatabaseCredentials, String> databaseCredentials1) {
		 databaseCredentials = databaseCredentials1;
	}
	
}
