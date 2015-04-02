package dk.schioler.tools.timeregistration.statistics;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import dk.schioler.tools.timeregistration.TimeRegistrationPropertyKeys;
import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.model.Project;
import dk.schioler.tools.timeregistration.model.Task;
import dk.schioler.tools.timeregistration.model.Util;

@Component
public class EventStats {

	public static final EventCategoryProvider EVENTCATEGORYPROVIDER_ACNCODE = new EventCategoryProvider() {
		@Override
		public String getEventData(Event e) {
			return e.getTask().getAccentureCode();
		}
	};

	public static final EventCategoryProvider EVENTCATEGORYPROVIDER_TASK = new EventCategoryProvider() {
		@Override
		public String getEventData(Event e) {
			return e.getTask().getTask();
		}
	};

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Logger errorLogger = LoggerFactory.getLogger(TimeRegistrationPropertyKeys.ERROR_LOG);
	private final Logger statisticsLogger = LoggerFactory.getLogger(TimeRegistrationPropertyKeys.STATISTICS_LOG);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");

	@Value("${output.event-file}")
	private String eventsFile;

	public EventStats() {
		super();
	}

	public EventStats(String eventsFile) {
		super();
		this.eventsFile = eventsFile;
	}

	public Map<String, Project> getEventsInPeriod(Date start, Date end) throws IOException {
		logger.debug("getEventsInPeriod: " + start + " to " + end);

		Map<String, Project> projectsInPeriod = new TreeMap<String, Project>();

		List<String> readLines = FileUtils.readLines(new File(eventsFile));
		for (String string : readLines) {
			Util.parseAndAddToProjects(string, projectsInPeriod, start, end);
		}

		//		List<Event> allEvents = new ArrayList<Event>();
		//
		//		Set<Entry<String, Project>> projectEntries = projectsInPeriod.entrySet();
		//		for (Entry<String, Project> entry : projectEntries) {
		//			Project p = entry.getValue();
		//			Map<String, Task> tasks = p.getTasks();
		//			Set<Entry<String, Task>> taskEntries = tasks.entrySet();
		//			for (Entry<String, Task> entry2 : taskEntries) {
		//				Task t = entry2.getValue();
		//				List<Event> events = t.getEvents();
		//				allEvents.addAll(events);
		//			}
		//		}
		//
		//		Collections.sort(allEvents, new Comparator<Event>() {
		//			@Override
		//			public int compare(Event arg0, Event arg1) {
		//				long diff = arg0.getStart() - arg1.getStart();
		//				return diff > 0 ? 1 : -1;
		//			}
		//		});
		//
		//		StatsUtil.addEndTime(allEvents);

		return projectsInPeriod;
	}

	public List<Event> getAllEventsInDateOrder(Map<String, Project> projects) {
		return getAllEventsInDateOrder(projects, false);
	}

	public List<Event> getAllEventsInDateOrderAndAddEndtime(Map<String, Project> projects) {
		return getAllEventsInDateOrder(projects, true);
	}

	public List<Event> getAllEventsInDateOrder(Map<String, Project> projects, boolean addEndtime) {
		List<Event> allEvents = new ArrayList<Event>();

		Set<Entry<String, Project>> projectEntries = projects.entrySet();
		for (Entry<String, Project> entry : projectEntries) {
			Project p = entry.getValue();
			Map<String, Task> tasks = p.getTasks();
			Set<Entry<String, Task>> taskEntries = tasks.entrySet();
			for (Entry<String, Task> entry2 : taskEntries) {
				Task t = entry2.getValue();
				List<Event> events = t.getEvents();
				allEvents.addAll(events);
			}
		}

		Collections.sort(allEvents, new Comparator<Event>() {
			@Override
			public int compare(Event arg0, Event arg1) {
				long diff = arg0.getStart() - arg1.getStart();
				return diff > 0 ? 1 : -1;
			}
		});

		if (addEndtime) {
			StatsUtil.addEndTime(allEvents);
		}
		return allEvents;
	}

	//	public Map<String, Category> buildMap(List<Event> events, EventCategoryProvider dataprovider) {
	//		Map<String, Category> map = new TreeMap<String, Category>();
	//
	//		for (Event event : events) {
	//			if (!event.getTask().getTask().equals(Util.TASK_STOP.getTask())) {
	//				Category cat = map.get(dataprovider.getEventData(event));
	//				if (cat == null) {
	//					cat = new Category(dataprovider.getEventData(event));
	//					map.put(dataprovider.getEventData(event), cat);
	//				}
	//				cat.addEvent(event);
	//			}
	//		}
	//
	//		return map;
	//	}

