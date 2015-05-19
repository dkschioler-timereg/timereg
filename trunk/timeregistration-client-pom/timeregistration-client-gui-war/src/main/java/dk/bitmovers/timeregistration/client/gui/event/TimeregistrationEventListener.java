package dk.bitmovers.timeregistration.client.gui.event;

import dk.bitmovers.timeregistration.common.TimeregistrationException;

public interface TimeregistrationEventListener {

	public Object handleEvent(TimeregistrationEvent event) throws TimeregistrationException;
}
