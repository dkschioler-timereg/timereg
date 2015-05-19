package dk.bitmovers.timeregistration.client.gui.event;

import java.util.Date;

public class TimeregistrationEventWorkClockStop extends AbstractTimeregistrationEventWorkClock {

	private static final long serialVersionUID = 1L;

	public TimeregistrationEventWorkClockStop(Date date) {
		super(date);
	}

}
