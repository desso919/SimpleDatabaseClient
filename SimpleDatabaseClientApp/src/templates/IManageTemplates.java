package templates;

import java.util.List;

public interface IManageTemplates {
	
	public boolean loadTemplates();	
	public void saveTemplates();	
	public boolean addTemplate(Template template);	
	public boolean deleteTemplate(Template template);	
	public boolean containsTemplate(Template template);
	public Template getTemplate(Template template);
	public Template getTemplateByName(String templateName);
	public List<String> getTemplatesNames();
	public List<Template> getTemplates();
	
}
