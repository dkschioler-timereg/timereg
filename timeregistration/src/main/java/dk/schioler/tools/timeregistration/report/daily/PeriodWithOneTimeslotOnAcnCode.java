package dk.schioler.tools.timeregistration.report.daily;

import java.util.Date;

import org.joda.time.DateTime;

import dk.schioler.tools.timeregistration.report.Timeslot;
import dk.schioler.tools.timeregistration.statistics.EventStats;

public class PeriodWithOneTimeslotOnAcnCode extends BasePeriod {

	public PeriodWithOneTimeslotOnAcnCode(Date start, Date end) {
		this(new DateTime(start), new DateTime(end));
	}

	public PeriodWithOneTimeslotOnAcnCode(DateTime start, DateTime end) {
		super(new DateTime(start), new DateTime(end));

		Timeslot ts = new TimeslotImpl(this.getStart(), getEnd(), EventStats.EVENTCATEGORYPROVIDER_ACNCODE);
		this.addTimeslot(ts);
	}

	@Override
	public String getName() {
		return "PeriodWithOneTimeslotOnAcnCode";
	}

}
