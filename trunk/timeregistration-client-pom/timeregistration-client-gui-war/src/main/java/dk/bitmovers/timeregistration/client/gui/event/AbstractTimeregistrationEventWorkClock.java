package dk.bitmovers.timeregistration.client.gui.event;

import java.util.Date;

import dk.bitmovers.timeregistration.client.gui.TimeregistrationEvent;

public class AbstractTimeregistrationEventWorkClock extends TimeregistrationEvent {

	private static final long serialVersionUID = 1L;

	private final Date time;

	public AbstractTimeregistrationEventWorkClock(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	
}
