package JDBCDriver;

public class OracleJDBCDriver implements DatabaseDriver {

	private String classForName = "Oracle driver";

	public String getClassForName() {
		return classForName;
	}

	public void setClassForName(String classForName) {
		this.classForName = classForName;
	} 
	
	@Override
	public String getDatabaseDriver() {
		return getClassForName();
	}
}
