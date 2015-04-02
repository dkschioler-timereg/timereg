package dk.schioler.tools.timeregistration.report.daily;

import java.util.Date;

import org.joda.time.DateTime;

import dk.schioler.tools.timeregistration.report.Timeslot;
import dk.schioler.tools.timeregistration.statistics.EventStats;

public class PeriodWithOneTimeslotOnTask extends BasePeriod {

	public PeriodWithOneTimeslotOnTask(Date start, Date end) {
		this(new DateTime(start), new DateTime(end));
	}

	public PeriodWithOneTimeslotOnTask(DateTime start, DateTime end) {
		super(new DateTime(start), new DateTime(end));

		Timeslot ts = new TimeslotImpl(getStart(), getEnd(), EventStats.EVENTCATEGORYPROVIDER_TASK);
		this.addTimeslot(ts);
	}

	@Override
	public String getName() {
		return "PeriodWithOneTimeslotOnAcnCode";
	}

}
