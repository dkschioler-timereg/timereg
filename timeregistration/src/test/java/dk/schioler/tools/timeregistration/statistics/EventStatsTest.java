package dk.schioler.tools.timeregistration.statistics;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.junit.Test;

import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.model.Project;

public class EventStatsTest {

	static {
		System.setProperty("output.event-file", "events/event.log");
	}

	//	@Test
	//	public void testPrintTimeUsageInPeriod() {
	//		try {
	//			EventStats stats = new EventStats("events/event.log");
	//			DateTime start = new DateTime();
	//			start = start.withField(DateTimeFieldType.hourOfDay(), 0).withField(DateTimeFieldType.minuteOfHour(), 00).withField(DateTimeFieldType.secondOfMinute(), 00);
	//			DateTime end = new DateTime();
	//			end = end.withField(DateTimeFieldType.hourOfDay(), 23).withField(DateTimeFieldType.minuteOfHour(), 59).withField(DateTimeFieldType.secondOfMinute(), 59);
	//
	//			stats.printTimeUsageInPeriod(start.toDate(), end.toDate(), System.out);
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//			fail(e.getMessage());
	//		}
	//	}

	@Test
	public void testGetEventsInPeriod() {
		try {
			EventStats stats = new EventStats("events/event.log");
			DateTime start = new DateTime();
			start = start.withField(DateTimeFieldType.hourOfDay(), 0).withField(DateTimeFieldType.minuteOfHour(), 00).withField(DateTimeFieldType.secondOfMinute(), 00);
			DateTime end = new DateTime();
			end = end.withField(DateTimeFieldType.hourOfDay(), 23).withField(DateTimeFieldType.minuteOfHour(), 59).withField(DateTimeFieldType.secondOfMinute(), 59);
			Map<String, Project> eventsInPeriod2 = stats.getEventsInPeriod(start.toDate(), end.toDate());
			List<Event> eventsInPeriod = stats.getAllEventsInDateOrderAndAddEndtime(eventsInPeriod2);
			Event previous = null;
			for (Event event : eventsInPeriod) {
				if (previous == null) {
					previous = event;
				} else {
					assertTrue(previous.getStart() <= event.getStart());
					previous = event;
				}
			}

			//			EventCategoryProvider acnData = new EventCategoryProvider() {
			//				@Override
			//				public String getEventData(EventInTransit e) {
			//					return e.getAcnCode();
			//				}
			//			};
			//			EventCategoryProvider taskData = new EventCategoryProvider() {
			//				@Override
			//				public String getEventData(EventInTransit e) {
			//					return e.getTask();
			//				}
			//			};
			//
			//			Map<String, Category> buildAcnMap = stats.buildMap(eventsInPeriod, acnData);
			//			stats.printDetails(buildAcnMap, taskData, System.out);
			//			stats.printOverview(buildAcnMap, System.out);
			//
			//			Map<String, Category> taskMap = stats.buildMap(eventsInPeriod, taskData);
			//			stats.printDetails(taskMap, acnData, System.out);
			//			stats.printOverview(taskMap, System.out);

		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
