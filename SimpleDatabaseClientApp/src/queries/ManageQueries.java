package queries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ManageQueries implements IManageQueries {

	private UserQueries userQueries;
	
	@Override
	public boolean loadQueries() {
		List<SQLQuery> queries = new ArrayList<SQLQuery>();
        String fileName = "queries.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	List<String> queryAttributes = Arrays.asList(line.split(","));
            	SQLQuery query = new SQLQuery();
            	query.setQueryName(queryAttributes.get(0)); 
            	query.setQuery(queryAttributes.get(1)); 
            	query.setAvarageExecutionTime(Double.valueOf(queryAttributes.get(2))); 

            	queries.add(query);
            }   
            
            userQueries = new UserQueries(queries);
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
        
		return false;
	}

	@Override
	public void saveQueries() {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		
		File fout = new File("queries.txt");
		List<SQLQuery> queries = getQueries();
		
		try {
			fos = new FileOutputStream(fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (Iterator<SQLQuery> iterator = queries.iterator(); iterator.hasNext();) {
				SQLQuery query = iterator.next();
				bw.write(query.getQueryName());
				bw.write(", ");
				bw.write(query.getQuery());
				bw.write(", ");
				bw.write(String.valueOf(query.getAvarageExecutionTime()));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null && fos != null) {
				try {
					bw.close();
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	@Override
	public boolean addQuerye(SQLQuery query) {
		return userQueries.addQuery(query);
	}

	@Override
	public boolean deleteQuery(SQLQuery query) {
		return userQueries.deleteQuery(query);
	}

	@Override
	public boolean containsQuery(SQLQuery query) {
		return userQueries.containsQuery(query);
	}

	@Override
	public SQLQuery getQuery(String query) {
		return userQueries.getQuery(query);
	}

	@Override
	public List<String> getQueriesNames() {
		return userQueries.getQueriesNames();
	}

	@Override
	public List<SQLQuery> getQueries() {
		return userQueries.getAllQueries();
	}
}
