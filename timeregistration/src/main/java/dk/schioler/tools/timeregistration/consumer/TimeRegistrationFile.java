package dk.schioler.tools.timeregistration.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.tools.timeregistration.TimeRegistrationPropertyKeys;

public class TimeRegistrationFile {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Logger errorLogger = LoggerFactory.getLogger(TimeRegistrationPropertyKeys.ERROR_LOG);
	private final Logger eventLogger = LoggerFactory.getLogger(TimeRegistrationPropertyKeys.EVENT_LOG);

//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");

//	private final File outputFile;

//	public TimeRegistrationFile(String outputFile) {
//		super();
//		this.outputFile = new File(outputFile);
//
//	}

	public void registerEvent(String event) {
		try {
			eventLogger.info(event);
//			FileUtils.writeStringToFile(outputFile, event + System.lineSeparator(), StandardCharsets.ISO_8859_1, true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorLogger.error(event);
		}
	}

//	/*
//	 * Find alle dagens events.
//	 * Beregn varighed af hver event.
//	 * Organisér på accenture koder og summér
//	 * Organisér på Task koder og summér
//	 *
//	 */
//
//	public List<Event> getEventsInPeriod(Date start, Date end) throws IOException {
//		List<Event> eventsInPriod = new ArrayList<Event>();
//
//		List<String> readLines = FileUtils.readLines(outputFile);
//		long startAsLong = start.getTime();
//		long endAsLong = end.getTime();
//
//		for (String string : readLines) {
//			Event e = new Event(string);
//			if (e.time.getTime() >= startAsLong && e.time.getTime() < endAsLong) {
//				eventsInPriod.add(e);
//			}
//		}
//
//		Collections.sort(eventsInPriod);
//		return eventsInPriod;
//	}
//
//	public void printTimeUsageInPeriod(Date start, Date end, PrintStream printStream) throws IOException {
//		//		Map<String, Category> usageOnAccCode = new TreeMap<String, TimeRegistrationFile.Category>();
//
//		List<Event> eventsInPeriod = getEventsInPeriod(start, end);
//		addDuration(eventsInPeriod);
////		printStream.format("%10s%10s%6s %16s%n", "time", "AcnCode", "Duration in secs", "Task");
////		for (Event event : eventsInPeriod) {
////			printStream.format("%10s%10s%6d %16s%n", sdf.format(event.time), event.acnCode, event.durationInMillis / 1000, event.task);
////		}
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
//				codeDuration= event.durationInMillis;
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
//				codeDuration= event.durationInMillis;
//				curCode = event.task;
//				printStream.format("%10s %10s %6d%n", sdf.format(event.time), event.task, event.durationInMillis);
//			}
//		}
//		printStream.format("%31d%n", codeDuration);
//
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
//			printStream.format("%15s %10d %n", entry.getValue().category.trim(), summedDuraction );
//		}
//
//		//		rv.append("**********************************").append(System.lineSeparator());
//
//		//		return rv.toString();
//	}
//
//	/*
//	 * An event is the start of an activity.
//	 * Therefor the duration of the event must be calculated when the next event occurs.
//	 */
//	public void addDuration(List<Event> eventsInPeriod) {
//
//		Event startEvent = null;
//		//		Event stopEvent = null;
//
//		Event previous = null;
//
//		for (Event event : eventsInPeriod) {
//			logger.debug("looking at event=" + event);
//			if (event.project.equals(Project.START_REGISTRATION.getProject())) {
//				if (previous == null) {
//					startEvent = event;
//					previous = event;
//				} else {
//					// We have dangling entries...
//					// not even started a period, but previous and current has been set... hmm...
//					throw new TimeRegistrationException("found Start event, but previous or current are set...");
//				}
//			} else if (event.project.equals(Project.STOP_REGISTRATION.getProject())) {
//				Event stop = event;
//				if (startEvent != null) {
//					if (previous != null) {
//						previous.durationInMillis = stop.time.getTime() - previous.time.getTime();
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
//					previous.durationInMillis = event.time.getTime() - previous.time.getTime();
//					logger.debug("settinmg duration=" + previous.durationInMillis);
//					previous = event;
//				} else {
//					throw new TimeRegistrationException("can not calculate duration of an event, with no event preceeding");
//				}
//			}
//
//		}
//
//	}
//
//	public class Category {
//		String category;
//		//		long summedDurationInMillis = -1;
//		List<Event> events = new ArrayList<Event>();
//
//		public Category(String category) {
//			super();
//			this.category = category;
//		}
//
//		public void addEvent(Event e) {
//			events.add(e);
//
//		}
//
//		public long getSummedDuraction() {
//			long duration = 0;
//			for (Event event : events) {
//				duration = duration + event.durationInMillis;
//			}
//			return duration;
//		}
//
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + getOuterType().hashCode();
//			result = prime * result + ((category == null) ? 0 : category.hashCode());
//			result = prime * result + ((events == null) ? 0 : events.hashCode());
//			return result;
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			Category other = (Category) obj;
//			if (!getOuterType().equals(other.getOuterType()))
//				return false;
//			if (category == null) {
//				if (other.category != null)
//					return false;
//			} else if (!category.equals(other.category))
//				return false;
//			if (events == null) {
//				if (other.events != null)
//					return false;
//			} else if (!events.equals(other.events))
//				return false;
//			return true;
//		}
//
//		private TimeRegistrationFile getOuterType() {
//			return TimeRegistrationFile.this;
//		}
//
//		@Override
//		public String toString() {
//			return "Category [category=" + category + ", events=" + events + "]";
//		}
//
//	}
//
//	public class Event implements Comparable<Event> {
//		public final static String SEPARATOR = ";";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
//		int dateIdx = 0;
//		int projectIdx = 1;
//		int taskIdx = 2;
//		int acnCodeIdx = 3;
//		String acnCode;
//		String project;
//		String task;
//		Date time;
//		long durationInMillis;
//
//		public Event(String line) {
//			try {
//				String[] split = line.split(";");
//				time = sdf.parse(split[dateIdx]);
//				project = split[projectIdx];
//				task = split[taskIdx];
//				acnCode = split[acnCodeIdx];
//			} catch (Exception e) {
//				throw new TimeRegistrationException(e.getMessage(), e);
//			}
//		}
//
//		@Override
//		public int compareTo(Event arg0) {
//			long span = this.time.getTime() - arg0.time.getTime();
//			return span > 0 ? 1 : -1;
//		}
//
//		@Override
//		public String toString() {
//			return "Event [acnCode=" + acnCode + ", project=" + project + ", task=" + task + ", time=" + time + ", durationInMillis=" + durationInMillis + "]";
//		}
//
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + getOuterType().hashCode();
//			result = prime * result + ((acnCode == null) ? 0 : acnCode.hashCode());
//			result = prime * result + (int) (durationInMillis ^ (durationInMillis >>> 32));
//			result = prime * result + ((project == null) ? 0 : project.hashCode());
//			result = prime * result + ((task == null) ? 0 : task.hashCode());
//			result = prime * result + ((time == null) ? 0 : time.hashCode());
//			return result;
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			Event other = (Event) obj;
//			if (!getOuterType().equals(other.getOuterType()))
//				return false;
//			if (acnCode == null) {
//				if (other.acnCode != null)
//					return false;
//			} else if (!acnCode.equals(other.acnCode))
//				return false;
//			if (durationInMillis != other.durationInMillis)
//				return false;
//			if (project == null) {
//				if (other.project != null)
//					return false;
//			} else if (!project.equals(other.project))
//				return false;
//			if (task == null) {
//				if (other.task != null)
//					return false;
//			} else if (!task.equals(other.task))
//				return false;
//			if (time == null) {
//				if (other.time != null)
//					return false;
//			} else if (!time.equals(other.time))
//				return false;
//			return true;
//		}
//
//		private TimeRegistrationFile getOuterType() {
//			return TimeRegistrationFile.this;
//		}
//
//	}

}
