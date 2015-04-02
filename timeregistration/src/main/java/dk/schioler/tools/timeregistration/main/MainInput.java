package dk.schioler.tools.timeregistration.main;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dk.schioler.tools.timeregistration.input.ConsoleMenuRunner;
import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.model.Project;
import dk.schioler.tools.timeregistration.report.Visitor;
import dk.schioler.tools.timeregistration.report.VisitorPrintOverview;
import dk.schioler.tools.timeregistration.report.VisitorPrintTimeline;
import dk.schioler.tools.timeregistration.report.daily.PeriodWithOneHourTimeslotsOnAcnCode;
import dk.schioler.tools.timeregistration.report.daily.PeriodWithOneTimeslotOnAcnCode;
import dk.schioler.tools.timeregistration.report.daily.PeriodWithOneTimeslotOnTask;
import dk.schioler.tools.timeregistration.statistics.EventStats;

public class MainInput {
	private static Logger LOG = LoggerFactory.getLogger(MainInput.class);

	public static void main(String[] args) {
		LOG.trace("main invoked");
		String[] cfg = { "/dk.schioler.timeregistration.ApplicationContext.xml" };
		ClassPathXmlApplicationContext appCtx = null;
		try {
			DateTime start = new DateTime();

			appCtx = new ClassPathXmlApplicationContext(cfg);
			ConsoleMenuRunner bean = appCtx.getBean("consoleMenuRunner", ConsoleMenuRunner.class);
			bean.run();

			DateTime end = new DateTime();
			EventStats stats = appCtx.getBean("eventStats", EventStats.class);

			start = start.withField(DateTimeFieldType.minuteOfHour(), 00).withField(DateTimeFieldType.secondOfMinute(), 00);
			end = end.withField(DateTimeFieldType.minuteOfHour(), 59).withField(DateTimeFieldType.secondOfMinute(), 59);

			Map<String, Project> projectsInPeriod = stats.getEventsInPeriod(start.toDate(), end.toDate());
			List<Event> allEventsInDateOrderAndAddEndtime = stats.getAllEventsInDateOrderAndAddEndtime(projectsInPeriod);

			PeriodWithOneHourTimeslotsOnAcnCode period = new PeriodWithOneHourTimeslotsOnAcnCode(start, end);
			for (Event event : allEventsInDateOrderAndAddEndtime) {
				period.addEvent(event);
			}
			Visitor v = new VisitorPrintTimeline(System.out);
			period.acccept(v);

			PeriodWithOneTimeslotOnTask pOnTask = new PeriodWithOneTimeslotOnTask(start, end);
			for (Event event : allEventsInDateOrderAndAddEndtime) {
				pOnTask.addEvent(event);
			}
			Visitor vOverView = new VisitorPrintOverview(System.out);
			pOnTask.acccept(vOverView);

			PeriodWithOneTimeslotOnAcnCode pOnAcn = new PeriodWithOneTimeslotOnAcnCode(start, end);
			for (Event event : allEventsInDateOrderAndAddEndtime) {
				pOnAcn.addEvent(event);
			}
			pOnAcn.acccept(vOverView);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (appCtx != null) {
				appCtx.close();
			}
		}

	}
}
