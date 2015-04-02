package dk.schioler.tools.timeregistration.report;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import dk.schioler.tools.timeregistration.model.Event;

public class VisitorPrintTimeline implements Visitor {

	private PrintStream printStream;

	public VisitorPrintTimeline(PrintStream printStream) {
		super();
		this.printStream = printStream;
	}

	SimpleDateFormat sdfHourOfDay = new SimpleDateFormat("HH:mm");
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public boolean visit(ReportElement report) {

		if (report instanceof Category) {

		} else if (report instanceof Period) {
			Period p = (Period) report;
			printStream.format("%-15s %15s %n", sdfDate.format(p.getPeriodStart()), sdfDate.format(p.getPeriodEnd()));
		} else if (report instanceof Timeslot) {
			Timeslot ts = (Timeslot) report;
			printStream.format(" %-5s-%5s:  %n", sdfHourOfDay.format(ts.getStart().toDate()), sdfHourOfDay.format(ts.getEnd().toDate()), ts.getSummedDuration());
			List<Event> events = ts.getEvents();
			Collections.sort(events);
			for (Event event : events) {
				printStream.format("   %-15s %10d  %-10s %-38s%n", sdfDate.format(new Date(event.getStart())), event.getDurationInMillis(), event.getTask()
				      .getAccentureCode(), event.getTask().getTask());
			}
		}
		return true;

	}
}
