package templates;

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

public class ManageTemplates implements IManageTemplates {

	private UserTemplates userTemplates;

	@Override
	public boolean loadTemplates() {
		List<Template> templates = new ArrayList<Template>();
        String fileName = "templates.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	List<String> templateAttributes = Arrays.asList(line.split(","));
            	Template template = new Template();
            	template.setTemplateName(templateAttributes.get(0));
            	template.setHost(templateAttributes.get(1));
            	template.setPort(templateAttributes.get(2));
            	template.setDatabseName(templateAttributes.get(3));
            	template.setUsername(templateAttributes.get(4));
            	template.setPassword(templateAttributes.get(5));
            	template.setDatabaseType(templateAttributes.get(6));
            	templates.add(template);
            }   
            
            userTemplates = new UserTemplates(templates);
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
	public void saveTemplates() {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		
		File fout = new File("templates.txt");
		List<Template> templates = getTemplates();
		
		try {
			fos = new FileOutputStream(fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (Iterator<Template> iterator = templates.iterator(); iterator.hasNext();) {
				Template template = (Template) iterator.next();
				bw.write(template.getTemplateName());
				bw.write(", ");
				bw.write(template.getHost());
				bw.write(", ");
				bw.write(template.getPort());
				bw.write(", ");
				bw.write(template.getDatabseName());
				bw.write(", ");
				bw.write(template.getUsername());
				bw.write(", ");
				bw.write(template.getPassword());
				bw.write(", ");
				bw.write(template.getDatabaseType());
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
	public boolean addTemplate(Template template) {
		return userTemplates.addTemplate(template);
	}
	
	@Override
	public List<Template> getTemplates() {
		return userTemplates.getTemplates();
	}

	@Override
	public boolean containsTemplate(Template template) {
		return userTemplates.containsTemplate(template);
	}

	@Override
	public Template getTemplate(Template template) {
		return userTemplates.getTemplate(template);
	}

	@Override
	public boolean deleteTemplate(Template template) {
		return userTemplates.deleteTemplate(template);
	}

	@Override
	public List<String> getTemplatesNames() {
		return userTemplates.getTemplatesNames();
	}

	@Override
	public Template getTemplateByName(String templateName) {
		return userTemplates.getTemplateByName(templateName);
	}
}
