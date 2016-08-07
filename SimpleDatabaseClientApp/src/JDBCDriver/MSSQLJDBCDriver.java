package JDBCDriver;

public class MSSQLJDBCDriver implements DatabaseDriver {

	private String classForName = "MS SQL driver";

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
