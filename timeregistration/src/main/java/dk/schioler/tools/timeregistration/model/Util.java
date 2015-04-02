package dk.schioler.tools.timeregistration.model;

import java.util.Date;
import java.util.Map;

import dk.schioler.tools.timeregistration.TimeRegistrationException;
import dk.schioler.tools.timeregistration.TimeRegistrationPropertyKeys;

public class Util {

	public static final Project INTERNAL = new Project("ToolExecution");

	public static final Task TASK_START;
	public static final Task TASK_STOP;

	static {
		TASK_START = new Task(INTERNAL, "START", "START");
		TASK_STOP = new Task(INTERNAL, "STOP", "STOP");
	}

	private static final int dateIdx = 0;
	private static final int projectIdx = 1;
	private static final int taskIdx = 2;
	private static final int acnCodeIdx = 3;

	public static String serialize(Event event) {
		StringBuilder sb = new StringBuilder();
		sb.append(TimeRegistrationPropertyKeys.sdf.format(new Date(event.getStart()))).append(TimeRegistrationPropertyKeys.SEPARATOR);
		sb.append(event.getTask().getProject().getProjectName()).append(TimeRegistrationPropertyKeys.SEPARATOR);
		sb.append(event.getTask().getTask()).append(TimeRegistrationPropertyKeys.SEPARATOR);
		sb.append(event.getTask().getAccentureCode());
		return sb.toString();
	}

	public static void parseAndAddToProjects(String string, Map<String, Project> projectMap, Date start, Date end) {
		try {
			String[] split = string.split(TimeRegistrationPropertyKeys.SEPARATOR);
			Date time = TimeRegistrationPropertyKeys.sdf.parse(split[dateIdx]);
			if (start != null && end != null) {
				if (start.getTime() <= time.getTime() && end.getTime() > time.getTime()) {
					addEventToProjectTask(time, split, projectMap);
				}
			} else {
				addEventToProjectTask(time, split, projectMap);
			}

		} catch (Exception e) {
			throw new TimeRegistrationException(e.getMessage(), e);
		}
	}

	public static void addEventToProjectTask(Date eventTime, String[] data, Map<String, Project> projectMap) {
		String project = data[projectIdx];
		String task = data[taskIdx];
		String acnCode = data[acnCodeIdx];

		Project project2 = projectMap.get(project);
		if (project2 == null) {
			project2 = new Project(project);
			// Parent is added implicitly
			Task t = new Task(project2, acnCode, task);
			new Event(t, eventTime.getTime());
			projectMap.put(project2.getProjectName(), project2);
		} else {
			Task task2 = project2.getTask(task);
			if (task2 == null) {
				// Parent is added implicitly
				task2 = new Task(project2, acnCode, task);
				new Event(task2, eventTime.getTime());
			} else {
				new Event(task2, eventTime.getTime());
			}
		}
	}
}
