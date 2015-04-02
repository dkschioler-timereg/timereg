package dk.schioler.tools.timeregistration.statistics;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.tools.timeregistration.TimeRegistrationException;
import dk.schioler.tools.timeregistration.model.Event;
import dk.schioler.tools.timeregistration.model.Util;

public class StatsUtil {
	private static final Logger LOG = LoggerFactory.getLogger(StatsUtil.class);

	public static void addEndTime(List<Event> eventsInPeriod) {

		// just to keep track of that we have had a start event
		Event startEvent = null;
		Event previous = null;

		for (Event event : eventsInPeriod) {
			LOG.debug("looking at event=" + event);
			if (event.getTask().getTask().equals(Util.TASK_START.getTask())) {
				if (previous == null) {
					startEvent = event;
					previous = event;
				} else {
					// We have dangling entries... 
					// not even started a period, but previous and current has been set... hmm...
					throw new TimeRegistrationException("found Start event, but previous or current are set...");
				}
			} else if (event.getTask().getTask().equals(Util.TASK_STOP.getTask())) {
				Event stop = event;
				if (startEvent != null) {
					if (previous != null) {
						previous.setEnd(stop.getStart());
						startEvent = null;
						previous = null;
					} else {
						throw new TimeRegistrationException("found STOP event but no previous entry");
					}
				} else {
					throw new TimeRegistrationException("found STOP event, but no START ");
				}
			} else {
				if (startEvent != null && previous != null) {
					previous.setEnd(event.getStart());
					previous = event;
				} else {
					throw new TimeRegistrationException("can not calculate duration of an event, with no event preceeding");
				}
			}

		}

	}

}
