package dk.bitmovers.timeregistration.client.gui.event;

import java.util.Date;

public class TimeregistrationEventWorkClockStart extends AbstractTimeregistrationEventWorkClock {

	private static final long serialVersionUID = 1L;

	public TimeregistrationEventWorkClockStart(Date date) {
		super(date);
	}

}
