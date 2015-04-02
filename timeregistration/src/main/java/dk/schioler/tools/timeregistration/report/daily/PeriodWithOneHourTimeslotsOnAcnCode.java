package dk.schioler.tools.timeregistration.report.daily;

import java.util.Date;

import org.joda.time.DateTime;

import dk.schioler.tools.timeregistration.report.Timeslot;
import dk.schioler.tools.timeregistration.statistics.EventStats;

public class PeriodWithOneHourTimeslotsOnAcnCode extends BasePeriod {

	public PeriodWithOneHourTimeslotsOnAcnCode(Date start, Date end) {
		this(new DateTime(start), new DateTime(end));
	}

	public PeriodWithOneHourTimeslotsOnAcnCode(DateTime start, DateTime end) {
		super(new DateTime(start), new DateTime(end));
		DateTime end2 = this.getEnd();
		DateTime start2 = this.getStart();
		start2 = start2.minus(start2.getMinuteOfHour());
		start2 = start2.minus(start2.getSecondOfMinute());
		while (start2.getMillis() < end2.getMillis()) {
			DateTime add1H = start2.plusHours(1);
			Timeslot ts = new TimeslotImpl(start2, add1H, EventStats.EVENTCATEGORYPROVIDER_ACNCODE);
			this.addTimeslot(ts);
			start2 = add1H;
		}
		Timeslot ts = new TimeslotImpl(start2, start2.plusHours(1), EventStats.EVENTCATEGORYPROVIDER_ACNCODE);
		this.addTimeslot(ts);

	}

	@Override
	public String getName() {
		return "PeriodWithOneTimeslotOnAcnCode";
	}

}
