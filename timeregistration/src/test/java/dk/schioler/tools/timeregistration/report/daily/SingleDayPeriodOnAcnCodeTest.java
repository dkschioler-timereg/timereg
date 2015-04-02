package dk.schioler.tools.timeregistration.report.daily;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.model.Project;
import dk.schioler.tools.timeregistration.report.Visitor;
import dk.schioler.tools.timeregistration.report.VisitorPrintOverview;
import dk.schioler.tools.timeregistration.statistics.EventStats;

public class SingleDayPeriodOnAcnCodeTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	static {
		System.setProperty("output.event-file", "events/event.log");
	}

//	Visitor v = new Visitor() {
//		@Override
//		public boolean visit(ReportElement report) {
//			//					System.out.println(report.getName());
//			if (report instanceof Category) {
//				Category cat = (Category) report;
//				System.out.println("category=" + cat.getCategory() + ", sum=" + cat.getSummedDuration());
//				List<EventInTransit> events = cat.getEvents();
//				for (EventInTransit event : events) {
//					System.out.println("    " + event);
//				}
//
//			} else {
//				System.out.println("visitor@" + report);
//			}
//			return true;
//		}
//	};

	@Test
	public void testAcccept() {
		try {

			DateTime testDay = new DateTime(2014, 11, 23, 21, 04);
			DateTime start = new DateTime(testDay).withField(DateTimeFieldType.hourOfDay(), 0).withField(DateTimeFieldType.minuteOfHour(), 00)
			      .withField(DateTimeFieldType.secondOfMinute(), 00);
			DateTime end = new DateTime(testDay).withField(DateTimeFieldType.hourOfDay(), 23).withField(DateTimeFieldType.minuteOfHour(), 59)
			      .withField(DateTimeFieldType.secondOfMinute(), 59);

			EventStats stats = new EventStats("src/test/resources/test-events/event.log");
//			List<EventInTransit> eventsInPeriod = stats.getEventsInPeriod(start.toDate(), end.toDate());
			Map<String, Project> eventsInPeriod = stats.getEventsInPeriod(start.toDate(), end.toDate());
			List<Event> allEventsInDateOrderAndAddEndtime = stats.getAllEventsInDateOrderAndAddEndtime(eventsInPeriod);

			// AcnCode
			SingleDayPeriodOnAcnCode period = new SingleDayPeriodOnAcnCode(testDay);
			logger.debug("started with date= {}", testDay);

			for (Event event : allEventsInDateOrderAndAddEndtime) {
				period.addEvent(event);
			}
			Visitor v = new VisitorPrintOverview(System.out);
			period.acccept(v);

			// Task
			SingleDayPeriodOnTask tastPeriod = new SingleDayPeriodOnTask(testDay);
			logger.debug("started with date= {}", testDay);

			for (Event event : allEventsInDateOrderAndAddEndtime) {
				tastPeriod.addEvent(event);
			}

			tastPeriod.acccept(v);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());

		}

	}
}
