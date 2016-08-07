package JDBCDriver;

public class DB2JDBCDriver implements DatabaseDriver {

	private String classForName = "DB2 driver";

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
