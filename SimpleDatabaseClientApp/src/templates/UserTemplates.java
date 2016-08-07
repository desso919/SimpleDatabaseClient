package templates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserTemplates {

	private List<Template> templates = new ArrayList<Template>();

	public UserTemplates(List<Template> templates) {
		this.templates.addAll(templates);
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	public boolean addTemplate(Template template) {
		return this.templates.add(template);
	}

	public boolean deleteTemplate(Template template) {
		return this.templates.remove(template);
	}

	public boolean containsTemplate(Template template) {
		return templates.contains(template);
	}

	public Template getTemplate(Template template) {
		if (template != null && template.getTemplateName() != null) {
			for (Iterator<Template> iterator = templates.iterator(); iterator.hasNext();) {
				Template templ = (Template) iterator.next();
				if (templ.getTemplateName().equalsIgnoreCase(template.getTemplateName())) {
					return templ;
				}
			}
		}
		return null;
	}
	
	public Template getTemplateByName(String templateName) {
		if (templateName != null && !templateName.equals("")) {
			for (Iterator<Template> iterator = templates.iterator(); iterator.hasNext();) {
				Template template = (Template) iterator.next();
				if (templateName.equalsIgnoreCase(template.getTemplateName())) {
					return template;
				}
			}
		}
		return null;
	}

	public List<String> getTemplatesNames() {
		List<String> templatesNames = new ArrayList<String>();
		
		for (Iterator<Template> iterator = templates.iterator(); iterator.hasNext();) {
			Template template = (Template) iterator.next();
			templatesNames.add(template.getTemplateName());
		}
		
		return templatesNames;
	}
}
