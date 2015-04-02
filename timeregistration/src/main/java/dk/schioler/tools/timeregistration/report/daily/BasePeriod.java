package dk.schioler.tools.timeregistration.report.daily;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.report.Period;
import dk.schioler.tools.timeregistration.report.Timeslot;
import dk.schioler.tools.timeregistration.report.Visitor;

public abstract class BasePeriod implements Period {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final DateTime start;
	private final DateTime end;
	private List<Timeslot> timeslots = new ArrayList<Timeslot>();

	private long summedDuration = 0;

	protected BasePeriod(DateTime start, DateTime end) {
		this.start = start;
		this.end = end;
	}

	protected void addTimeslot(Timeslot timeslot) {
		timeslots.add(timeslot);
	}

	@Override
	public boolean addEvent(Event event) {
		boolean addEvent = false;
		logger.debug("addEvent= {}" + event);

		if (event.getStart() >= this.start.getMillis() && event.getStart() < this.end.getMillis()) {
			for (Timeslot timeslot : timeslots) {
				addEvent = timeslot.addEvent(event);
				if (addEvent) {
					summedDuration = summedDuration + event.getDurationInMillis();
					break;
				}
			}
		} else {
			logger.debug("skipping event: {}", event.getStart());
		}
		logger.debug("returning {}", addEvent);
		return addEvent;
	}

	@Override
	public long getSummedDuration() {
		return summedDuration;
	}

	@Override
	public boolean acccept(Visitor visitor) {
		boolean carryOn = visitor.visit(this);
		if (carryOn) {
			for (Timeslot timeslot : timeslots) {
				carryOn = timeslot.acccept(visitor);
				if (!carryOn) {
					break;
				}

			}
		}
		return carryOn;
	}

	@Override
	public Date getPeriodStart() {
		return start.toDate();
	}

	@Override
	public Date getPeriodEnd() {
		return end.toDate();
	}

	protected DateTime getStart() {
		return start;
	}

	protected DateTime getEnd() {
		return end;
	}
}
