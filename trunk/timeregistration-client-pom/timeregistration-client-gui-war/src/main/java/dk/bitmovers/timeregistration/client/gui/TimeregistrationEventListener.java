package dk.bitmovers.timeregistration.client.gui;

import dk.bitmovers.timeregistration.common.TimeregistrationException;

public interface TimeregistrationEventListener {
	public boolean supports(TimeregistrationEvent event) throws TimeregistrationException;

	public String handleEvent(TimeregistrationEvent event) throws TimeregistrationException;
}
