package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class ASEDatabase extends Database {

	@Override
	public Connection getConnection() {
		Map<DatabaseCredentials, String> databaseConnectionDetails = super.getDatabaseConnectionDetails();
		Connection databaseConnection = null;
		Properties info = new Properties();

		info.put("user", databaseConnectionDetails.get(DatabaseCredentials.USERNAME));
		info.put("password", databaseConnectionDetails.get(DatabaseCredentials.PASSWORD));

		String connectionString = "jdbc:sybase:Tds:" + databaseConnectionDetails.get(DatabaseCredentials.DATABASE_HOST) + ":"
				+ databaseConnectionDetails.get(DatabaseCredentials.DATABASE_PORT) + "/"
				+ databaseConnectionDetails.get(DatabaseCredentials.DATABASE_NAME);
		try {
			databaseConnection = DriverManager.getConnection(connectionString, info);
		} catch (SQLException e) {
			System.out.println("Error while trying to connect: " + e.getMessage());
		}
		
		return databaseConnection;
	}
}
