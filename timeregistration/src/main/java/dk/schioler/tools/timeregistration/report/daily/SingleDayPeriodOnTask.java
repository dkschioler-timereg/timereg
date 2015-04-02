package dk.schioler.tools.timeregistration.report.daily;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

public class SingleDayPeriodOnTask extends PeriodWithOneTimeslotOnTask {

	public SingleDayPeriodOnTask(DateTime theDate) {
		super(new DateTime(theDate).withField(DateTimeFieldType.hourOfDay(), 0).withField(DateTimeFieldType.minuteOfHour(), 00)
		      .withField(DateTimeFieldType.secondOfMinute(), 00), new DateTime(theDate).withField(DateTimeFieldType.hourOfDay(), 23)
		      .withField(DateTimeFieldType.minuteOfHour(), 59).withField(DateTimeFieldType.secondOfMinute(), 59));
		
	}

	@Override
	public String getName() {
		return "SingleDayPeriodOnTask";
	}

}
