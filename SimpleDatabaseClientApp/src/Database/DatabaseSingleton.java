package Database;

public final class DatabaseSingleton {

	private static Database database = null;

	private DatabaseSingleton() {}

	public static synchronized Database getInstance() {
		if(database == null) {
		  System.out.println("There is no instance of database!");	
		}
		return database;
	}

	public static synchronized void setDatabase(DatabaseType dbType) {
		if(database == null) {
			if (dbType == DatabaseType.MS_SQL) {
				database = new MSSQLDatabase();
			}
			if (dbType == DatabaseType.ORACLE) {
				database = new OracleDatabase();
			}
			if (dbType == DatabaseType.ASE) {
				database = new ASEDatabase();
			}
			if (dbType == DatabaseType.DB2) {
				database = new DB2Database();
			}
		} else {
			System.out.println("Database already set.");
		}
	}

}
