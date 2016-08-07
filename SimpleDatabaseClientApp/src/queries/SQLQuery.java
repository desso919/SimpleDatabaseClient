package queries;

public class SQLQuery {

	private String queryName;
	private String query;
	private double AvarageExecutionTime;
	
	public SQLQuery(String queryName, String query, double avarageExecutionTime) {
		this.queryName = queryName;
		this.query = query;
		AvarageExecutionTime = avarageExecutionTime;
	}
	
	public SQLQuery(String queryName, String query) {
		this.queryName = queryName;
		this.query = query;
		AvarageExecutionTime = 0.0f;
	}
	
	public SQLQuery(){}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public double getAvarageExecutionTime() {
		return AvarageExecutionTime;
	}

	public void setAvarageExecutionTime(double avarageExecutionTime) {
		AvarageExecutionTime = avarageExecutionTime;
	}	
}
