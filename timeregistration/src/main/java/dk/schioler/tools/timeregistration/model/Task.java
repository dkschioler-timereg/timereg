package dk.schioler.tools.timeregistration.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dk.schioler.tools.timeregistration.TimeRegistrationException;

public class Task {

	private final Project project;
	private final String accentureCode;
	private final String taskName;

	private final List<Event> events = new ArrayList<Event>();

	public Task(Project project, String accentureCode, String task) {
		super();
		List<String> errors = new ArrayList<String>();
		if (project == null) {
			errors.add("project can not be null");
		}
		if (StringUtils.isBlank(task)) {
			errors.add("task can not be empty");
		}
		if (StringUtils.isBlank(accentureCode)) {
			errors.add("accentureCode can not be empty");
		}

		if (!errors.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			String and = "";
			for (String string : errors) {
				sb.append(and).append(string);
			}
			throw new TimeRegistrationException(sb.toString());
		}
		this.project = project;
		this.taskName = task;
		this.accentureCode = accentureCode;
		this.project.addTask(this);
	}

	public Project getProject() {
		return project;
	}

	public String getAccentureCode() {
		return accentureCode;
	}

	public String getTask() {
		return taskName;
	}

	public boolean addEvent(Event event) {
		boolean added = false;
		if (!events.contains(event)) {
			added = events.add(event);
		}

		return added;
	}

	public List<Event> getEvents() {
		return events;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accentureCode == null) ? 0 : accentureCode.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
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
		Task other = (Task) obj;
		if (accentureCode == null) {
			if (other.accentureCode != null)
				return false;
		} else if (!accentureCode.equals(other.accentureCode))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [project=" + project.getProjectName() + ", accentureCode=" + accentureCode + ", taskName=" + taskName + ", events=" + events + "]";
	}

}