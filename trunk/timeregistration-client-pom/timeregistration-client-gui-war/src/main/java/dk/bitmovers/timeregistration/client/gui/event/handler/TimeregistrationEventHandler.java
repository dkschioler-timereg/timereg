package dk.bitmovers.timeregistration.client.gui.event.handler;

import dk.bitmovers.timeregistration.client.gui.event.TimeregistrationEvent;
import dk.bitmovers.timeregistration.common.TimeregistrationException;

public interface TimeregistrationEventHandler {
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException;

	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException;
}
