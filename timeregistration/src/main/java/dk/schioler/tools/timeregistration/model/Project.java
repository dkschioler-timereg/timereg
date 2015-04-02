package dk.schioler.tools.timeregistration.model;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import dk.schioler.tools.timeregistration.TimeRegistrationException;

public class Project {
	private final Map<String, Task> tasks = new TreeMap<String, Task>();

	private final String projectName;

	public Project(String project) {
		if (StringUtils.isNotBlank(project)) {
			this.projectName = project;
		} else {
			throw new TimeRegistrationException("project may not be empty");
		}
	}

	public Map<String, Task> getTasks() {
		return tasks;
	}

	public Task getTask(String taskName) {
		return tasks.get(taskName);
	}

	public void addTask(Task task) {
		if (task != null) {
			tasks.put(task.getTask(), task);
		}
	}

	public String getProjectName() {
		return projectName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;		
		return true;
	}

	@Override
	public String toString() {
		return "Project [project=" + projectName + " tasks=  " + tasks + "]";
	}
}
