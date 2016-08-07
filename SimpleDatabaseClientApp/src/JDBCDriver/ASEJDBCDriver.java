package JDBCDriver;

public class ASEJDBCDriver implements DatabaseDriver {

	private String classForName = "ASE driver";

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