	//	public void logDetails(Map<String, Category> events, EventCategoryProvider dataProvider) {
	//		long total = 0;
	//
	//		Set<Entry<String, Category>> entrySet = events.entrySet();
	//		for (Entry<String, Category> event : entrySet) {
	//			Category cat = event.getValue();
	//
	//			total = total + cat.getSummedDuration();
	//			List<EventInTransit> e = cat.getEvents();
	//			for (EventInTransit event2 : e) {
	//				statisticsLogger.info(cat.getCategory().trim() + TimeRegistrationProperties.SEPARATOR + sdf.format(event2.getTime()) + TimeRegistrationProperties.SEPARATOR
	//				      + event2.getDurationInMillis() + TimeRegistrationProperties.SEPARATOR + dataProvider.getEventData(event2));
	//
	//			}
	//			statisticsLogger.info("Sum: " + cat.getSummedDuration());
	//		}
	//		statisticsLogger.info("Total: " + total);
	//	}

	//	public void printDetails(Map<String, Category> events, EventCategoryProvider dataProvider, PrintStream printStream) {
	//		long total = 0;
	//
	//		Set<Entry<String, Category>> entrySet = events.entrySet();
	//		for (Entry<String, Category> event : entrySet) {
	//			printStream.format("%n");
	//			Category cat = event.getValue();
	//			printStream.format("%-18s          %n", cat.getCategory().trim());
	//
	//			total = total + cat.getSummedDuration();
	//			List<EventInTransit> e = cat.getEvents();
	//			for (EventInTransit event2 : e) {
	//				printStream.format("   %-15s %8d  %-40s%n", sdf.format(event2.getTime()), event2.getDurationInMillis(), dataProvider.getEventData(event2));
	//			}
	//			printStream.format("Sum:             %10d%n", cat.getSummedDuration());
	//		}
	//		printStream.format("Total: %20d%n", total);
	//	}

	//	public void printOverview(Map<String, Project> projects, PrintStream printStream) {
	//		printStream.format("%n");
	//		printStream.format("Overview: %n");
	//		long total = 0;
	//
	//		Set<Entry<String, Project>> entrySet = projects.entrySet();
	//		for (Entry<String, Project> event : entrySet) {
	//			Project cat = event.getValue();
	//			String c = cat.getProjectName().trim();
	//			int length = Math.min(60, c.length());
	//			printStream.format("%-60s   %10d%n", c.substring(0, length), cat.getSummedDuration());
	//			total = total + cat.getSummedDuration();
	//		}
	//		printStream.format("Total:                                               %20d%n", total);
	//	}

