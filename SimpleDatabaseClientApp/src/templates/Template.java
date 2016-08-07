package templates;

public class Template {
	private String templateName;
	private String host;
	private String port;
	private String databseName;
	private String username;
	private String password;
	private String databaseType;

	public Template(String templateName, String host, String port, String databseName, String username, String password, String databaseType) {
		this.templateName = templateName;
		this.host = host;
		this.port = port;
		this.databseName = databseName;
		this.username = username;
		this.password = password;
		this.databaseType = databaseType;
	}
	
	public Template() {}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabseName() {
		return databseName;
	}

	public void setDatabseName(String databseName) {
		this.databseName = databseName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String msSql) {
		this.databaseType = msSql;
	}

	@Override
	public String toString() {
		return "Template [templateName=" + templateName + ", host=" + host + ", port=" + port + ", databseName="
				+ databseName + ", username=" + username + ", password=" + password + ", databaseType=" + databaseType
				+ "]";
	}
}
