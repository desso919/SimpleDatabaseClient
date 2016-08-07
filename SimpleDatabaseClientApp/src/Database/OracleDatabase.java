package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class OracleDatabase extends Database {

	@Override
	public Connection getConnection() {
		Map<DatabaseCredentials, String> databaseConnectionDetails = super.getDatabaseConnectionDetails();
		Connection databaseConnection = null;
		Properties info = new Properties();
		
		info.put("user", databaseConnectionDetails.get(DatabaseCredentials.USERNAME));
		info.put("password", databaseConnectionDetails.get(DatabaseCredentials.PASSWORD));

		String oracleURL = "jdbc:oracle:thin:@" + databaseConnectionDetails.get(DatabaseCredentials.DATABASE_HOST) + ":"
		+ databaseConnectionDetails.get(DatabaseCredentials.DATABASE_PORT) +":" 
		+ databaseConnectionDetails.get(DatabaseCredentials.DATABASE_NAME);
		System.out.println("JDBC URL is: " + oracleURL);

		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			}

		try {
			databaseConnection = DriverManager.getConnection(oracleURL, info);
		} catch (SQLException e) {
			System.out.println("Error while trying to connect: " + e.getMessage());
			e.printStackTrace();
		}
		return databaseConnection;
	}
}
