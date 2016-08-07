package queries;

import java.util.List;

public interface IManageQueries {

	public boolean loadQueries();	
	public void saveQueries();	
	public boolean addQuerye(SQLQuery query);	
	public boolean deleteQuery(SQLQuery query);	
	public boolean containsQuery(SQLQuery query);
	public SQLQuery getQuery(String query);
	public List<String> getQueriesNames();
	public List<SQLQuery> getQueries();
}