	//	public void printTimeUsageInPeriod(Date start, Date end, PrintStream printStream) throws IOException {
	//		//		Map<String, Category> usageOnAccCode = new TreeMap<String, TimeRegistrationFile.Category>();
	//
	//		List<Event> eventsInPeriod = getEventsInPeriod(start, end);
	//
	//		Collections.sort(eventsInPeriod, new Comparator<Event>() {
	//
	//			@Override
	//			public int compare(Event arg0, Event arg1) {
	//				return arg0.acnCode.compareTo(arg1.acnCode);
	//			}
	//		});
	//
	//		String curCode = "";
	//		long codeDuration = 0;
	//		for (Event event : eventsInPeriod) {
	//			if (StringUtils.isBlank(curCode)) {
	//				curCode = event.acnCode;
	//				codeDuration = event.durationInMillis;
	//				printStream.format("%n%8s%n", event.acnCode);
	//				printStream.format("%10s%10s%6d %16s%n", sdf.format(event.time), event.acnCode, event.durationInMillis, event.task);
	//			} else if (curCode.equals(event.acnCode)) {
	//				codeDuration = codeDuration + event.durationInMillis;
	//				printStream.format("%10s%10s%6d %16s%n", sdf.format(event.time), event.acnCode, event.durationInMillis, event.task);
	//			} else {
	//				printStream.format("%31d%n", codeDuration);
	//				printStream.format("%n%8s%n", event.acnCode);
	//				codeDuration = event.durationInMillis;
	//				curCode = event.acnCode;
	//				printStream.format("%10s%10s%6d %16s%n", sdf.format(event.time), event.acnCode, event.durationInMillis, event.task);
	//			}
	//		}
	//		printStream.format("%31d%n", codeDuration);
	//
	//		Collections.sort(eventsInPeriod, new Comparator<Event>() {
	//
	//			@Override
	//			public int compare(Event arg0, Event arg1) {
	//				return arg0.task.compareTo(arg1.task);
	//			}
	//		});
	//		curCode = null;
	//		codeDuration = 0;
	//		for (Event event : eventsInPeriod) {
	//			if (StringUtils.isBlank(curCode)) {
	//				curCode = event.task;
	//				codeDuration = event.durationInMillis;
	//				printStream.format("%n%8s%n", event.task);
	//				printStream.format("%10s %10s %6d %n", sdf.format(event.time), event.task, event.durationInMillis);
	//			} else if (curCode.equals(event.task)) {
	//				codeDuration = codeDuration + event.durationInMillis;
	//				printStream.format("%10s %10s %6d%n", sdf.format(event.time), event.task, event.durationInMillis);
	//			} else {
	//				printStream.format("%31d%n", codeDuration);
	//				printStream.format("%n%8s%n", event.task);
	//				codeDuration = event.durationInMillis;
	//				curCode = event.task;
	//				printStream.format("%10s %10s %6d%n", sdf.format(event.time), event.task, event.durationInMillis);
	//			}
	//		}
	//		printStream.format("%31d%n", codeDuration);
	//
	//		Map<String, Category> eventsOnAcnCode = new TreeMap<String, Category>();
	//		Map<String, Category> eventsOnProjectTask = new TreeMap<String, Category>();
	//
	//		Date regStart = null;
	//		Date refStop = null;
	//		for (Event event : eventsInPeriod) {
	//			if (regStart == null && refStop == null) {
	//				regStart = event.time;
	//				refStop = event.time;
	//			} else {
	//				if (regStart.getTime() > event.time.getTime()) {
	//					regStart = event.time;
	//				}
	//				if (refStop.getTime() < event.time.getTime()) {
	//					refStop = event.time;
	//				}
	//			}
	//
	//			Category acnCat = eventsOnAcnCode.get(event.acnCode);
	//			if (acnCat == null) {
	//				acnCat = new Category(event.acnCode);
	//				eventsOnAcnCode.put(event.acnCode, acnCat);
	//			}
	//			acnCat.addEvent(event);
	//
	//			Category taskCat = eventsOnProjectTask.get(event.task);
	//			if (taskCat == null) {
	//				taskCat = new Category(event.task);
	//				eventsOnProjectTask.put(event.task, taskCat);
	//			}
	//			taskCat.addEvent(event);
	//		}
	//
	//		//		printStream.format("%10s%10s%10d %16s%n",
	//		//		StringBuffer rv = new StringBuffer();
	//		printStream.format("%30s%n", "**********************************");
	//		printStream.format("%15s %16s%n", "Period Start", sdf.format(regStart));
	//		printStream.format("%15s %16s%n", "Period End", sdf.format(refStop));
	//		printStream.format("%30s%n", "**********************************");
	//
	//		Set<Entry<String, Category>> entrySet = eventsOnProjectTask.entrySet();
	//
	//		for (Entry<String, Category> entry : entrySet) {
	//			long summedDuraction = entry.getValue().getSummedDuraction();
	//			printStream.format("%10d  %30s%n", summedDuraction, entry.getValue().category.trim());
	//		}
	//
	//		printStream.format("%30s%n", "**********************************");
	//
	//		Set<Entry<String, Category>> acnCodeEntries = eventsOnAcnCode.entrySet();
	//
	//		for (Entry<String, Category> entry : acnCodeEntries) {
	//			long summedDuraction = entry.getValue().getSummedDuraction();
	//			printStream.format("%15s %10d %n", entry.getValue().category.trim(), summedDuraction);
	//		}
	//
	//		//		rv.append("**********************************").append(System.lineSeparator());
	//
	//		//		return rv.toString();
	//	}

	/*
	 * An event is the start of an activity.
	 * Therefor the duration of the event must be calculated when the next event occurs.
	 */
	//	public void addDuration(List<EventInTransit> eventsInPeriod) {
	//
	//		EventInTransit startEvent = null;
	//		//		Event stopEvent = null;
	//
	//		EventInTransit previous = null;
	//
	//		for (EventInTransit event : eventsInPeriod) {
	//			logger.debug("looking at event=" + event);
	//			if (event.getProject().equals(Project.START_REGISTRATION.getProjectName())) {
	//				if (previous == null) {
	//					startEvent = event;
	//					previous = event;
	//				} else {
	//					// We have dangling entries...
	//					// not even started a period, but previous and current has been set... hmm...
	//					throw new TimeRegistrationException("found Start event, but previous or current are set...");
	//				}
	//			} else if (event.getProject().equals(Project.STOP_REGISTRATION.getProjectName())) {
	//				EventInTransit stop = event;
	//				if (startEvent != null) {
	//					if (previous != null) {
	//						previous.setDurationInMillis(stop.getTime().getTime() - previous.getTime().getTime());
	//						startEvent = null;
	//						previous = null;
	//					} else {
	//						throw new TimeRegistrationException("found STOP event but no previous entry");
	//					}
	//				} else {
	//					throw new TimeRegistrationException("found STOP event, but no START ");
	//				}
	//			} else {
	//				if (previous != null) {
	//					previous.setDurationInMillis(event.getTime().getTime() - previous.getTime().getTime());
	//					logger.debug("settinmg duration=" + previous.getDurationInMillis());
	//					previous = event;
	//				} else {
	//					throw new TimeRegistrationException("can not calculate duration of an event, with no event preceeding");
	//				}
	//			}
	//
	//		}
	//
	//	}

}
