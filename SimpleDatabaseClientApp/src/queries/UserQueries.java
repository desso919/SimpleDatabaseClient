package queries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserQueries {

	private List<SQLQuery> queries = new ArrayList<SQLQuery>();

	public UserQueries(List<SQLQuery> queries) {
		this.queries.addAll(queries);
	}

	public List<SQLQuery> getAllQueries() {
		return queries;
	}

	public void setQueries(List<SQLQuery> query) {
		this.queries = query;
	}

	public boolean addQuery(SQLQuery query) {
		return this.queries.add(query);
	}

	public boolean deleteQuery(SQLQuery query) {
		return this.queries.remove(query);
	}

	public boolean containsQuery(SQLQuery query) {
		return queries.contains(query);
	}

	public SQLQuery getQuery(String query) {
		if (query != null && !query.equals("")) {
			for (Iterator<SQLQuery> iterator = queries.iterator(); iterator.hasNext();) {
				SQLQuery tempQuery = iterator.next();
				if (tempQuery.getQueryName().equalsIgnoreCase(query)) {
					return tempQuery;
				}
			}
		}
		return null;
	}

	public List<String> getQueriesNames() {
		List<String> queriesName = new ArrayList<String>();
		
		for (Iterator<SQLQuery> iterator = queries.iterator(); iterator.hasNext();) {
			SQLQuery query = (SQLQuery) iterator.next();
			queriesName.add(query.getQueryName());
		}
		return queriesName;
	}
}
