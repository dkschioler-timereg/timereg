package dk.schioler.tools.timeregistration.configuration;

import java.util.Map;

import dk.schioler.tools.timeregistration.model.Project;

public interface TaskProvider {
	public Map<String, Project> getProjects();
}
