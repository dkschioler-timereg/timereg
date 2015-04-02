package dk.schioler.tools.timeregistration.input;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jline.AnsiWindowsTerminal;
import jline.console.ConsoleReader;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import dk.schioler.tools.timeregistration.TimeRegistrationException;
import dk.schioler.tools.timeregistration.TimeRegistrationPropertyKeys;
import dk.schioler.tools.timeregistration.configuration.Configuration;
import dk.schioler.tools.timeregistration.input.menu.Menu;
import dk.schioler.tools.timeregistration.input.menu.MenuCategory;
import dk.schioler.tools.timeregistration.input.menu.MenuItem;
import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.model.Project;
import dk.schioler.tools.timeregistration.model.Task;
import dk.schioler.tools.timeregistration.model.Util;
import dk.schioler.tools.timeregistration.statistics.EventStats;

@Component
public class ConsoleMenuRunner {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MessageChannel inputChannel;

	@Autowired
	private Configuration configuration;

	@Autowired
	private EventStats eventStats;

	private Menu menu;

	private DateTime programExecutionStart;
	private Task activeTask;
	private Date taskStartTime;

	public void run() {
		this.programExecutionStart = new DateTime();
		SimpleDateFormat hourAndMinutes = new SimpleDateFormat("HH:mm");
		try {
			activeTask = Util.TASK_START;
			taskStartTime = new Date();
			sendEvent();

			doMenu();
			String line = null;
			while (true) {
				printMenu();
				line = reader.readLine();
				logger.debug("readInput=" + line);
				if (!StringUtils.isBlank(line)) {
					String trim = line.trim();
					String[] split = trim.split(" ");
					logger.debug("input=" + Arrays.toString(split));
					if (split.length == 1) {
						String input = split[0].trim();
						if ("-1".equals(input)) {
							break;
						} else if ("e".equals(input)) {
							printStats(programExecutionStart, new DateTime());

						} else {
							Task task = menu.getTask(input);
							if (task != null) {
								activeTask = task;
								taskStartTime = new Date();
								sendEvent();
							}
						}
					} else if (split.length == 2) {
						Task task = menu.getTask(split[0].trim());
						Date parse = hourAndMinutes.parse(split[1].trim());
						DateTime iTime = new DateTime(parse);
						logger.debug("time={}", iTime);
						DateTime target = new DateTime().withHourOfDay(iTime.getHourOfDay()).withMinuteOfHour(iTime.getMinuteOfHour())
						      .withSecondOfMinute(iTime.getSecondOfMinute());
						logger.debug("input: " + target);

						if (task != null) {
							activeTask = task;
							taskStartTime = target.toDate();
							sendEvent();
						}
					}

					line = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			activeTask = Util.TASK_STOP;
			taskStartTime = new Date();
			sendEvent();
		}
	}

	private void sendEvent() {
		inputChannel.send(new GenericMessage<String>(Util.serialize(new Event(activeTask, taskStartTime.getTime()))));
	}

	private void printStats(DateTime start, DateTime end) throws IOException {
		start = start.withField(DateTimeFieldType.minuteOfHour(), 00).withField(DateTimeFieldType.secondOfMinute(), 00);
		end = end.withField(DateTimeFieldType.minuteOfHour(), 59).withField(DateTimeFieldType.secondOfMinute(), 59);

		Map<String, Project> projectsInPeriod = eventStats.getEventsInPeriod(start.toDate(), end.toDate());

		List<Event> allEventsInDateOrder = eventStats.getAllEventsInDateOrder(projectsInPeriod);
		for (Event event : allEventsInDateOrder) {
			reader.println(event.toString());
		}
		reader.readLine();
	}

	ConsoleReader reader = null;

	public ConsoleMenuRunner() {
		super();
		try {
			AnsiWindowsTerminal ts = new AnsiWindowsTerminal();

			//			System.out.println("isSuppoerted=" + ts.isSupported());
			ts.setEchoEnabled(false);

			//			PrintStream ps = new PrintStream(System.out);
			reader = new ConsoleReader(System.in, System.out, ts);

			reader.setBellEnabled(true);
			//		reader.setDebug(new PrintWriter(new FileWriter("writer.debug", true)));
			//			reader.setCursorPosition(120);
			reader.setHandleUserInterrupt(true);
			reader.setHistoryEnabled(true);
			reader.setPrompt("Enter your choice > ");

			menu = new Menu();

		} catch (Exception e) {
			throw new TimeRegistrationException(e.getMessage(), e);
		}
	}

	public void doMenu() {

		Map<String, Object> projects = configuration.buildProjectMap();
//		Set<Entry<String, Project>> entrySet = projects.entrySet();
//		for (Entry<String, Project> entry : entrySet) {
//			Project project = entry.getValue();
//			logger.debug(project.toString());
//			menu.addCategory(project);
//
//			Map<String, Task> tasks = project.getTasks();
//			Set<Entry<String, Task>> entrySet2 = tasks.entrySet();
//
//			for (Entry<String, Task> taskEntry : entrySet2) {
//				menu.addTask(taskEntry.getValue());
//				logger.debug("    " + taskEntry.getValue().toString());
//			}
//		}
	}

	public void printMenu() throws IOException {
		reader.println("******");

		for (MenuCategory category : menu.getCategories()) {
			reader.println(category.getProject().getProjectName());
			for (MenuItem item : category.getItems()) {
				reader.println("    " + item.getIdx() + " : " + item.getTask().getTask() + ", " + item.getTask().getAccentureCode());
			}
		}
		reader.println();
		reader.println("Active Registration:");
		reader.println("  Started : " + TimeRegistrationPropertyKeys.consoleOutFormat.format(taskStartTime));
		reader.println("  Project : " + activeTask.getProject().getProjectName());
		reader.println("  Task    : " + activeTask.getTask());
		reader.println();
		reader.flush();

	}

}
