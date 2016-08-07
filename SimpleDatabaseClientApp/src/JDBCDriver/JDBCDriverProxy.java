package JDBCDriver;

public class JDBCDriverProxy implements DatabaseDriver {

	private DatabaseDriver realDriver;
	
	public JDBCDriverProxy(DatabaseDriver jdbcDriver) {
		if(jdbcDriver != null) {
			this.realDriver = jdbcDriver;
		}
	}

	@Override
	public String getDatabaseDriver() { 
		return realDriver.getDatabaseDriver();
	}

}
