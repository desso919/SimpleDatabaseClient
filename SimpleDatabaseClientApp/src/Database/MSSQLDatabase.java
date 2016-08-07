package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class MSSQLDatabase extends Database {

	@Override
	public Connection getConnection() {
		Map<DatabaseCredentials, String> databaseConnectionDetails = super.getDatabaseConnectionDetails();
		Connection databaseConnection = null;
		Properties info = new Properties();

		info.put("user", databaseConnectionDetails.get(DatabaseCredentials.USERNAME));
		info.put("password", databaseConnectionDetails.get(DatabaseCredentials.PASSWORD));

		String ceonnectionString = "jdbc:sqlserver://"
				+ databaseConnectionDetails.get(DatabaseCredentials.DATABASE_HOST) + ":"
				+ databaseConnectionDetails.get(DatabaseCredentials.DATABASE_PORT) + ";databasename="
				+ databaseConnectionDetails.get(DatabaseCredentials.DATABASE_NAME) + ";user="
				+ databaseConnectionDetails.get(DatabaseCredentials.USERNAME) + ";password="
				+ databaseConnectionDetails.get(DatabaseCredentials.PASSWORD);

		System.out.println("URL is: --------------> " + ceonnectionString);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}

		try {
			databaseConnection = DriverManager.getConnection(ceonnectionString);
		} catch (SQLException e) {
			System.out.println("Error while trying to connect: " + e.getMessage());
			e.printStackTrace();
		}

		return databaseConnection;
	}

}
